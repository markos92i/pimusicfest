package model.mapping;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.LineUp;

public class LineUpMapper {
	
	public static String toJson(LineUp lineup) throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(lineup);
	}
	
	public static LineUp fromJson(String lineup) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(lineup, LineUp.class);
	}
	
}
