package com.example.glproject.DAO;

import java.util.List;

import com.example.glproject.businessobjects.GenericTask;

public interface GenericTaskDAO extends DAO<GenericTask> {

	void update(GenericTask gt);

	List<GenericTask> getByType(String type);

	GenericTask getGenericTask(String reference);
}
