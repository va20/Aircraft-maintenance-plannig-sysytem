package com.glproject.groupe3.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.glproject.groupe3.DAO.AbstractDAOFactory;
import com.glproject.groupe3.DAO.Factory;
import com.glproject.groupe3.DAOImpl.StaffDAOImpl;
import com.glproject.groupe3.businessobjects.Staff;
import com.glproject.groupe3.util.Constants;

@Path("/" + Constants.STAFFS)
public class StaffResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{login}")
	public Staff getStaff(@PathParam("login") String login) {
		return ((StaffDAOImpl) AbstractDAOFactory.getFactory(Factory.ES_DAO_FACTORY).getStaffDAO())
				.get(Constants.STAFFS, String.valueOf(login));
	}

	@POST
	@Path("/{login}/{password}")
	public boolean checkStaff(@PathParam("login") String login, @PathParam("password") String password) {
		Staff s = getStaff(login);
		String hashPassword = Staff.hashPass(password + s.getSalt());

		if (s.getPassword().equals(hashPassword))
			return true;

		return false;
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void addStaff(Staff s) {
		((StaffDAOImpl) AbstractDAOFactory.getFactory(Factory.ES_DAO_FACTORY).getStaffDAO()).add(s, Constants.STAFFS,
				String.valueOf(s.getLogin()));
	}

	@DELETE
	@Path("/{id}")
	public void deleteStaffs(@PathParam("id") long id) {
		((StaffDAOImpl) AbstractDAOFactory.getFactory(Factory.ES_DAO_FACTORY).getStaffDAO()).delete(Constants.STAFFS,
				String.valueOf(id));
	}
}