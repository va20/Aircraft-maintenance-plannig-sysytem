
package com.glproject.groupe3.businessobjects;

public class MRO {
	private long id;
	private long idPlane;

	public MRO() {
	}

	public MRO(long id, long idPlane) {
		super();
		this.id = id;
		this.idPlane = idPlane;
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
}