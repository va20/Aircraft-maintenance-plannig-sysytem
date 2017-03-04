package com.example.glproject.businessobjects;

import java.util.List;

public class Mro {
	private int id;
	private List<Task> list_task;

	public Mro(int id, List<Task> list_task) {
		this.id = id;
		this.list_task = list_task;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Task> getList_task() {
		return list_task;
	}

	public void setList_task(List<Task> list_task) {
		this.list_task = list_task;
	}
}
