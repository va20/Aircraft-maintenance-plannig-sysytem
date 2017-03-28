package com.example.glproject.ws;

import com.example.glproject.DAO.DAOFactory;
import com.example.glproject.businessobjects.Staff;
import org.apache.commons.codec.digest.DigestUtils;
import org.eclipse.jetty.util.security.Credential;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.security.SecureRandom;
import java.util.List;

@Path("/staff")
public class StaffResource {

	@POST
	@Path("/{login}/{password}")
	public boolean signIn(@PathParam("login") String login, @PathParam("password") String password) {
		String hashedPassword= DigestUtils.sha512Hex(password);
		for(int i=0;i<10000;i++){
			hashedPassword=DigestUtils.sha512Hex(hashedPassword);
		}
		Staff staff = DAOFactory.getStaffDAO().isValid(login, hashedPassword);
		if (staff.getPassword().equals(password)) {
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
