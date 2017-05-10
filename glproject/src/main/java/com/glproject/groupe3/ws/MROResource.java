package com.glproject.groupe3.ws;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.glproject.groupe3.DAO.AbstractDAOFactory;
import com.glproject.groupe3.DAO.Factory;
import com.glproject.groupe3.DAOImpl.MRODAOImpl;
import com.glproject.groupe3.DAOImpl.TaskDAOImpl;
import com.glproject.groupe3.businessobjects.MRO;
import com.glproject.groupe3.businessobjects.Task;
import com.glproject.groupe3.util.Constants;

@Path("/" + Constants.MRO)
public class MROResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<MRO> getMROs() {
		return ((MRODAOImpl) AbstractDAOFactory.getFactory(Factory.ES_DAO_FACTORY).getMRODAO()).getAll(Constants.MRO);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public MRO getMRO(@PathParam("id") long id) {
		return ((MRODAOImpl) AbstractDAOFactory.getFactory(Factory.ES_DAO_FACTORY).getMRODAO()).get(Constants.MRO,
				String.valueOf(id));
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}/tasks")
	public List<Task> getTasksByMRO(@PathParam("id") long id) {
		return ((TaskDAOImpl) AbstractDAOFactory.getFactory(Factory.ES_DAO_FACTORY).getTaskDAO()).getTasksByMRO(id);
	}
}