package com.example.glproject.businessobjects;

import java.util.Date;

public class Task {
	private long id;
	private long idPlane;
	private long idMRO;
	private Date deadline;
	private GenericTask genericTask;
	
	public Task() {		
	}
	
	public Task(long id, long idPlane, long idMRO, Date deadline, GenericTask genericTask) {
		super();
		this.id = id;
		this.idPlane = idPlane;
		this.idMRO = idMRO;
		this.deadline = deadline;
		this.genericTask = genericTask;
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
