package com.glproject.groupe3.DAO;

import com.glproject.groupe3.DAOImpl.FlightDAOImpl;
import com.glproject.groupe3.DAOImpl.GenericTaskDAOImpl;
import com.glproject.groupe3.DAOImpl.MRODAOImpl;
import com.glproject.groupe3.DAOImpl.PlaneDAOImpl;
import com.glproject.groupe3.DAOImpl.StaffDAOImpl;
import com.glproject.groupe3.DAOImpl.TaskDAOImpl;
import com.glproject.groupe3.businessobjects.Flight;
import com.glproject.groupe3.businessobjects.GenericTask;
import com.glproject.groupe3.businessobjects.MRO;
import com.glproject.groupe3.businessobjects.Plane;
import com.glproject.groupe3.businessobjects.Staff;
import com.glproject.groupe3.businessobjects.Task;

public class DAOFactory extends AbstractDAOFactory {

	private static class DAOHolder {
		private static final FlightDAO FLIGHT_DAO = new FlightDAOImpl(Flight.class);
		private static final PlaneDAO PLANE_DAO = new PlaneDAOImpl(Plane.class);
		private static final GenericTaskDAO GENERIC_TASK_DAO = new GenericTaskDAOImpl(GenericTask.class);
		private static final MRODAO MRO_DAO = new MRODAOImpl(MRO.class);
		private static final TaskDAO TASK_DAO = new TaskDAOImpl(Task.class);
		private static final StaffDAO STAFF_DAO = new StaffDAOImpl(Staff.class);
	}

	public DAO<?> getFlightDAO() {
		return DAOHolder.FLIGHT_DAO;
	}

	@Override
	public DAO<?> getPlaneDAO() {
		return DAOHolder.PLANE_DAO;
	}

	@Override
	public DAO<?> getStaffDAO() {
		return DAOHolder.STAFF_DAO;
	}

	@Override
	public DAO<?> getGenericTaskDAO() {
		return DAOHolder.GENERIC_TASK_DAO;
	}

	@Override
	public DAO<?> getTaskDAO() {
		return DAOHolder.TASK_DAO;
	}

	@Override
	public DAO<?> getMRODAO() {
		return DAOHolder.MRO_DAO;
	}
}