package com.example.glproject.ws;

import com.example.glproject.DAO.AbstractDAOFactory;
import com.example.glproject.DAO.Factory;
import com.example.glproject.DAOImpl.FlightDAOImpl;
import com.example.glproject.businessobjects.Flight;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/flights")
public class FlightResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Flight> getFlights() {
		return ((FlightDAOImpl)AbstractDAOFactory.getFactory(Factory.ES_DAO_FACTORY).getFlightDAO()).getAll("flights");
	}

	@GET
	@Path("/{commercial}")
	@Produces(MediaType.APPLICATION_JSON)
	public Flight getFlight(@PathParam("commercial") String commercial) {
		return ((FlightDAOImpl)AbstractDAOFactory.getFactory(Factory.ES_DAO_FACTORY).getFlightDAO()).getFlight(commercial);

	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void addFlight(Flight flight) {
		((FlightDAOImpl)AbstractDAOFactory.getFactory(Factory.ES_DAO_FACTORY).getFlightDAO()).add(flight,"flights");
	}
}