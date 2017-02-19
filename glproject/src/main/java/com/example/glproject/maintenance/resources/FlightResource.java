package com.example.glproject.maintenance.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/flights")
public class FlightResource {

	@GET
	@Produces(MediaType.TEXT_PLAIN) // pour l'instant, après ca sera JSON_APP
	public String getFlights() {
		return "all the flights";
	}

	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	public String createFlight(String s /* Flight f */) {
		return "flight created";
	}
    ;; ; 
}
