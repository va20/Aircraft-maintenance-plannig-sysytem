package com.glproject.groupe3.ws;

import com.glproject.groupe3.DAO.AbstractDAOFactory;
import com.glproject.groupe3.DAO.Factory;
import com.glproject.groupe3.DAOImpl.FlightDAOImpl;
import com.glproject.groupe3.businessobjects.Flight;
import com.glproject.groupe3.util.Constants;
import com.glproject.groupe3.util.SpreadsheetParser;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.InputStream;
import java.util.List;

@Path("/" + Constants.FLIGHTS)
public class FlightResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Flight> getFlights() {
		return ((FlightDAOImpl) AbstractDAOFactory.getFactory(Factory.ES_DAO_FACTORY).getFlightDAO())
				.getAll(Constants.FLIGHTS);
	}

	@GET
	@Path("/{commercial}")
	@Produces(MediaType.APPLICATION_JSON)
	public Flight getFlight(@PathParam("commercial") String commercial) {
		return ((FlightDAOImpl) AbstractDAOFactory.getFactory(Factory.ES_DAO_FACTORY).getFlightDAO())
				.get(Constants.FLIGHTS, commercial);

	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void addFlight(Flight flight) {
		((FlightDAOImpl) AbstractDAOFactory.getFactory(Factory.ES_DAO_FACTORY).getFlightDAO()).add(flight,
				Constants.FLIGHTS, flight.getCommercial());
	}

	@PUT
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public void importFlights(@FormDataParam("file") InputStream input){
		new SpreadsheetParser().importFlights(input);
	}

	@DELETE
	@Path("/{commercial}")
	public void deletePlane(@PathParam("commercial") String commercial) {
		((FlightDAOImpl) AbstractDAOFactory.getFactory(Factory.ES_DAO_FACTORY).getPlaneDAO()).delete(Constants.FLIGHTS,
				commercial);
	}
}