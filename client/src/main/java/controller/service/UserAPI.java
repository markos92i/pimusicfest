package controller.service;

import java.io.IOException;

import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;

import controller.client.Connector;
import model.User;
import model.mapping.UserMapper;

public class UserAPI {
	private static final String path = "/user";

	public static User[] list() throws JsonParseException, JsonMappingException, IOException {
		Connector connector = Connector.getInstance();
		String dir = path + "/";
		Response response = connector.get(dir);
		if (response.getStatus() != 200) { throw new RuntimeException("Error: " + response.getStatus()); }
		JsonNode output = response.readEntity(JsonNode.class);
		User[] users = new User[output.size()];
		for(int i=0; i<output.size(); i++) { users[i] = UserMapper.fromJson(output.get(i).asText().toString()); }	
		return users;
	}

	public static void create(User object) throws JsonParseException, JsonMappingException, IOException {
		Connector connector = Connector.getInstance();
		String dir = path + "/create";
		Response response = connector.put(dir, object);
		if (response.getStatus() != 200) { throw new RuntimeException("Error: " + response.getStatus()); }
	}
	
	public static User read(String _id) throws JsonParseException, JsonMappingException, IOException {
		Connector connector = Connector.getInstance();
		String dir = path + "/read/" + _id;
		Response response = connector.get(dir);
		if (response.getStatus() != 200) { throw new RuntimeException("Error: " + response.getStatus()); }
		JsonNode output = response.readEntity(JsonNode.class);
		User user = UserMapper.fromJson(output.toString());
		return user;
	}
	
	public static void update(User object) throws JsonParseException, JsonMappingException, IOException {
		Connector connector = Connector.getInstance();
		String dir = path + "/update";
		Response response = connector.put(dir, object);
		if (response.getStatus() != 200) { throw new RuntimeException("Error: " + response.getStatus()); }
	}
	
	public static void delete(String _id) throws JsonParseException, JsonMappingException, IOException {
		Connector connector = Connector.getInstance();
		String dir = path + "/delete/" + _id;
		Response response = connector.delete(dir);
		if (response.getStatus() != 200) { throw new RuntimeException("Error: " + response.getStatus()); }
	}

}
