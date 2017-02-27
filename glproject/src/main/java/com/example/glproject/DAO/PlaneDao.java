package com.example.glproject.DAO;

import java.util.List;

import com.example.glproject.businessobjects.Plane;

public interface PlaneDao {
	
	boolean add(Plane plane);

	boolean update(int id);

	boolean delete(int id);

	List<Plane> getAllPlanes();
	// List<Plane> ByDay(Date date);
}
