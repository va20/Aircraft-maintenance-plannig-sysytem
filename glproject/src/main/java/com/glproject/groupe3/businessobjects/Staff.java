package com.glproject.groupe3.businessobjects;

import java.security.NoSuchAlgorithmException;

import com.glproject.groupe3.util.Util;

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
		this.salt = Util.generateSalt();
		this.password = Util.hashPass(password + salt);
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

	@Override
	public String toString() {
		return "Staff [firstname=" + firstname + ", lastname=" + lastname + ", login=" + login + ", password="
				+ password + ", salt=" + salt + "]";
	}
}