
package com.glproject.groupe3.businessobjects;

public class MRO {
	private long id;
	private long idPlane;
	private String airport;

	public MRO() {
	}

	public MRO(long id, long idPlane, String airport) {
		super();
		this.id = id;
		this.idPlane = idPlane;
		this.airport = airport;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getIdPlane() {
		return idPlane;
	}

	public void setIdPlane(long idPlane) {
		this.idPlane = idPlane;
	}

	public String getAirport() {
		return airport;
	}

	public void setAirport(String airport) {
		this.airport = airport;
	}
}