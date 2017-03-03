package com.example.glproject.DAO;

import com.example.glproject.DAOimpl.FlightDAOimpl;
import com.example.glproject.DAOimpl.GenericTaskDAOimpl;
import com.example.glproject.DAOimpl.MroDAOimpl;
import com.example.glproject.DAOimpl.PlaneDAOimpl;
import com.example.glproject.DAOimpl.TaskDAOimpl;

public class DAO {

	public static FlightDAO getFlightDAO()
	{
		return new FlightDAOimpl();
	}
	public static GenericTaskDAO getGenericTaskDAO()
	{
		return new GenericTaskDAOimpl();
	}
	public static MroDao getMroDAO ()
	{
		return new MroDAOimpl();
	}
	public static PlaneDAO getplaneDAO()
	{
		return new PlaneDAOimpl();
	}
	public static TaskDAO getTaskDAO()
	{
		return new TaskDAOimpl();
	}
}
