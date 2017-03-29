package com.example.glproject.businessobjects;

import org.apache.commons.codec.digest.DigestUtils;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Staff {
	private long id;
	private String firstname, lastname;
	private String login, password,salt;

	public Staff() {
	}

	public Staff(long id, String firstname, String lastname, String login, String password) throws NoSuchAlgorithmException {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.login = login;
		this.salt=generate_salt();
		this.password = hash_pass(password+salt);
	}

	public long getId() {
		return id;
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

	public void setId(long id) {
		this.id = id;
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

	public String getSalt(){
		return this.salt;
	}

	public String hash_pass(String pass) {
		String pass_tmp = DigestUtils.sha512Hex(pass);
		for (int i = 0; i < 10000; i++) {
			pass_tmp = DigestUtils.sha512Hex(pass);
		}
		return pass_tmp;
	}

	public String generate_salt() throws NoSuchAlgorithmException {
		//Always use a SecureRandom generator
		SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
		//Create array for salt
		byte[] salt_byte = new byte[32];
		String salt=null;
		//Get a random salt
		sr.nextBytes(salt_byte);
		StringBuilder salt_stringB=new StringBuilder();
		for(int i=0; i< salt_byte.length ;i++) {
                salt_stringB.append(Integer.toString((salt_byte[i] & 0xff) + 0x100, 16).substring(1));
		}
		salt=salt_stringB.toString();
		return salt;

	}

	@Override
	public String toString() {
		return "Staff [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", login=" + login
				+ ", password=" + password + "]";
	}
}