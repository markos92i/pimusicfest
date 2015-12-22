package controller.service;

import java.io.IOException;

import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;

import controller.client.Connector;
import model.Sponsor;
import model.mapping.SponsorMapper;

public class SponsorAPI {
	private static final String path = "/sponsor";

	public static Sponsor[] list() throws JsonParseException, JsonMappingException, IOException {
		Connector connector = Connector.getInstance();
		String dir = path + "/";
		Response response = connector.get(dir);
		if (response.getStatus() != 200) { throw new RuntimeException("Error: " + response.getStatus()); }
		JsonNode output = response.readEntity(JsonNode.class);
		Sponsor[] sponsors = new Sponsor[output.size()];
		for(int i=0; i<output.size(); i++) { sponsors[i] = SponsorMapper.fromJson(output.get(i).asText().toString()); }	
		return sponsors;
	}

	public static void create(Sponsor object) throws JsonParseException, JsonMappingException, IOException {
		Connector connector = Connector.getInstance();
		String dir = path + "/create";
		Response response = connector.put(dir, object);
		if (response.getStatus() != 200) { throw new RuntimeException("Error: " + response.getStatus()); }
	}
	
	public static Sponsor read(String _id) throws JsonParseException, JsonMappingException, IOException {
		Connector connector = Connector.getInstance();
		String dir = path + "/read/" + _id;
		Response response = connector.get(dir);
		if (response.getStatus() != 200) { throw new RuntimeException("Error: " + response.getStatus()); }
		JsonNode output = response.readEntity(JsonNode.class);
		Sponsor sponsor = SponsorMapper.fromJson(output.toString());
		return sponsor;
	}
	
	public static void update(Sponsor object) throws JsonParseException, JsonMappingException, IOException {
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
