package com.example.glproject.DAO;

import com.example.glproject.DAOImpl.FlightDAOImpl;
import com.example.glproject.DAOImpl.GenericTaskDAOImpl;
import com.example.glproject.DAOImpl.MRODAOImpl;
import com.example.glproject.DAOImpl.PlaneDAOImpl;
import com.example.glproject.DAOImpl.StaffDAOImpl;
import com.example.glproject.DAOImpl.TaskDAOImpl;
import com.example.glproject.businessobjects.Flight;
import com.example.glproject.businessobjects.GenericTask;
import com.example.glproject.businessobjects.MRO;
import com.example.glproject.businessobjects.Plane;
import com.example.glproject.businessobjects.Staff;
import com.example.glproject.businessobjects.Task;

public class DAOFactory extends AbstractDAOFactory {

	private static class DAOHolder {
		private static final FlightDAO FLIGHT_DAO = new FlightDAOImpl(Flight.class);
		private static final PlaneDAO PLANE_DAO = new PlaneDAOImpl(Plane.class);
		private static final GenericTaskDAO GENERIC_TASK_DAO = new GenericTaskDAOImpl(GenericTask.class);
		private static final MRODAO MRO_DAO = new MRODAOImpl(MRO.class);
		private static final TaskDAO TASK_DAO = new TaskDAOImpl(Task.class);
		private static final StaffDAO STAFF_DAO = new StaffDAOImpl(Staff.class);
	}

	public DAO getFlightDAO() {
		return DAOHolder.FLIGHT_DAO;
	}

	@Override
	public DAO getPlaneDAO() {
		return DAOHolder.PLANE_DAO;
	}

	@Override
	public DAO getStaffDAO() {
		return DAOHolder.STAFF_DAO;
	}

	@Override
	public DAO getGenericTaskDAO() {
		return DAOHolder.GENERIC_TASK_DAO;
	}

	@Override
	public DAO getTaskDAO() {
		return DAOHolder.TASK_DAO;
	}
}
