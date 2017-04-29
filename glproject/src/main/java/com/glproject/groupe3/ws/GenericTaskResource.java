package com.glproject.groupe3.ws;

import java.io.InputStream;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.multipart.FormDataParam;

import com.glproject.groupe3.DAO.AbstractDAOFactory;
import com.glproject.groupe3.DAO.Factory;
import com.glproject.groupe3.DAOImpl.GenericTaskDAOImpl;
import com.glproject.groupe3.businessobjects.GenericTask;
import com.glproject.groupe3.util.Constants;
import com.glproject.groupe3.util.SpreadsheetParser;

@Path("/" + Constants.GENERIC_TASKS)
public class GenericTaskResource {

	@PUT
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public void importMPD(@FormDataParam("file") InputStream input) {
		new SpreadsheetParser().importFile(input);
	}

	@GET
	@Path("/{taskNumber}")
	@Produces(MediaType.APPLICATION_JSON)
	public GenericTask getGenericTask(@PathParam("taskNumber") String taskNumber) {
		return ((GenericTaskDAOImpl) AbstractDAOFactory.getFactory(Factory.ES_DAO_FACTORY).getGenericTaskDAO())
				.get(Constants.GENERIC_TASKS, String.valueOf(taskNumber));
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<GenericTask> getGenericTasks() {
		return ((GenericTaskDAOImpl) AbstractDAOFactory.getFactory(Factory.ES_DAO_FACTORY).getGenericTaskDAO())
				.getAll(Constants.GENERIC_TASKS);
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void addGenericTask(GenericTask genericTask) {
		((GenericTaskDAOImpl) AbstractDAOFactory.getFactory(Factory.ES_DAO_FACTORY).getGenericTaskDAO())
				.add(genericTask, Constants.GENERIC_TASKS, String.valueOf(genericTask.getTaskNumber()));
	}

//	@GET
//	@Path("/{typeOfGenericTask}")
//	@Produces(MediaType.APPLICATION_JSON)
//	public List<GenericTask> getMPDByTypeOfPlane(@PathParam("taskNumber") String typeOfGenericTask) {
//		return ((GenericTaskDAOImpl) AbstractDAOFactory.getFactory(Factory.ES_DAO_FACTORY).getGenericTaskDAO())
//				.getMPDByTypeOfPlane(typeOfGenericTask);
//	}
}