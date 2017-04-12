package com.glproject.groupe3.ws;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.glproject.groupe3.DAO.AbstractDAOFactory;
import com.glproject.groupe3.DAO.Factory;
import com.glproject.groupe3.DAOImpl.PlaneDAOImpl;
import com.glproject.groupe3.businessobjects.Plane;

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
		return ((PlaneDAOImpl) AbstractDAOFactory.getFactory(Factory.ES_DAO_FACTORY).getPlaneDAO()).get("planes",
				String.valueOf(id));
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void addPlane(Plane plane) {
		((PlaneDAOImpl) AbstractDAOFactory.getFactory(Factory.ES_DAO_FACTORY).getPlaneDAO()).add(plane, "planes",
				String.valueOf(plane.getId()));
	}

	@DELETE
	@Path("/{id}")
	public void deletePlane(@PathParam("id") long id) {
		((PlaneDAOImpl) AbstractDAOFactory.getFactory(Factory.ES_DAO_FACTORY).getPlaneDAO()).delete("planes",
				String.valueOf(id));
	}
}