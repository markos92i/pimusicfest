package model.authentication;

public class Encryptation {
	private static final int rounds = 12;
	
	public static boolean checkPassword(String password, String storedPassword) {
		return BCrypt.checkpw(password, storedPassword);
	}
	
	public static String encrypt(String password) {
		return BCrypt.hashpw(password, BCrypt.gensalt(rounds));	
	}
	
}
