package com.example.glproject.businessobjects;

import org.joda.time.DateTime;

public class Flight {
	private long idPlane;
	private String commercial;
	private String depAirport;
	private String arrAirport;
	private DateTime depTime;
	private DateTime arrTime;

	public Flight() {
	}

	public Flight(long idPlane, String commercial, String depAirport, String arrAirport, DateTime depTime, DateTime arrTime) {
		this.idPlane = idPlane;
		this.arrAirport = arrAirport;
		this.arrTime = arrTime;
		this.commercial = commercial;
		this.depAirport = depAirport;
		this.depTime = depTime;
	}

	public long getIdPlane() {
		return idPlane;
	}

	public void setIdPlane(long idPlane) {
		this.idPlane = idPlane;
	}

	public String getCommercial() {
		return commercial;
	}

	public void setCommercial(String commercial) {
		this.commercial = commercial;
	}

	public String getDepAirport() {
		return depAirport;
	}

	public void setDepAirport(String depAirport) {
		this.depAirport = depAirport;
	}

	public String getArrAirport() {
		return arrAirport;
	}

	public void setArrAirport(String arrAirport) {
		this.arrAirport = arrAirport;
	}

	public DateTime getDepTime() {
		return depTime;
	}

	public void setDepTime(DateTime depTime) {
		this.depTime = depTime;
	}

	public DateTime getArrTime() {
		return arrTime;
	}

	public void setArrTime(DateTime arrTime) {
		this.arrTime = arrTime;
	}
}
