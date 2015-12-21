package model.mapping;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.Artist;

public class ArtistMapper {
	
	public static String toJson(Artist artist) throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(artist);
	}
	
	public static Artist fromJson(String artist) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(artist, Artist.class);
	}

}
