package com.example.glproject.DAOImpl;

import com.example.glproject.DAO.DAOImpl;
import com.example.glproject.DAO.TaskDAO;
import com.example.glproject.businessobjects.Flight;
import com.example.glproject.businessobjects.Task;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TaskDAOImpl extends DAOImpl<Task> implements TaskDAO {
	
	public TaskDAOImpl(Class<Task> typeT) {
		super(typeT);
	}

	public void update(Task task) {
//		try {
//			esc.getClient().prepareUpdate("gl", "task", Long.toString(task.getId()))
//					.setDoc(XContentFactory.jsonBuilder().startObject().field("idPlane", task.getIdPlane())
//							.field("mro", task.getMro()).field("deadline", task.getDeadline())
//							.field("genericTask", task.getGenericTask()).endObject())
//					.get();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}

	}

	public List<Task> getTasksByMRO(long id) {
		List<Task> tasks = new ArrayList<Task>();
		ObjectMapper mapper = new ObjectMapper();

		SearchRequestBuilder requestBuilder = esc.getClient().prepareSearch("gl").setTypes("task");
		SearchResponse searchResponse = requestBuilder.get();
		SearchHit[] searchHits = searchResponse.getHits().getHits();

		for (SearchHit sh : searchHits) {
			Task task = null;

//			try {
//				task = mapper.readValue(sh.sourceAsString(), Task.class);
//				MRO mro = task.getMro();
//				if (mro.getId() == id) {
//					tasks.add(task);
//				}
//			} catch (JsonParseException e) {
//				e.printStackTrace();
//			} catch (JsonMappingException e) {
//				e.printStackTrace();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
		}
		return tasks;
	}

	public List<Task> getTasksByPlane(long id) {
		List<Task> tasks = new ArrayList<Task>();
		ObjectMapper mapper = new ObjectMapper();

		SearchRequestBuilder requestBuilder = esc.getClient().prepareSearch("gl").setTypes("task");
		SearchResponse searchResponse = requestBuilder.get();
		SearchHit[] searchHits = searchResponse.getHits().getHits();

		for (SearchHit sh : searchHits) {
			Task task = null;

			try {
				task = mapper.readValue(sh.sourceAsString(), Task.class);
				if (task.getIdPlane() == id) {
					tasks.add(task);
				}
			} catch (JsonParseException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return tasks;
	}

	public Flight getFlight(String commercial) {
		return null;
	}

	public List<Flight> getFlights() {
		return null;
	}
}
