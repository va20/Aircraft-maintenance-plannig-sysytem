package com.example.glproject.DAO;

import com.example.glproject.DAOImpl.FlightDAOImpl;
import com.example.glproject.DAOImpl.GenericTaskDAOImpl;
import com.example.glproject.DAOImpl.MroDAOImpl;
import com.example.glproject.DAOImpl.PlaneDAOImpl;
import com.example.glproject.DAOImpl.TaskDAOImpl;

public class DAO {

	public static FlightDAO getFlightDAO() {
		return new FlightDAOImpl();
	}

	public static GenericTaskDAO getGenericTaskDAO() {
		return new GenericTaskDAOImpl();
	}

	public static MroDao getMroDAO() {
		return new MroDAOImpl();
	}

	public static PlaneDAO getplaneDAO() {
		return new PlaneDAOImpl();
	}

	public static TaskDAO getTaskDAO() {
		return new TaskDAOImpl();
	}
}
