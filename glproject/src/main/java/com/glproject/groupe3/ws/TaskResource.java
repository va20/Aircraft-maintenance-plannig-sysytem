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
import com.glproject.groupe3.DAOImpl.TaskDAOImpl;
import com.glproject.groupe3.businessobjects.Task;
import com.glproject.groupe3.util.Constants;

@Path("/" + Constants.TASKS)
public class TaskResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Task> getTasks() {
		return ((TaskDAOImpl) AbstractDAOFactory.getFactory(Factory.ES_DAO_FACTORY).getTaskDAO())
				.getAll(Constants.TASKS);
	}

	@GET
	@Path("/{idTask}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Task> getTasksByTask(@PathParam("idTask") long idTask) {
		return ((TaskDAOImpl) AbstractDAOFactory.getFactory(Factory.ES_DAO_FACTORY).getTaskDAO())
				.getTasksByPlane(String.valueOf(idTask), Constants.TASKS);
	}

	@DELETE
	@Path("/{id}")
	public void deleteTask(@PathParam("id") long id) {
		((TaskDAOImpl) AbstractDAOFactory.getFactory(Factory.ES_DAO_FACTORY).getTaskDAO()).delete(Constants.TASKS,
				String.valueOf(id));
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void addTask(Task task) {
		((TaskDAOImpl) AbstractDAOFactory.getFactory(Factory.ES_DAO_FACTORY).getTaskDAO()).add(task, Constants.TASKS,
				String.valueOf(task.getId()));
	}

	@GET
	@Path("/{idPlane}/{idTask}")
	@Produces(MediaType.APPLICATION_JSON)
	public Task getTask(@PathParam("idTask") long id) {
		return ((TaskDAOImpl) AbstractDAOFactory.getFactory(Factory.ES_DAO_FACTORY).getTaskDAO()).get(Constants.TASKS,
				String.valueOf(id));
	}
}
