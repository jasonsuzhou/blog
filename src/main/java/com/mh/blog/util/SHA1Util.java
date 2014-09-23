package com.mh.blog.util;

import java.security.MessageDigest;

import org.apache.commons.codec.binary.Hex;

public class SHA1Util {
	private static final String SHA_1_ALGORITHMS = "SHA-1";
	
	public static String getSummaryPassword(String password) {
		String result = null;
		try {
			MessageDigest digest = MessageDigest.getInstance(SHA_1_ALGORITHMS);
			digest.reset();
			result = new String(Hex.encodeHex(digest.digest(password.getBytes())));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
