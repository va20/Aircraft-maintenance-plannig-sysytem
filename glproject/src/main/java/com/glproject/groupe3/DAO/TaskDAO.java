package com.glproject.groupe3.DAO;

import java.util.List;

import com.glproject.groupe3.businessobjects.Task;

public interface TaskDAO extends DAO<Task> {
	
	void update(long id, Task task);

	List<Task> getTasksByMRO(String id);

	List<Task> getTasksByPlane(String id);
}
