package com.example.glproject.DAO;

import java.util.List;

import org.joda.time.DateTime;

import com.example.glproject.businessobjects.Flight;

public interface FlightDAO extends DAO<Flight> {

	void update(Flight flight);

	List<Flight> getByDayArr(DateTime day);

	List<Flight> getByDayDep(DateTime day);
}
