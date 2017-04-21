
package com.glproject.groupe3.businessobjects;

public class MRO {
	private long id;
	private long idPlane;
	private String airport;
	private String name;

	public MRO() {
	}

	public MRO(long id, long idPlane, String airport, String name) {
		super();
		this.id = id;
		this.idPlane = idPlane;
		this.airport = airport;
		this.name = name;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}