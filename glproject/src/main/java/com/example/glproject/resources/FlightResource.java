package com.example.glproject.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.example.glproject.DAO.DAOFactory;
import com.example.glproject.businessobjects.Flight;

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