package com.glproject.groupe3.ws;

import com.glproject.groupe3.DAO.AbstractDAOFactory;
import com.glproject.groupe3.DAO.Factory;
import com.glproject.groupe3.DAOImpl.TaskDAOImpl;
import com.glproject.groupe3.businessobjects.Task;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/tasks")
public class TaskResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Task> getTasks() {
		return ((TaskDAOImpl) AbstractDAOFactory.getFactory(Factory.ES_DAO_FACTORY).getTaskDAO()).getAll("tasks");
	}

	@GET
	@Path("/{idPlane}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Task> getTasksByPlane(@PathParam("idPlane") long idPlane) {
		return ((TaskDAOImpl) AbstractDAOFactory.getFactory(Factory.ES_DAO_FACTORY).getTaskDAO())
				.getTasksByPlane(String.valueOf(idPlane), "tasks");

	}

	@DELETE
	@Path("/{id}")
	public void deleteTask(@PathParam("id") long id) {
		((TaskDAOImpl) AbstractDAOFactory.getFactory(Factory.ES_DAO_FACTORY).getTaskDAO()).delete("tasks",
				String.valueOf(id));
	}
	// @PUT
	// @Consumes(MediaType.APPLICATION_JSON)
	// public void addFlight(Flight flight) {
	// ((FlightDAOImpl)
	// AbstractDAOFactory.getFactory(Factory.ES_DAO_FACTORY).getFlightDAO()).add(flight,
	// "flights",
	// flight.getCommercial());
	// }
}
