package com.example.glproject.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/planes")
public class PlaneResource {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getPlanes() {
		return "all the planes";
	}

	@GET
	@Path("/{type}")
	@Produces(MediaType.TEXT_PLAIN)
	public String getMPD(@PathParam("type") String type) {
		return "mpd";
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String createPlane(String s) {
		return "flight created";
	}
}
