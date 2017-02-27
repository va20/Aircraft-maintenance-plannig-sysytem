package com.example.glproject.businessobjects;

import java.util.Date;

/**
 * Created by info on 20/02/17.
 */
public class Task {

	private int id;
	private Plane plane;
	private Mro mro;
	private Date deadline;

	public Task(int id, Plane plane, Mro mro, Date deadline) {
		this.id = id;
		this.plane = plane;
		this.mro = mro;
		this.deadline = deadline;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Plane getPlane() {
		return plane;
	}

	public void setPlane(Plane plane) {
		this.plane = plane;
	}

	public Mro getMro() {
		return mro;
	}

	public void setMro(Mro mro) {
		this.mro = mro;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

}
