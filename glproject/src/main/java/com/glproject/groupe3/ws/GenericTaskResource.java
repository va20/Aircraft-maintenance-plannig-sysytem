package com.glproject.groupe3.ws;

import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.multipart.FormDataParam;

import com.glproject.groupe3.parsing.SpreadsheetParser;

@Path("/mpd")
public class GenericTaskResource {

	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public void importMPD(@FormDataParam("input") InputStream input) {
		System.out.println("staif");
		new SpreadsheetParser().importFile(input);
	}
}
