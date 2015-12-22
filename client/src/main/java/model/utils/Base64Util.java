package model.utils;

import java.util.Base64;

public class Base64Util {
	
	public static String Decode(String encodedAuth) {
		byte[] authDecBytes = Base64.getDecoder().decode(encodedAuth);
		return new String(authDecBytes);
	}
	
	public static String Encode(String decodedAuth) {
		return Base64.getEncoder().encodeToString(decodedAuth.getBytes());
	}

}
