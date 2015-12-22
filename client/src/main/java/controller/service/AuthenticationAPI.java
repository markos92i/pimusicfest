package controller.service;

import java.io.IOException;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;

import controller.client.Connector;
import model.utils.Base64Util;

public class AuthenticationAPI {
	private static final String path = "/auth";
	
	public static void signup(String _id, String password) throws JsonParseException, JsonMappingException, IOException {
		Connector connector = Connector.getInstance();
		String dir = path + "/signup";
		String authString = _id + ":" + password;
		connector.setToken(Base64Util.Encode(authString));
		Response response = connector.get(dir);
		if (response.getStatus() != 200) { throw new RuntimeException("Error: " + response.getStatus()); }
	}
	
	public static boolean signin(String _id, String password) throws JsonParseException, JsonMappingException, IOException {
		Connector connector = Connector.getInstance();
		String dir = path + "/signin";
		boolean access;
		String authString = _id + ":" + password;
		connector.setToken(Base64Util.Encode(authString));
		Response response = connector.get(dir);
		if (response.getStatus() != 200) { throw new RuntimeException("Error: " + response.getStatus()); }
		JsonNode output = response.readEntity(JsonNode.class);
		access = output.get("access").asBoolean();
		if (access) { connector.setToken(output.get("token").asText()); }
		return access;
	}
		
	public static void signout() throws JsonParseException, JsonMappingException, IOException {
		Connector connector = Connector.getInstance();
		String dir = path + "/signout/";
		Response response = connector.delete(dir);
		connector.setToken("");
		if (response.getStatus() != 200) { throw new RuntimeException("Error: " + response.getStatus()); }
	}

}
