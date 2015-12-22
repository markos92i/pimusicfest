package controller.service;

import java.io.IOException;

import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;

import controller.client.Connector;
import model.Artist;
import model.mapping.ArtistMapper;

public class ArtistAPI {
	private static final String path = "/artist";

	public static Artist[] list() throws JsonParseException, JsonMappingException, IOException {
		Connector connector = Connector.getInstance();
		String dir = path + "/";
		Response response = connector.get(dir);
		if (response.getStatus() != 200) { throw new RuntimeException("Error: " + response.getStatus()); }
		JsonNode output = response.readEntity(JsonNode.class);
		Artist[] artists = new Artist[output.size()];
		for(int i=0; i<output.size(); i++) { artists[i] = ArtistMapper.fromJson(output.get(i).asText().toString()); }	
		return artists;
	}

	public static void create(Artist object) throws JsonParseException, JsonMappingException, IOException {
		Connector connector = Connector.getInstance();
		String dir = path + "/create";
		Response response = connector.put(dir, object);
		if (response.getStatus() != 200) { throw new RuntimeException("Error: " + response.getStatus()); }
	}
	
	public static Artist read(String _id) throws JsonParseException, JsonMappingException, IOException {
		Connector connector = Connector.getInstance();
		String dir = path + "/read/" + _id;
		Response response = connector.get(dir);
		if (response.getStatus() != 200) { throw new RuntimeException("Error: " + response.getStatus()); }
		JsonNode output = response.readEntity(JsonNode.class);
		Artist artist = ArtistMapper.fromJson(output.toString());
		return artist;
	}
	
	public static void update(Artist object) throws JsonParseException, JsonMappingException, IOException {
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
