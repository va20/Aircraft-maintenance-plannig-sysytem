package com.example.glproject.ws;

import com.example.glproject.DAO.AbstractDAOFactory;
import com.example.glproject.DAO.Factory;
import com.example.glproject.DAOImpl.PlaneDAOImpl;
import com.example.glproject.businessobjects.Plane;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/planes")
public class PlaneResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Plane> getPlanes() {
		return ((PlaneDAOImpl) AbstractDAOFactory.getFactory(Factory.ES_DAO_FACTORY).getPlaneDAO()).getAll("planes");
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Plane getPlane(@PathParam("id") long id) {
		return ((PlaneDAOImpl) AbstractDAOFactory.getFactory(Factory.ES_DAO_FACTORY).getPlaneDAO()).get(id,"planes");

	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void addPlane(Plane plane) {
		((PlaneDAOImpl) AbstractDAOFactory.getFactory(Factory.ES_DAO_FACTORY).getPlaneDAO()).add(plane,"planes");

	}
}
