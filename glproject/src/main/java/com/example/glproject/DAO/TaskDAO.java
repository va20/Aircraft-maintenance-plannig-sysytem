package com.example.glproject.DAO;

import java.io.IOException;
import java.util.List;

import com.example.glproject.businessobjects.Task;

public interface TaskDAO {

	List<Task> getTasks();

	Task getTask(long id);

	void addTask(Task task);

	void update(Task task) throws IOException;

	void delete(long id);

	List<Task> getTasksByMRO(long id);

	List<Task> getTasksByPlane(long id);
}
