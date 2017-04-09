package com.glproject.groupe3.ws;

import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.multipart.FormDataParam;

import com.glproject.groupe3.businessobjects.GenericTask;
import com.glproject.groupe3.parsing.SpreadsheetParser;

@Path("/mpd")
public class GenericTaskResource {

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void addPlane(GenericTask gt) {
		// DAOFactory.getGenericTaskDAO().add(gt);
	}

	@PUT
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public void importMPD(@FormDataParam("file") InputStream input) {
		new SpreadsheetParser().importFile(input);
	}
}
