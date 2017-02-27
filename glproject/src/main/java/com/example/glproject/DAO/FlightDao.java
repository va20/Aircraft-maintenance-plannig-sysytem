package com.example.glproject.DAO;

import java.util.Date;
import java.util.List;

import com.example.glproject.businessobjects.Flight;

/**
 * Created by info on 20/02/17.
 */
public interface FlightDao {
	boolean add(Flight flight);

	boolean update(Flight flight);

	boolean delete(Flight flight);

	List<Flight> getListFlight();// lists of flight

	List<Flight> getByDay(Date day);// Returns list of flights corresponding to
									// a "day"
}
