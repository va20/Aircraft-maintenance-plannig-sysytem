package com.example.glproject.resources;

import com.example.glproject.DAO.DAOFactory;
import com.example.glproject.businessobjects.Flight;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/flights")
public class FlightResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Flight> getFlights() {
		return DAOFactory.getFlightDAO().getFlights();
	}

	@GET
	@Path("/{commercial}")
	@Produces(MediaType.APPLICATION_JSON)
	public Flight getFlight(@PathParam("commercial") String commercial) {
		return DAOFactory.getFlightDAO().getFlight(commercial);
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void addFlight(Flight flight) {
		DAOFactory.getFlightDAO().add(flight);
	}
}