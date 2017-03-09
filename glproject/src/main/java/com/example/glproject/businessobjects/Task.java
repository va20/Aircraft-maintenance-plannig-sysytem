package com.example.glproject.businessobjects;

import java.util.Date;

public class Task extends GenericTask {
	private long id;
	private long idPlane;
	private MRO mro;
	private Date deadline;

	public Task() {
	}

	public Task(String taskNumber, int zone, String descr, long periodicity, boolean hangar, long duration, int men,
			String applicability, int id, long idPlane, MRO mro, Date deadline) {
		super(taskNumber, zone, descr, periodicity, hangar, duration, men, applicability);
		this.id = id;
		this.idPlane = idPlane;
		this.mro = mro;
		this.deadline = deadline;
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

	public MRO getMro() {
		return mro;
	}

	public void setMro(MRO mro) {
		this.mro = mro;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}
}
