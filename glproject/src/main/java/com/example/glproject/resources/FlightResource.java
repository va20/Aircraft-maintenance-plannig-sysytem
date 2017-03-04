package com.example.glproject.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/flights")
public class FlightResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getFlights() {
		return "all the flights";
	}

	@GET
	@Path("/{commercial}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getFlight(@PathParam("commercial") String commercial) {
		return "commercial";
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String createFlight(String s) {
		return "flight created";
	}
}
