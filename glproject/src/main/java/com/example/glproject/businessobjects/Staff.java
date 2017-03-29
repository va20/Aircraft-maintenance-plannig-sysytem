package com.example.glproject.businessobjects;

import org.apache.commons.codec.digest.DigestUtils;

public class Staff {
	private long id;
	private String firstname, lastname;
	private String login, password;

	public Staff() {
	}

	public Staff(long id, String firstname, String lastname, String login, String password) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.login = login;

		this.password = hash_pass(password);
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

	public String hash_pass(String pass) {
		String pass_tmp = DigestUtils.sha512Hex(pass);
		for (int i = 0; i < 10000; i++) {
			pass_tmp = DigestUtils.sha512Hex(pass);
		}
		return pass_tmp;
	}

	@Override
	public String toString() {
		return "Staff [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", login=" + login
				+ ", password=" + password + "]";
	}
}