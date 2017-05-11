package com.glproject.groupe3.ws;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.elasticsearch.action.update.UpdateRequest;

import com.fasterxml.jackson.core.JsonGenerationException;
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
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Task getTask(@PathParam("id") long id) {
		return ((TaskDAOImpl) AbstractDAOFactory.getFactory(Factory.ES_DAO_FACTORY).getTaskDAO()).get(Constants.TASKS,
				String.valueOf(id));
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void addTask(Task task) {
		((TaskDAOImpl) AbstractDAOFactory.getFactory(Factory.ES_DAO_FACTORY).getTaskDAO()).add(task, Constants.TASKS,
				String.valueOf(task.getId()));
	}

	@POST
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Task updateTask(@PathParam("id") long id, Task task) {
		ObjectMapper mapper = new ObjectMapper();
		String json = null;

		UpdateRequest ur = new UpdateRequest();
		ur.index(Constants.GL);
		ur.type(Constants.TASKS);
		ur.id(String.valueOf(task.getId()));

		try {
			json = mapper.writeValueAsString(task);
			System.out.println(json);

		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		ur.doc(json);

		((TaskDAOImpl) AbstractDAOFactory.getFactory(Factory.ES_DAO_FACTORY).getTaskDAO()).update(ur);

		return task;
	}

	@DELETE
	@Path("/{id}")
	public void deleteTask(@PathParam("id") long id) {
		((TaskDAOImpl) AbstractDAOFactory.getFactory(Factory.ES_DAO_FACTORY).getTaskDAO()).delete(Constants.TASKS,
				String.valueOf(id));
	}
}