package com.glproject.groupe3.businessobjects;

import org.joda.time.DateTime;

public class Task {

	public enum Status {
		DONE, NDONE, ONGOING;
	}

	public enum Warning {
		NONE, GREEN, ORANGE, RED;
	}

	private long id;
	private long idPlane;
	private String idMro;
	private DateTime deadline;
	private String taskNumber;
	private String type;
	private Status status;
	private Warning warning;

	public Task() {
	}

	public Task(long id, long idPlane, String idMro, DateTime deadline, String taskNumber, String type, Status status,
			Warning warning) {
		super();
		this.id = id;
		this.idPlane = idPlane;
		this.idMro = idMro;
		this.deadline = deadline;
		this.taskNumber = taskNumber;
		this.type = type;
		this.status = status;
		this.warning = warning;
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

	public String getIdMro() {
		return idMro;
	}

	public void setIdMro(String idMro) {
		this.idMro = idMro;
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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Warning getWarning() {
		return warning;
	}

	public void setWarning(Warning warning) {
		this.warning = warning;
	}
}