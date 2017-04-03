package com.example.glproject.businessobjects;

import org.joda.time.DateTime;

public class Task {
	private long id;
	private long idPlane;
	private long idMRO;
	private DateTime deadline;
	private String taskNumber;

	public Task() {
	}

	public Task(long id, long idPlane, long idMRO, DateTime deadline, String taskNumber) {
		super();
		this.id = id;
		this.idPlane = idPlane;
		this.idMRO = idMRO;
		this.deadline = deadline;
		this.taskNumber = taskNumber;
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

	@Override
	public String toString() {
		return "Task [id=" + id + ", idPlane=" + idPlane + ", idMRO=" + idMRO + ", deadline=" + deadline
				+ ", taskNumber=" + taskNumber + "]";
	}
}