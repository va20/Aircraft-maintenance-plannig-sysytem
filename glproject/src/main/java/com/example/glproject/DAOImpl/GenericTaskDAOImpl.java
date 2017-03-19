package com.example.glproject.DAOImpl;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.example.glproject.DAO.GenericTaskDAO;
import com.example.glproject.businessobjects.GenericTask;
import com.example.glproject.persistence.ElasticSearchClient;
import com.fasterxml.jackson.core.JsonGenerationException;

public class GenericTaskDAOImpl implements GenericTaskDAO {
	private ElasticSearchClient esc = ElasticSearchClient.getInstance();

	public GenericTask getGenericTask() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<GenericTask> getGenericTasks() {
		// TODO Auto-generated method stub
		return null;
	}

	public void add(GenericTask gt) {
		ObjectMapper mapper = new ObjectMapper();
		String json = null;

		try {
			json = mapper.writeValueAsString(gt);

		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// indexing
		esc.getClient().prepareIndex("gl", "mpd").setSource(json).get();
	}

	public void update(GenericTask gt) {
		// TODO Auto-generated method stub
		
	}

	public void delete(String reference) {
		// TODO Auto-generated method stub
		
	}

	public List<GenericTask> getByType(int type) {
		// TODO Auto-generated method stub
		return null;
	}

	public GenericTask get(String reference) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
