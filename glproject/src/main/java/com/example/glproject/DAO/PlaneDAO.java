package com.example.glproject.DAO;

import com.example.glproject.businessobjects.Plane;

public interface PlaneDAO extends DAO<Plane> {

	void updatePlane(Plane plane);
}
