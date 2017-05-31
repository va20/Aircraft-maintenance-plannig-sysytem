package com.glproject.groupe3.main;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import com.glproject.groupe3.DAO.AbstractDAOFactory;
import com.glproject.groupe3.DAO.Factory;
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
import com.glproject.groupe3.businessobjects.Task.Status;
import com.glproject.groupe3.businessobjects.Task.Warning;
import com.glproject.groupe3.util.Constants;

public class FillDatabase {

	public static void fill() throws NoSuchAlgorithmException {
		addStaffs();
		addPlanes();
		addFlights();
		addGenericTasks();
		addTasks();
		addMROs();
	}

	public static void deleteAll() {
		((StaffDAOImpl) AbstractDAOFactory.getFactory(Factory.ES_DAO_FACTORY).getStaffDAO()).deleteAll("staffs");
		((FlightDAOImpl) AbstractDAOFactory.getFactory(Factory.ES_DAO_FACTORY).getFlightDAO()).deleteAll("flights");
		((PlaneDAOImpl) AbstractDAOFactory.getFactory(Factory.ES_DAO_FACTORY).getPlaneDAO()).deleteAll("planes");
	}

	public static void addStaffs() throws NoSuchAlgorithmException {
		List<Staff> list = new ArrayList<Staff>();
		Staff staff1 = new Staff("chris", "dionisio", "chris", "pwd");
		Staff staff2 = new Staff("sisi", "sisi", "sisi", "pwd");
		Staff staff3 = new Staff("MCC", "mcc", "mcc", "pwd");

		list.add(staff1);
		list.add(staff2);
		list.add(staff3);

		for (int i = 0; i < list.size(); i++) {
			((StaffDAOImpl) AbstractDAOFactory.getFactory(Factory.ES_DAO_FACTORY).getStaffDAO()).add(list.get(i),
					Constants.STAFFS, list.get(i).getLogin());
		}
	}

	public static void addPlanes() {
		List<Plane> list = new ArrayList<Plane>();
		Plane plane1 = new Plane(1, "Airbus A320", "N3840F");
		Plane plane2 = new Plane(2, "Airbus A320", "A3810B");
		Plane plane3 = new Plane(3, "Boeing 747", "D384F");
		Plane plane4 = new Plane(4, "Boeing 747", "S9899A");
		Plane plane5 = new Plane(5, "MD-80", "QS85AR");
		Plane plane6 = new Plane(6, "Airbus A335-600", "FF649I");
		Plane plane7 = new Plane(7, "Boeing 787", "Z56IFA");
		Plane plane8 = new Plane(8, "Boeing 777", "PM484K");
		Plane plane9 = new Plane(9, "MD-8", "PA787H");
		Plane plane10 = new Plane(11, "Fokker 100", "QQ984K");
		Plane plane12 = new Plane(10, "Boeing 747", "PC7830A");

		list.add(plane1);
		list.add(plane2);
		list.add(plane3);
		list.add(plane4);
		list.add(plane5);
		list.add(plane6);
		list.add(plane7);
		list.add(plane8);
		list.add(plane9);
		list.add(plane10);
		list.add(plane12);

		for (int i = 0; i < list.size(); i++) {
			((PlaneDAOImpl) AbstractDAOFactory.getFactory(Factory.ES_DAO_FACTORY).getPlaneDAO()).add(list.get(i),
					Constants.PLANES, String.valueOf(list.get(i).getId()));
		}
	}

	public static void addFlights() {
		List<Flight> list = new ArrayList<Flight>();
		Flight flight1 = new Flight(7, "TVA1234", "LFPA", "LFPO", new DateTime(), new DateTime().plusHours(3));
		Flight flight2 = new Flight(8, "GVF1224", "AGPW", "LFPA", new DateTime().plusSeconds(90),
				new DateTime().plusHours(5));
		Flight flight3 = new Flight(7, "TVH1234", "LPPO", "MLPO", new DateTime(),
				new DateTime().plusHours(4).plusMinutes(45));
		Flight flight4 = new Flight(9, "TVF1439", "LFPO", "LPPO", new DateTime(), new DateTime().plusHours(12));
//		Flight flight5 = new Flight(10, "PVI1234", "MLPO", "AGPW", new DateTime(),
//				new DateTime().plusHours(2).plusMinutes(30));
		Flight flight6 = new Flight(11, "TSF1214", "LFPA", "LKPO", new DateTime(), new DateTime().plusHours(6));
		Flight flight7 = new Flight(4, "APW4778", "MLPO", "AKPW", new DateTime().plusHours(9).plusMinutes(4),
				new DateTime().plusHours(10).plusMinutes(50));
		Flight flight8 = new Flight(2, "MAQ7844", "PALW", "SIOZ", new DateTime().plusSeconds(1900),
				new DateTime().plusHours(6));
		Flight flight9 = new Flight(10, "LOA6314", "WWAQ", "VZDE", new DateTime().plusSeconds(160),
				new DateTime().plusHours(4).plusMinutes(12));

		list.add(flight1);
		list.add(flight2);
		list.add(flight3);
		list.add(flight4);
//		list.add(flight5);
		list.add(flight6);
		list.add(flight7);
		list.add(flight8);
		list.add(flight9);

		for (int i = 0; i < list.size(); i++) {
			((FlightDAOImpl) AbstractDAOFactory.getFactory(Factory.ES_DAO_FACTORY).getFlightDAO()).add(list.get(i),
					Constants.FLIGHTS, String.valueOf(list.get(i).getCommercial()));
		}
	}

	public static void addGenericTasks() {
		List<GenericTask> list = new ArrayList<GenericTask>();
		GenericTask gt1 = new GenericTask("300001-01-1", "Boeing 747", 571,
				"HIRF/LIGHTNING PROTECTION-WING, CHECK BONDING MEASUREMENT OF CLAMPS WITH A LOOP MRB REFERENCE : TESTER FROM FUSELAGE TO RIB 9",
				null, "truee", 1, "PRE 24943, (57-1086)");
		GenericTask gt2 = new GenericTask("300001-02-1", "Boeing 747", 571,
				"CHECK THAT THE CONNECTOR IS CORRECTLY TIGHTENED BY MEASUREMENT OF THE RESISTANCE WITH A LOOP TESTER: - 4005VC, 4007VC, 4009VC, 4011VC, LH SIDE - 4013VC, 4015VC, 4017VC, 4019VC, LH SIDE - 4006VC, 4008VC, 4010VC, 4012VC, RH SIDE - 4014VC, 4016VC, 4018VC, 4020VC, RH SIDE - ACCESS: 521EB 521GB 522AB",
				null, "truee", 1, "ALL");
		GenericTask gt3 = new GenericTask("300002-01-1", "Boeing 747", 334,
				"GENERAL VISUAL INSPECTION OF ELEMENTS FITTED ON THE FOLLOWING HARNESSES: - 405VB, 406VB, 407VB, 408VB, 409VB, LH SIDE - - 400VB, 401VB, 402VB, 403VB, 404VB, RH SIDE - ACCESS: 334AB 334BB 334CB 334DB 334EB",
				null, "truee", 1, "ALL");
		GenericTask gt4 = new GenericTask("300003-01-1", "Boeing 747", 321,
				"HIRF/LIGHTNING PROTECTION-VERTICAL FIN, GENERAL VISUAL INSPECTION OF ELEMENTS FITTED ON THE FOLLOWING HARNESSES: - 4202VB, 4204VB - ACCESS: 321AL",
				null, "truee", 1, "ALL");
		GenericTask gt5 = new GenericTask("300003-03-1", "Boeing 747", 324,
				"HIRF/LIGHTNING PROTECTION-VERTICAL FIN, GENERAL VISUAL INSPECTION OF ELEMENTS FITTED ON THE FOLLOWING HARNESSES: - 4202VB, 4204VB - ACCESS: 324AT",
				null, "truee", 1, "ALL");

		list.add(gt1);
		list.add(gt2);
		list.add(gt3);
		list.add(gt4);
		list.add(gt5);

		for (int i = 0; i < 5; i++) {
			((GenericTaskDAOImpl) AbstractDAOFactory.getFactory(Factory.ES_DAO_FACTORY).getGenericTaskDAO())
					.add(list.get(i), Constants.GENERIC_TASKS, String.valueOf(list.get(i).getTaskNumber()));
		}
	}

	public static void addTasks() {
		List<Task> list = new ArrayList<Task>();
		Task t1 = new Task(5154128, 3, "mro0", new DateTime().plusHours(2), "300001-01-1", "base", Status.NDONE,
				Warning.NONE);
		Task t2 = new Task(7857577, 3, "mro1", new DateTime().plusHours(2), "300001-02-1", "base", Status.NDONE,
				Warning.NONE);
		Task t3 = new Task(1815125, 4, "mro1", new DateTime().plusHours(2), "300002-01-1", "inline", Status.NDONE,
				Warning.NONE);
		Task t4 = new Task(1658165, 4, "mro2", new DateTime().plusMinutes(78), "300003-01-1", "base", Status.NDONE,
				Warning.NONE);
		Task t5 = new Task(4654655, 3, "mro1", new DateTime().plusDays(3).plusHours(2), "300001-01-1", "inline",
				Status.NDONE, Warning.NONE);
		Task t6 = new Task(7857866, 4, "mro2", new DateTime().plusMinutes(45), "300002-01-1", "inline", Status.NDONE,
				Warning.NONE);
		Task t7 = new Task(2452872, 3, "mro3", new DateTime().plusDays(1).plusHours(6), "300003-03-1", "base",
				Status.NDONE, Warning.NONE);
		Task t8 = new Task(7457085, 4, "mro1", new DateTime().plusHours(4).plusMinutes(45), "300001-02-1", "inline",
				Status.NDONE, Warning.NONE);
		Task t9 = new Task(7975545, 4, "mro4", new DateTime().plusMinutes(118), "300003-03-1", "base", Status.NDONE,
				Warning.NONE);
		Task t10 = new Task(1245775, 3, "mro1", new DateTime().plusHours(6), "300003-01-1", "inline", Status.NDONE,
				Warning.NONE);
		Task t11 = new Task(4747475, 10, "mro2", new DateTime().plusSeconds(20), "300003-03-1", "inline", Status.NDONE,
				Warning.NONE);

		list.add(t1);
		list.add(t2);
		list.add(t3);
		list.add(t4);
		list.add(t5);

		list.add(t6);
		list.add(t7);
		list.add(t8);
		list.add(t9);
		list.add(t10);
		list.add(t11);

		for (int i = 0; i < list.size(); i++) {
			((TaskDAOImpl) AbstractDAOFactory.getFactory(Factory.ES_DAO_FACTORY).getTaskDAO()).add(list.get(i),
					Constants.TASKS, String.valueOf(list.get(i).getId()));
		}
	}

	public static void addMROs() throws NoSuchAlgorithmException {
		List<MRO> list = new ArrayList<MRO>();
		MRO mro0 = new MRO("mro0", "pwd", "LFPO", "MRO0");
		MRO mro1 = new MRO("mro1", "pwd", "LFPA", "MRO1");
		MRO mro2 = new MRO("mro2", "pwd", "MLPO", "MRO2");
		MRO mro3 = new MRO("mro3", "pwd", "LFPA", "MRO3");
		MRO mro4 = new MRO("mro4", "pwd", "MLPO", "MRO4");

		list.add(mro0);
		list.add(mro1);
		list.add(mro2);
		list.add(mro3);
		list.add(mro4);

		for (int i = 0; i < list.size(); i++) {
			((MRODAOImpl) AbstractDAOFactory.getFactory(Factory.ES_DAO_FACTORY).getMRODAO()).add(list.get(i),
					Constants.MRO, list.get(i).getLogin());
		}
	}
}