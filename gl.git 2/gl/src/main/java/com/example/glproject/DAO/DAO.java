package com.example.glproject.DAO;

import com.example.glproject.DAOimpl.FlightDAOimpl;

public class DAO {

	public static FlightDAOimpl getFlightDAO()
	{
		return new FlightDAOimpl();
	}
}
