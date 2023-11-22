package org.example.hashing;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class SHA256Helper {

	public static String hash(String data) {
		try {
			
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(data.getBytes(StandardCharsets.UTF_8));

			//we want to end up with hexadecimal values not bytes
			StringBuilder hexadecimalString = new StringBuilder();

            for (byte b : hash) {
                String hexadecimal = Integer.toHexString(transformToPositiveValue(b));

				//padding - makes sure the hexadecimalString will have 64 characters
                if (hexadecimal.length() == 1) hexadecimalString.append('0');
                hexadecimalString.append(hexadecimal);
            }
			
			return hexadecimalString.toString();
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static int transformToPositiveValue(byte hash) {
		return 0xff & hash;
	}
}
