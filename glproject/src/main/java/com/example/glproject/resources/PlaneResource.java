package com.example.glproject.resources;

import java.util.Date;
import java.util.List;

import javax.ws.rs.Path;

import com.example.glproject.businessobjects.Flight;

@Path("/planes")
public class PlaneResource {

	
	public boolean add(Flight flight) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean update(Flight flight) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean delete(Flight flight) {
		// TODO Auto-generated method stub
		return false;
	}

	public List<Flight> getListFlight() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Flight> getByDay(Date day) {
		// TODO Auto-generated method stub
		return null;
	}

}
