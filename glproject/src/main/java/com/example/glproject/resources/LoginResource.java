package com.example.glproject.resources;

import java.util.List;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.example.glproject.DAO.DAOFactory;
import com.example.glproject.businessobjects.Staff;

@Path("/login")
public class LoginResource {

	@POST
	@Path("/{login}/{password}")
	public boolean signIn(@PathParam("login") String login, @PathParam("password") String password) {
		List<Staff> staffMembers = DAOFactory.getStaffDAO().getStaffMembers();

		for (Staff s : staffMembers) {
			if (s.getLogin().equals(login) && s.getPassword().equals(password))
				return true;
		}

		return false;
	}
}
