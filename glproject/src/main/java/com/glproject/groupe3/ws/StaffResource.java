package com.glproject.groupe3.ws;

import com.glproject.groupe3.DAO.AbstractDAOFactory;
import com.glproject.groupe3.DAO.Factory;
import com.glproject.groupe3.DAOImpl.StaffDAOImpl;
import com.glproject.groupe3.businessobjects.Staff;
import com.glproject.groupe3.util.Constants;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/" + Constants.STAFFS)
public class StaffResource {

	@POST
	@Path("/{login}/{password}")
	public boolean signIn(@PathParam("login") String login, @PathParam("password") String password) {
		System.out.println(password);
		List<Staff> staffs = ((StaffDAOImpl) AbstractDAOFactory.getFactory(Factory.ES_DAO_FACTORY).getStaffDAO())
				.getAll(Constants.STAFFS);

		for (Staff s : staffs) {
			System.out.println(s.toString());
			if (s.getLogin().equals(login)) {
				String hash_password = s.hashPass(password + s.getSalt());
				if (s.getPassword().equals(hash_password)) {
					return true;
				}
			}
		}

		return false;
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void addStaff(Staff s) {
		((StaffDAOImpl) AbstractDAOFactory.getFactory(Factory.ES_DAO_FACTORY).getStaffDAO()).add(s, Constants.STAFFS,
				String.valueOf(s.getId()));
	}

	@DELETE
	@Path("/{id}")
	public void deleteStaffs(@PathParam("id") long id) {
		((StaffDAOImpl) AbstractDAOFactory.getFactory(Factory.ES_DAO_FACTORY).getStaffDAO()).delete(Constants.STAFFS,
				String.valueOf(id));
	}
}