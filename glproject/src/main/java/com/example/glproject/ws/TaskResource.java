package com.example.glproject.ws;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.example.glproject.DAO.AbstractDAOFactory;
import com.example.glproject.DAO.Factory;
import com.example.glproject.DAOImpl.TaskDAOImpl;
import com.example.glproject.businessobjects.Task;

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

	// @PUT
	// @Consumes(MediaType.APPLICATION_JSON)
	// public void addFlight(Flight flight) {
	// ((FlightDAOImpl)
	// AbstractDAOFactory.getFactory(Factory.ES_DAO_FACTORY).getFlightDAO()).add(flight,
	// "flights",
	// flight.getCommercial());
	// }
}
