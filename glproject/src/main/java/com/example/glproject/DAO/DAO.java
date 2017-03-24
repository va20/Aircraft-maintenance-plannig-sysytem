package com.example.glproject.DAO;

import java.util.List;

import org.elasticsearch.action.update.UpdateRequest;

public interface DAO<T> {

	public abstract T get(long id, String type);

	public abstract void add(T obj, String type);

	public abstract void update(UpdateRequest updateReq);

	public abstract void delete(long id, String type);
	
	public abstract List<T> getAll(String type);
}
