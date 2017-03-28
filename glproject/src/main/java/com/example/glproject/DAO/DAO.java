package com.example.glproject.DAO;

import com.example.glproject.businessobjects.Flight;
import org.elasticsearch.action.update.UpdateRequest;

import java.util.List;

public interface DAO<T> {

	public abstract T get(long id, String type);

	public abstract void add(T obj, String type);

	public abstract void update(UpdateRequest updateReq);

	public abstract void delete(long id, String type);
	
	public abstract List<T> getAll(String type);

    public abstract Flight getFlight(String commercial);

    public abstract List<Flight> getFlights();
}
