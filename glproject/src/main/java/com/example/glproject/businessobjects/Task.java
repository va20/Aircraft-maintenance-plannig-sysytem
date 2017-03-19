package com.example.glproject.businessobjects;

import java.util.Date;

public class Task {
	private long id;
	private long idPlane;
	private MRO mro;
	private Date deadline;
	private GenericTask genericTask;

	public Task() {
	}

	public Task(long id, long idPlane, MRO mro, Date deadline, GenericTask gt) {
		this.id = id;
		this.idPlane = idPlane;
		this.mro = mro;
		this.deadline = deadline;
		this.genericTask = gt;
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

	public GenericTask getGenericTask() {
		return genericTask;
	}

	public void setGenericTask(GenericTask genericTask) {
		this.genericTask = genericTask;
	}
}
