package com.example.glproject.DAO;

import java.io.IOException;
import java.util.List;

import com.example.glproject.businessobjects.GenericTask;

public interface GenericTaskDAO {


	List<GenericTask> getGenericTasks();

	void add(GenericTask gt);

	void update(GenericTask gt) throws IOException;

	void delete(String reference);

	List<GenericTask> getByType(int type);// Returns a list GenericTask by
											// Plane_type

	GenericTask getGenericTask(String reference);// Return generictask corresponding the
										// reference
}
