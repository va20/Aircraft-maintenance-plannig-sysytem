package com.example.glproject.DAO;

import java.util.Date;
import java.util.List;

import com.example.glproject.businessobjects.Flight;

public interface FlightDAO {
	boolean add(Flight flight);

	boolean update(Flight flight);

	boolean delete(Flight flight);

	List<Flight> getListFlight();// lists of flight

	List<Flight> getByDay(Date day);// Returns list of flights corresponding to
									// a "day"
}
