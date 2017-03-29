package com.example.glproject.DAO;

import org.elasticsearch.action.update.UpdateRequest;

import java.util.List;

public interface DAO<T> {

	public abstract T get(String type, String id);

	public abstract void add(T obj, String type, String id);

	public abstract void update(UpdateRequest updateReq);

	public abstract void delete(String type, String id);

	public abstract List<T> getAll(String type);

	public abstract void deleteAll(String type);
}
