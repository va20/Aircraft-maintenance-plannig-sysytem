package com.example.glproject.ws;

import com.example.glproject.DAO.AbstractDAOFactory;
import com.example.glproject.DAO.Factory;
import com.example.glproject.DAOImpl.StaffDAOImpl;
import com.example.glproject.businessobjects.Staff;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/staff")
public class StaffResource {

	@POST
	@Path("/{login}/{password}")
	public boolean signIn(@PathParam("login") String login, @PathParam("password") String password) {
		List<Staff> staffs = ((StaffDAOImpl) AbstractDAOFactory.getFactory(Factory.ES_DAO_FACTORY).getStaffDAO())
				.getAll("staffs");

		for (Staff s : staffs) {
			System.out.println(s.toString());
			String hash_password = s.hash_pass(password);
			if (s.getLogin().equals(login) && s.getPassword().equals(hash_password)) {
				return true;
			}
		}

		return false;
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void addStaff(Staff s) {
		((StaffDAOImpl) AbstractDAOFactory.getFactory(Factory.ES_DAO_FACTORY).getStaffDAO()).add(s, "staffs",
				String.valueOf(s.getId()));
	}

	@DELETE
	public void deleteStaffs(@PathParam("id") long id) {
		((StaffDAOImpl) AbstractDAOFactory.getFactory(Factory.ES_DAO_FACTORY).getStaffDAO()).delete("staffs",
				String.valueOf(id));
	}
}
