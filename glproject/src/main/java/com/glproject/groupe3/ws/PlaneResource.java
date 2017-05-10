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
import com.glproject.groupe3.DAOImpl.GenericTaskDAOImpl;
import com.glproject.groupe3.DAOImpl.PlaneDAOImpl;
import com.glproject.groupe3.DAOImpl.TaskDAOImpl;
import com.glproject.groupe3.businessobjects.GenericTask;
import com.glproject.groupe3.businessobjects.Plane;
import com.glproject.groupe3.businessobjects.Task;
import com.glproject.groupe3.util.Constants;

@Path("/" + Constants.PLANES)
public class PlaneResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Plane> getPlanes() {
		return ((PlaneDAOImpl) AbstractDAOFactory.getFactory(Factory.ES_DAO_FACTORY).getPlaneDAO())
				.getAll(Constants.PLANES);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Plane getPlane(@PathParam("id") long id) {
		return ((PlaneDAOImpl) AbstractDAOFactory.getFactory(Factory.ES_DAO_FACTORY).getPlaneDAO())
				.get(Constants.PLANES, String.valueOf(id));
	}

	@GET
	@Path("/{type}/mpd")
	@Produces(MediaType.APPLICATION_JSON)
	public List<GenericTask> getMPDByTypeOfPlane(@PathParam("type") String type) {
		return ((GenericTaskDAOImpl) AbstractDAOFactory.getFactory(Factory.ES_DAO_FACTORY).getGenericTaskDAO())
				.getMPDByTypeOfPlane(type);
	}

	@GET
	@Path("/{id}/tasks")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Task> getTasksByPlane(@PathParam("id") long id) {
		return ((TaskDAOImpl) AbstractDAOFactory.getFactory(Factory.ES_DAO_FACTORY).getTaskDAO())
				.getTasksByPlane(String.valueOf(id));
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void addPlane(Plane plane) {
		((PlaneDAOImpl) AbstractDAOFactory.getFactory(Factory.ES_DAO_FACTORY).getPlaneDAO()).add(plane,
				Constants.PLANES, String.valueOf(plane.getId()));
	}

	@DELETE
	@Path("/{id}")
	public void deletePlane(@PathParam("id") long id) {
		((PlaneDAOImpl) AbstractDAOFactory.getFactory(Factory.ES_DAO_FACTORY).getPlaneDAO()).delete(Constants.PLANES,
				String.valueOf(id));
	}
}