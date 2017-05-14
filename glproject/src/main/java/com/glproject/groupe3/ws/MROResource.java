package com.glproject.groupe3.ws;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
import com.glproject.groupe3.util.Util;

@Path("/" + Constants.MRO)
public class MROResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<MRO> getMROs() {
		return ((MRODAOImpl) AbstractDAOFactory.getFactory(Factory.ES_DAO_FACTORY).getMRODAO()).getAll(Constants.MRO);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{login}")
	public MRO getMRO(@PathParam("login") String login) {
		return ((MRODAOImpl) AbstractDAOFactory.getFactory(Factory.ES_DAO_FACTORY).getMRODAO()).get(Constants.MRO, login);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{login}/tasks")
	public List<Task> getTasksByMRO(@PathParam("login") String login) {
		return ((TaskDAOImpl) AbstractDAOFactory.getFactory(Factory.ES_DAO_FACTORY).getTaskDAO()).getTasksByMRO(login);
	}

	@POST
	@Path("/{login}/{password}")
	@Produces(MediaType.APPLICATION_JSON)
	public MRO checkMRO(@PathParam("login") String login, @PathParam("password") String password) {
		MRO mro = getMRO(login);
		String hashPassword = Util.hashPass(password + mro.getSalt());

		if (mro.getPassword().equals(hashPassword))
			return mro;

		return null;
	}
}