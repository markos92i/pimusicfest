package controller.service;

import java.io.IOException;

import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;

import controller.client.Connector;
import model.LineUp;
import model.mapping.LineUpMapper;

public class LineUpAPI {
	private static final String path = "/lineup";

	public static LineUp[] list() throws JsonParseException, JsonMappingException, IOException {
		Connector connector = Connector.getInstance();
		String dir = path + "/";
		Response response = connector.get(dir);
		if (response.getStatus() != 200) { throw new RuntimeException("Error: " + response.getStatus()); }
		JsonNode output = response.readEntity(JsonNode.class);
		LineUp[] lineUps = new LineUp[output.size()];
		for(int i=0; i<output.size(); i++) { lineUps[i] = LineUpMapper.fromJson(output.get(i).asText().toString()); }	
		return lineUps;
	}

	public static void create(LineUp object) throws JsonParseException, JsonMappingException, IOException {
		Connector connector = Connector.getInstance();
		String dir = path + "/create";
		Response response = connector.put(dir, object);
		if (response.getStatus() != 200) { throw new RuntimeException("Error: " + response.getStatus()); }
	}
	
	public static LineUp read(String _id) throws JsonParseException, JsonMappingException, IOException {
		Connector connector = Connector.getInstance();
		String dir = path + "/read/" + _id;
		Response response = connector.get(dir);
		if (response.getStatus() != 200) { throw new RuntimeException("Error: " + response.getStatus()); }
		JsonNode output = response.readEntity(JsonNode.class);
		LineUp lineUp = LineUpMapper.fromJson(output.toString());
		return lineUp;
	}
	
	public static void update(LineUp object) throws JsonParseException, JsonMappingException, IOException {
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
