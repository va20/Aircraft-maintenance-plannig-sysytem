package com.example.glproject.businessobjects;

public class Plane {
	private long id;
	private String type;
	private String tailNumber;

	public Plane() {
	}

	public Plane(long id, String type, String tailNumber) {
		super();
		this.id = id;
		this.type = type;
		this.tailNumber = tailNumber;
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

	public String getTailNumber() {
		return tailNumber;
	}

	public void setTailNumber(String tailNumber) {
		this.tailNumber = tailNumber;
	}

	@Override
	public String toString() {
		return "Plane [id=" + id + ", type=" + type + ", tailNumber=" + tailNumber + "]";
	}
}
