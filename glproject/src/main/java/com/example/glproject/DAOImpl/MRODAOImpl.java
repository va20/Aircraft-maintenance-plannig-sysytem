package com.example.glproject.DAOImpl;

import com.example.glproject.DAO.DAOImpl;
import com.example.glproject.DAO.MRODAO;
import com.example.glproject.businessobjects.Flight;
import com.example.glproject.businessobjects.MRO;

import java.util.List;

public class MRODAOImpl extends DAOImpl<MRO> implements MRODAO {
	
	public MRODAOImpl(Class<MRO> typeT) {
		super(typeT);
	}

	public void update(MRO mro){
		
	}

	public Flight getFlight(String commercial) {
		return null;
	}

	public List<Flight> getFlights() {
		return null;
	}
}
