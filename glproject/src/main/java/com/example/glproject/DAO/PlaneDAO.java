package com.example.glproject.DAO;

import java.util.List;

import com.example.glproject.businessobjects.Plane;

public interface PlaneDAO {

	Plane getPlane(long id);

	List<Plane> getPlanes();

	void add(Plane plane);

	void updatePlane(Plane plane);

	void deletePlane(long id);
	
	void deletePlanes();
}
