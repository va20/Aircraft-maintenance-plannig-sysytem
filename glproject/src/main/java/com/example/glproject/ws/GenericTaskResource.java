package com.example.glproject.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import com.example.glproject.businessobjects.GenericTask;

@Path("/generictasks")
public class GenericTaskResource {

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void addPlane(GenericTask gt) {
		//DAOFactory.getGenericTaskDAO().add(gt);
	}
}
