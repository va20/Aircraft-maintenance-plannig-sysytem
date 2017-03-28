package com.example.glproject.ws;

import com.example.glproject.DAO.DAOFactory;
import com.example.glproject.businessobjects.Plane;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

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
