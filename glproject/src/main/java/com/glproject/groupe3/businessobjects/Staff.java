package com.glproject.groupe3.businessobjects;

import org.apache.commons.codec.digest.DigestUtils;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Staff {
	private String firstname, lastname;
	private String login, password, salt;

	public Staff() {
	}

	public Staff(String firstname, String lastname, String login, String password) throws NoSuchAlgorithmException {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.login = login;
		this.salt = generateSalt();
		this.password = hashPass(password + salt);
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return this.salt;
	}

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

	@Override
	public String toString() {
		return "Staff [firstname=" + firstname + ", lastname=" + lastname + ", login=" + login + ", password="
				+ password + ", salt=" + salt + "]";
	}
}