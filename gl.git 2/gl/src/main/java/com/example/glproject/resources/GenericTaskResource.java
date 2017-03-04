package com.example.glproject.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/generictasks")
public class GenericTaskResource {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getGenericTasks() {
		return "all the tasks";
	}

	@GET
	@Path("/{tasknumber}")
	@Produces(MediaType.TEXT_PLAIN)
	public String getMPD(@PathParam("tasknumber") String type) {
		return "generic task";
	}
}
