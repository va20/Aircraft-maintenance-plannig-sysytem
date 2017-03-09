package com.example.glproject.DAO;

import java.util.Date;
import java.util.List;

import com.example.glproject.businessobjects.Flight;

public interface FlightDAO {

	Flight getFlight(String commercial);

	List<Flight> getFlights();

	void add(Flight flight);

	List<Flight> getByDay(Date day);
}
