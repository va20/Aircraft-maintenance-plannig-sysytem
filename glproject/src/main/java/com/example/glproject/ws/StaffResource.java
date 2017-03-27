package com.example.glproject.resources;

import com.example.glproject.DAO.DAOFactory;
import com.example.glproject.businessobjects.Staff;
import org.apache.commons.codec.digest.DigestUtils;
import org.eclipse.jetty.util.security.Credential;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/staff")
public class StaffResource {

	@POST
	@Path("/{login}/{password}")
	public boolean signIn(@PathParam("login") String login, @PathParam("password") String password) {
		byte[] hashedPasword = DigestUtils.sha512(password);

		Staff staff = DAOFactory.getStaffDAO().isValid(login, hashedPassword);
		if (s.getPassword().equals(password)) {
				return true;
			}

		return false;
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void addStaff(Staff s) {
		DAOFactory.getStaffDAO().addStaff(s);
	}
	
	@DELETE
	public void deleteStaffs() {
		DAOFactory.getStaffDAO().deleteStaffs();
	}
}
