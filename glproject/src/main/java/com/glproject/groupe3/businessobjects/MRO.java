
package com.glproject.groupe3.businessobjects;

import java.security.NoSuchAlgorithmException;

import com.glproject.groupe3.util.Util;

public class MRO {
	private long id;
	private String login, password, salt;
	private String airport;
	private String name;

	public MRO() {
	}

	public MRO(long id, String login, String password, String airport, String name) throws NoSuchAlgorithmException {
		super();
		this.id = id;
		this.login = login;
		this.password = password;
		this.name = name;
		this.airport = airport;
		this.salt = Util.generateSalt();
		this.password = Util.hashPass(password + salt);
	}

	public long getId() {
		return id;
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

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getAirport() {
		return airport;
	}

	public void setAirport(String airport) {
		this.airport = airport;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "MRO [id=" + id + ", login=" + login + ", password=" + password + ", salt=" + salt + ", airport="
				+ airport + ", name=" + name + "]";
	}
}