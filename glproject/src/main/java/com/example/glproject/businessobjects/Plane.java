package com.example.glproject.businessobjects;

public class Plane {
	private long id;
	private String type;

	public Plane() {
	}

	public Plane(long id, String type, String mpd) {
		this.id = id;
		this.type = type;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
