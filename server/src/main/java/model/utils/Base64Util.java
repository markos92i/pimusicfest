package model.utils;

import java.util.Base64;

public class Base64Util {
	
	public static String Decode(String encodedAuth) {
		return new String(Base64.getDecoder().decode(encodedAuth));
	}
	
	public static String Encode(String decodedAuth) {
		return Base64.getEncoder().encodeToString(decodedAuth.getBytes());
	}

}
