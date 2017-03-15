package com.example.glproject.DAO;

import com.example.glproject.DAOImpl.FlightDAOImpl;
import com.example.glproject.DAOImpl.GenericTaskDAOImpl;
import com.example.glproject.DAOImpl.MRODAOImpl;
import com.example.glproject.DAOImpl.PlaneDAOImpl;
import com.example.glproject.DAOImpl.StaffDAOImpl;
import com.example.glproject.DAOImpl.TaskDAOImpl;

public class DAOFactory {

	private DAOFactory() {
	}

	private static class DAOHolder {
		private static final FlightDAO FLIGHT_DAO = new FlightDAOImpl();
		private static final GenericTaskDAO GENERIC_TASK_DAO = new GenericTaskDAOImpl();
		private static final MRODao MRO_DAO = new MRODAOImpl();
		private static final PlaneDAO PLANE_DAO = new PlaneDAOImpl();
		private static final TaskDAO TASK_DAO = new TaskDAOImpl();
		private static final StaffDAO STAFF_DAO = new StaffDAOImpl();
	}

	public static FlightDAO getFlightDAO() {
		return DAOHolder.FLIGHT_DAO;
	}

	public static GenericTaskDAO getGenericTaskDAO() {
		return DAOHolder.GENERIC_TASK_DAO;
	}

	public static MRODao getMroDAO() {
		return DAOHolder.MRO_DAO;
	}

	public static PlaneDAO getPlaneDAO() {
		return DAOHolder.PLANE_DAO;
	}

	public static TaskDAO getTaskDAO() {
		return DAOHolder.TASK_DAO;
	}

	public static StaffDAO getStaffDAO() {
		return DAOHolder.STAFF_DAO;
	}
}
