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
import com.example.glproject.businessobjects.Plane;

@Path("/planes")
public class PlaneResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Plane> getPlanes() {
		return DAOFactory.getPlaneDAO().getPlanes();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Plane getPlane(@PathParam("id") long id) {
		return DAOFactory.getPlaneDAO().getPlane(id);
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void addPlane(Plane plane) {
		DAOFactory.getPlaneDAO().add(plane);
	}
}
