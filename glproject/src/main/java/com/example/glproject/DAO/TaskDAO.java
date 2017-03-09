package com.example.glproject.DAO;

import java.util.List;

import com.example.glproject.businessobjects.Task;

public interface TaskDAO {

	List<Task> getTasks();

	Task getTask(long id);

	void addTask(long id);

	void update(long id);

	void delete(long id);

	List<Task> getTasksByMRO(long id);

	List<Task> getTasksByPlane(long id);
}
