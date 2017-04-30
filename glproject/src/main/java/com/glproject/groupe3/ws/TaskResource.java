package com.glproject.groupe3.ws;

import com.glproject.groupe3.DAO.AbstractDAOFactory;
import com.glproject.groupe3.DAO.Factory;
import com.glproject.groupe3.DAOImpl.TaskDAOImpl;
import com.glproject.groupe3.businessobjects.Task;
import com.glproject.groupe3.util.Constants;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

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
	@Path("{plane_id}/{idTask}")
	@Produces(MediaType.APPLICATION_JSON)
	public Task getTask(@PathParam("idTask") long id) {
		return ((TaskDAOImpl) AbstractDAOFactory.getFactory(Factory.ES_DAO_FACTORY).getTaskDAO()).get(Constants.TASKS,
				String.valueOf(id));
	}
}
