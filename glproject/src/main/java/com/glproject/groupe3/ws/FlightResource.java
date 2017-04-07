package com.glproject.groupe3.ws;

import com.glproject.groupe3.DAO.AbstractDAOFactory;
import com.glproject.groupe3.DAO.Factory;
import com.glproject.groupe3.DAOImpl.FlightDAOImpl;
import com.glproject.groupe3.businessobjects.Flight;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/flights")
public class FlightResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Flight> getFlights() {
		return ((FlightDAOImpl) AbstractDAOFactory.getFactory(Factory.ES_DAO_FACTORY).getFlightDAO()).getAll("flights");
	}

	@GET
	@Path("/{commercial}")
	@Produces(MediaType.APPLICATION_JSON)
	public Flight getFlight(@PathParam("commercial") String commercial) {
		return ((FlightDAOImpl) AbstractDAOFactory.getFactory(Factory.ES_DAO_FACTORY).getFlightDAO()).get("flights",
				commercial);

	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void addFlight(Flight flight) {
		((FlightDAOImpl) AbstractDAOFactory.getFactory(Factory.ES_DAO_FACTORY).getFlightDAO()).add(flight, "flights",
				flight.getCommercial());
	}


	@DELETE
	@Path("/{commercial}")
	public void deletePlane(@PathParam("commercial") String commercial) {
		((FlightDAOImpl) AbstractDAOFactory.getFactory(Factory.ES_DAO_FACTORY).getPlaneDAO()).delete("flights",
				commercial);
	}
}