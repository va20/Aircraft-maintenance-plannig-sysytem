package com.example.glproject.businessobjects;

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
		this.password = password;
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

	@Override
	public String toString() {
		return "Staff [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", login=" + login
				+ ", password=" + password + "]";
	}
}