package com.glproject.groupe3.DAOImpl;

import com.glproject.groupe3.DAO.DAOImpl;
import com.glproject.groupe3.DAO.PlaneDAO;
import com.glproject.groupe3.businessobjects.Plane;

public class PlaneDAOImpl extends DAOImpl<Plane> implements PlaneDAO {
	
	public PlaneDAOImpl(Class<Plane> typeT) {
		super(typeT);
	}
}