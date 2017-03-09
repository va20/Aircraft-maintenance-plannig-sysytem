package com.example.glproject.DAO;

import com.example.glproject.DAOImpl.FlightDAOImpl;
import com.example.glproject.DAOImpl.GenericTaskDAOImpl;
import com.example.glproject.DAOImpl.MRODAOImpl;
import com.example.glproject.DAOImpl.PlaneDAOImpl;
import com.example.glproject.DAOImpl.TaskDAOImpl;

public class DAOFactory {

	public static FlightDAO getFlightDAO() {
		return new FlightDAOImpl();
	}

	public static GenericTaskDAO getGenericTaskDAO() {
		return new GenericTaskDAOImpl();
	}

	public static MRODao getMroDAO() {
		return new MRODAOImpl();
	}

	public static PlaneDAO getPlaneDAO() {
		return new PlaneDAOImpl();
	}

	public static TaskDAO getTaskDAO() {
		return new TaskDAOImpl();
	}
}
