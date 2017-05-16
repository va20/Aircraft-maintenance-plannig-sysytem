package com.glproject.groupe3.util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import org.apache.commons.codec.digest.DigestUtils;

public class Util {

	public static String hashPass(String pass) {
		String passTmp = DigestUtils.sha512Hex(pass);
		for (int i = 0; i < 10000; i++) {
			passTmp = DigestUtils.sha512Hex(pass);
		}

		return passTmp;
	}

	public static String generateSalt() throws NoSuchAlgorithmException {
		SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
		byte[] saltByte = new byte[32];
		String salt = null;
		sr.nextBytes(saltByte);
		StringBuilder saltStringB = new StringBuilder();

		for (int i = 0; i < saltByte.length; i++)
			saltStringB.append(Integer.toString((saltByte[i] & 0xff) + 0x100, 16).substring(1));

		salt = saltStringB.toString();

		return salt;
	}
}