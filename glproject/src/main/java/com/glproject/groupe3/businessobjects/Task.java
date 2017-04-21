package com.glproject.groupe3.businessobjects;

import org.joda.time.DateTime;

public class Task {
	private long id;
	private long idPlane;
	private String airport;
	private long idMRO;
	private DateTime deadline;
	private String taskNumber;
	private String type;

	public Task() {
	}

	public Task(long id, long idPlane, String airport, long idMRO, DateTime deadline, String taskNumber, String type) {
		super();
		this.id = id;
		this.idPlane = idPlane;
		this.airport = airport;
		this.idMRO = idMRO;
		this.deadline = deadline;
		this.taskNumber = taskNumber;
		this.type = type;
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

	public long getIdMRO() {
		return idMRO;
	}

	public void setIdMRO(long idMRO) {
		this.idMRO = idMRO;
	}

	public DateTime getDeadline() {
		return deadline;
	}

	public void setDeadline(DateTime deadline) {
		this.deadline = deadline;
	}

	public String getTaskNumber() {
		return taskNumber;
	}

	public void setTaskNumber(String taskNumber) {
		this.taskNumber = taskNumber;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", idPlane=" + idPlane + ", airport=" + airport + ", idMRO=" + idMRO + ", deadline="
				+ deadline + ", taskNumber=" + taskNumber + ", type=" + type + "]";
	}
}