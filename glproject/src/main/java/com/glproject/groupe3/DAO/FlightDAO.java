package com.glproject.groupe3.DAO;

import java.util.List;

import org.joda.time.DateTime;

import com.glproject.groupe3.businessobjects.Flight;

public interface FlightDAO extends DAO<Flight> {

	void update(Flight flight);

	List<Flight> getByDayArr(DateTime day);

	List<Flight> getByDayDep(DateTime day);
}
