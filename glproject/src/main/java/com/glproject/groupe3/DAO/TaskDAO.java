package com.glproject.groupe3.DAO;

import java.util.List;

import com.glproject.groupe3.businessobjects.Task;

public interface TaskDAO extends DAO<Task> {

	List<Task> getTasksByMRO(long id);

	List<Task> getTasksByPlane(String id, String type);
}
