package com.example.glproject.businessobjects;

import java.util.List;

public class MRO {
	private long id;
	private List<Task> list_task;

	public MRO(long id, List<Task> list_task) {
		this.id = id;
		this.list_task = list_task;
	}

	public long getId() {
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
