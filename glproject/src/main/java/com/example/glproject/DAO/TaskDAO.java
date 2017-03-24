package com.example.glproject.DAO;

import java.util.List;

import com.example.glproject.businessobjects.Task;

public interface TaskDAO extends DAO<Task> {

	void update(Task task);

	List<Task> getTasksByMRO(long id);

	List<Task> getTasksByPlane(long id);
}
