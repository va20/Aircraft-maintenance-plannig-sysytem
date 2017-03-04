package com.example.glproject.businessobjects;

import java.util.Date;

public class Flight {
	private Plane plane;
	private String commercial;
	private Date dep_airport;
	private Date arr_airport;
	private Date dep_time;
	private Date arr_time;

	public Flight(Plane plane, String commercial, Date dep_airport, Date arr_airport, Date dep_time, Date arr_time) {
		this.arr_airport = arr_airport;
		this.arr_time = arr_time;
		this.commercial = commercial;
		this.dep_airport = dep_airport;
		this.dep_time = dep_time;
		this.plane = plane;
	}

	public Plane getPlane() {
		return plane;
	}

	public void setPlane(Plane plane) {
		this.plane = plane;
	}

	public String getCommercial() {
		return commercial;
	}

	public void setCommercial(String commercial) {
		this.commercial = commercial;
	}

	public Date getDep_airport() {
		return dep_airport;
	}

	public void setDep_airport(Date dep_airport) {
		this.dep_airport = dep_airport;
	}

	public Date getArr_airport() {
		return arr_airport;
	}

	public void setArr_airport(Date arr_airport) {
		this.arr_airport = arr_airport;
	}

	public Date getDep_time() {
		return dep_time;
	}

	public void setDep_time(Date dep_time) {
		this.dep_time = dep_time;
	}

	public Date getArr_time() {
		return arr_time;
	}

	public void setArr_time(Date arr_time) {
		this.arr_time = arr_time;
	}
}
