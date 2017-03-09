package com.example.glproject.businessobjects;

public class Plane {
	private long id;
	private String type;
	private String MPD;

	public Plane() {
	}

	public Plane(long id, String type, String mpd) {
		this.id = id;
		this.type = type;
		this.MPD = mpd;
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

	public String getMPD() {
		return MPD;
	}

	public void setMPD(String mPD) {
		MPD = mPD;
	}
}
