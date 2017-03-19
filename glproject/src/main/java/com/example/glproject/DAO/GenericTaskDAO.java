package com.example.glproject.DAO;

import java.util.List;

import com.example.glproject.businessobjects.GenericTask;

public interface GenericTaskDAO {

	GenericTask getGenericTask();

	List<GenericTask> getGenericTasks();

	void add(GenericTask gt);

	void update(GenericTask gt);

	void delete(String reference);

	List<GenericTask> getByType(int type);// Returns a list GenericTask by
											// Plane_type

	GenericTask get(String reference);// Return generictask corresponding the
										// reference
}
