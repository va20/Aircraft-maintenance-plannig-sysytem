package com.glproject.groupe3.DAOImpl;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.glproject.groupe3.DAO.AbstractDAOFactory;
import com.glproject.groupe3.DAO.DAOImpl;
import com.glproject.groupe3.DAO.Factory;
import com.glproject.groupe3.DAO.TaskDAO;
import com.glproject.groupe3.businessobjects.Task;
import com.glproject.groupe3.util.Constants;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.search.SearchHit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TaskDAOImpl extends DAOImpl<Task> implements TaskDAO {
	private final static Logger logger = Logger.getLogger(TaskDAOImpl.class);

	public TaskDAOImpl(Class<Task> typeT) {
		super(typeT);
	}

	public void update(long id, Task task) {
		ObjectMapper mapper = new ObjectMapper();
		String json = null;

		UpdateRequest ur = new UpdateRequest();
		ur.index(Constants.GL);
		ur.type(Constants.TASKS);
		ur.id(String.valueOf(task.getId()));

		try {
			json = mapper.writeValueAsString(task);
			System.out.println(json);

		} catch (JsonGenerationException e) {
			logger.error("Error" + e.toString());
		} catch (JsonMappingException e) {
			logger.error("Error" + e.toString());
		} catch (IOException e) {
			logger.error("Error" + e.toString());
		}

		ur.doc(json);

		((TaskDAOImpl) AbstractDAOFactory.getFactory(Factory.ES_DAO_FACTORY).getTaskDAO()).update(ur);
	}

	public List<Task> getTasksByMRO(String id) {
		List<Task> tasks = new ArrayList<Task>();
		ObjectMapper mapper = new ObjectMapper();

		SearchRequestBuilder requestBuilder = esc.getClient().prepareSearch(Constants.GL).setTypes(Constants.TASKS);
		SearchResponse searchResponse = requestBuilder.get();
		SearchHit[] searchHits = searchResponse.getHits().getHits();

		for (SearchHit sh : searchHits) {
			Task task = null;

			try {
				task = mapper.readValue(sh.sourceAsString(), Task.class);
				if (task.getIdMro().equals(id))
					tasks.add(task);

			} catch (JsonParseException e) {
				logger.error("Error" + e.toString());
			} catch (JsonMappingException e) {
				logger.error("Error" + e.toString());
			} catch (IOException e) {
				logger.error("Error" + e.toString());
			}
		}
		return tasks;
	}

	public List<Task> getTasksByPlane(String idPlane) {
		List<Task> tasks = new ArrayList<Task>();
		ObjectMapper mapper = new ObjectMapper();

		SearchRequestBuilder requestBuilder = esc.getClient().prepareSearch(Constants.GL).setTypes(Constants.TASKS);
		SearchResponse searchResponse = requestBuilder.get();
		SearchHit[] searchHits = searchResponse.getHits().getHits();

		for (SearchHit sh : searchHits) {
			Task task = null;

			try {
				task = mapper.readValue(sh.sourceAsString(), Task.class);
				if (String.valueOf(task.getIdPlane()).equals(idPlane))
					tasks.add(task);

			} catch (JsonParseException e) {
				logger.error("Error" + e.toString());
			} catch (JsonMappingException e) {
				logger.error("Error" + e.toString());
			} catch (IOException e) {
				logger.error("Error" + e.toString());
			}
		}

		return tasks;
	}
}