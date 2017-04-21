package com.glproject.groupe3.ws;

import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.multipart.FormDataParam;

import com.glproject.groupe3.util.Constants;
import com.glproject.groupe3.util.SpreadsheetParser;

@Path("/" + Constants.GENERIC_TASKS)
public class GenericTaskResource {

	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public void importMPD(@FormDataParam("input") InputStream input) {
		new SpreadsheetParser().importFile(input);
	}
}
