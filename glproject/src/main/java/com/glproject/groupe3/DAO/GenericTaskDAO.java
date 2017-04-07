package com.glproject.groupe3.DAO;

import java.util.List;

import com.glproject.groupe3.businessobjects.GenericTask;

public interface GenericTaskDAO extends DAO<GenericTask> {

	//void update(GenericTask gt);

	List<GenericTask> getByType(String type);
}
