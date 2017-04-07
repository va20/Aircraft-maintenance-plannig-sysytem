package com.glproject.groupe3.main;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import com.glproject.groupe3.DAO.AbstractDAOFactory;
import com.glproject.groupe3.DAO.Factory;
import com.glproject.groupe3.DAOImpl.FlightDAOImpl;
import com.glproject.groupe3.DAOImpl.GenericTaskDAOImpl;
import com.glproject.groupe3.DAOImpl.PlaneDAOImpl;
import com.glproject.groupe3.DAOImpl.StaffDAOImpl;
import com.glproject.groupe3.DAOImpl.TaskDAOImpl;
import com.glproject.groupe3.businessobjects.Flight;
import com.glproject.groupe3.businessobjects.GenericTask;
import com.glproject.groupe3.businessobjects.MRO;
import com.glproject.groupe3.businessobjects.Plane;
import com.glproject.groupe3.businessobjects.Staff;
import com.glproject.groupe3.businessobjects.Task;

public class FillDatabase {

	public static void fill() throws NoSuchAlgorithmException {
		addStaffs();
		addPlanes();
		addFlights();
		addGenericTasks();
		addTasks();
	}

	public static void deleteAll() {
		((StaffDAOImpl) AbstractDAOFactory.getFactory(Factory.ES_DAO_FACTORY).getStaffDAO()).deleteAll("staffs");
		((FlightDAOImpl) AbstractDAOFactory.getFactory(Factory.ES_DAO_FACTORY).getFlightDAO()).deleteAll("flights");
		((PlaneDAOImpl) AbstractDAOFactory.getFactory(Factory.ES_DAO_FACTORY).getPlaneDAO()).deleteAll("planes");
	}

	public static void addStaffs() throws NoSuchAlgorithmException {
		List<Staff> list = new ArrayList<Staff>();
		Staff staff1 = new Staff(1, "chris", "dionisio", "chris", "pwd");
		Staff staff2 = new Staff(2, "ptitprince", "ali", "ali", "pwd");
		Staff staff3 = new Staff(3, "sisi", "sisi", "sisi", "pwd");
		Staff staff4 = new Staff(4, "saif", "al-qazweny", "saif", "pwd");
		Staff staff5 = new Staff(5, "hugues", "le lann", "ug", "pwd");
		Staff staff6 = new Staff(6, "wang", "pin", "wang", "pwd");
		Staff staff7 = new Staff(7, "vianel", "ebourefe", "vianel", "pwd");
		Staff staff8 = new Staff(8, "arnaud", "duhamel", "arnaud", "pwd");
		Staff staff9 = new Staff(9, "rayane", "mouaatarif", "rayane", "pwd");

		list.add(staff1);
		list.add(staff2);
		list.add(staff3);
		list.add(staff4);
		list.add(staff5);
		list.add(staff6);
		list.add(staff7);
		list.add(staff8);
		list.add(staff9);

		for (int i = 0; i < 9; i++) {
			((StaffDAOImpl) AbstractDAOFactory.getFactory(Factory.ES_DAO_FACTORY).getStaffDAO()).add(list.get(i),
					"staffs", String.valueOf(list.get(i).getId()));
		}
	}

	public static void addPlanes() {
		List<Plane> list = new ArrayList<Plane>();
		Plane plane1 = new Plane(1, "Airbus A320", "N3840F");
		Plane plane2 = new Plane(2, "Airbus A320", "A3810B");
		Plane plane3 = new Plane(3, "Boeing 747", "D384F");
		Plane plane4 = new Plane(4, "Boeing 747", "S9899A");
		Plane plane5 = new Plane(5, "MD-80", "QS85AR");
		Plane plane6 = new Plane(6, "Boeing 777", "A565II");

		list.add(plane1);
		list.add(plane2);
		list.add(plane3);
		list.add(plane4);
		list.add(plane5);
		list.add(plane6);

		for (int i = 0; i < 6; i++) {
			((PlaneDAOImpl) AbstractDAOFactory.getFactory(Factory.ES_DAO_FACTORY).getPlaneDAO()).add(list.get(i),
					"planes", String.valueOf(list.get(i).getId()));
		}
	}

	public static void addFlights() {
		List<Flight> list = new ArrayList<Flight>();
		Flight flight1 = new Flight(1, "TVA1234", "LFPA", "LFPO", new DateTime(), new DateTime().plusHours(3));
		Flight flight2 = new Flight(2, "GVF1224", "AGPW", "LFPA", new DateTime(), new DateTime().plusHours(5));
		Flight flight3 = new Flight(3, "TVH1234", "LPPO", "MLPO", new DateTime(), new DateTime().plusHours(4));
		Flight flight4 = new Flight(4, "TVF1439", "LFPO", "LPPO", new DateTime(), new DateTime().plusHours(12));
		Flight flight5 = new Flight(5, "PVI1234", "MLPO", "AGPW", new DateTime(), new DateTime().plusHours(2));
		Flight flight6 = new Flight(6, "TSF1214", "LFPA", "LKPO", new DateTime(), new DateTime().plusHours(6));

		list.add(flight1);
		list.add(flight2);
		list.add(flight3);
		list.add(flight4);
		list.add(flight5);
		list.add(flight6);

		for (int i = 0; i < 6; i++) {
			((FlightDAOImpl) AbstractDAOFactory.getFactory(Factory.ES_DAO_FACTORY).getFlightDAO()).add(list.get(i),
					"flights", String.valueOf(list.get(i).getCommercial()));
		}
	}

	public static void addGenericTasks() {
		List<GenericTask> list = new ArrayList<GenericTask>();
		GenericTask gt1 = new GenericTask("12-33-02-001", "Airbus A320", 131, "Aileron Centering Mechanism Assembly",
				100, false, 2, "Airbus A320");
		GenericTask gt2 = new GenericTask("12-33-02-002", "Airbus A320", 454,
				"Quadrant - Spoiler Control, Shaft Assembly", 20, true, 2, "Airbus A320");

		list.add(gt1);
		list.add(gt2);

		for (int i = 0; i < 2; i++) {
			((GenericTaskDAOImpl) AbstractDAOFactory.getFactory(Factory.ES_DAO_FACTORY).getGenericTaskDAO())
					.add(list.get(i), "mpd", String.valueOf(list.get(i).getTaskNumber()));
		}
	}

	public static void addTasks() {
		List<Task> list = new ArrayList<Task>();
		Task t1 = new Task(1, 2, 1, new DateTime(), "12-33-02-001");
		Task t2 = new Task(2, 2, 1, new DateTime(), "12-33-02-002");

		list.add(t1);
		list.add(t2);

		for (int i = 0; i < 2; i++) {
			((TaskDAOImpl) AbstractDAOFactory.getFactory(Factory.ES_DAO_FACTORY).getTaskDAO()).add(list.get(i), "tasks",
					String.valueOf(list.get(i).getId()));
		}
	}

	public static void addMROs() {
		List<MRO> list = new ArrayList<MRO>();
		// MRO m1 = new MRO(1, );
	}
}