package model.authentication;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import model.User;
import model.mapping.UserMapper;
import model.persistence.UserDAO;
import net.jodah.expiringmap.ExpiringMap;

public class Authentication {
	private static final long time = 60;
	private static final TimeUnit unit = TimeUnit.MINUTES;
	private static Map<String, User> tokenHash = ExpiringMap.builder().expiration(time, unit).build(); 
	
	public static User getUser(String token) {
		return tokenHash.get(token);
	}
	public static String login(String _id, String password) throws JsonProcessingException, IOException {
		String token = "";
		UserDAO userDAO = new UserDAO();
		JsonNode userJson = userDAO.read(_id);
		User user = UserMapper.fromJson(userJson.toString());
		if(_id.toLowerCase().equals(user.get_id().toLowerCase()) && password.equals(user.getPassword())) {
		    UUID uuid = UUID.randomUUID(); //Token Generation
		    token = uuid.toString();
			tokenHash.put(token, user);
		}
		return token;
	}
	public static boolean logout(String token) {
		return tokenHash.remove(token)!=null;
	}
	public static void register(String username, String password) {
		UserDAO userDAO = new UserDAO();
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode user = mapper.createObjectNode();
		user.put("_id", username);
		user.put("password", password);
		user.put("role", "user");
		userDAO.create(user);
	}

}
