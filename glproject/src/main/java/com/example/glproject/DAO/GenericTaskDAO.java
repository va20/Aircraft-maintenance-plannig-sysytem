package com.example.glproject.DAO;

import java.util.List;

import com.example.glproject.businessobjects.GenericTask;

public interface GenericTaskDAO {
	boolean add(GenericTask g_task, String plane_type);

	boolean update(GenericTask g_task);

	boolean delete(String reference);

	List<GenericTask> getByType(int type);// Returns a list GenericTask by
											// Plane_type

	GenericTask get(String reference);// Return generictask corresponding the
										// reference
}
