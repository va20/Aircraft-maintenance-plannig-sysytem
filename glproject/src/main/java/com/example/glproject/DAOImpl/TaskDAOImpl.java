package com.example.glproject.DAOImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.search.SearchHit;

import com.example.glproject.DAO.TaskDAO;
import com.example.glproject.businessobjects.MRO;
import com.example.glproject.businessobjects.Task;
import com.example.glproject.persistence.ElasticSearchClient;
import com.fasterxml.jackson.core.JsonGenerationException;

public class TaskDAOImpl implements TaskDAO {
	private ElasticSearchClient esc = ElasticSearchClient.getInstance();

	public void addTask(Task task) {
		ObjectMapper mapper = new ObjectMapper();
		String json = null;

		try {
			json = mapper.writeValueAsString(task);

		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		esc.getClient().prepareIndex("gl", "task").setSource(json).get();
	}

	public void update(Task task) throws IOException {
		esc.getClient().prepareUpdate("gl","task", Long.toString(task.getId()))
		.setDoc(XContentFactory.jsonBuilder()
	    .startObject()
	    .field("idPlane" , task.getIdPlane())
	    .field("mro" , task.getMro())
	    .field("deadline" , task.getDeadline())
	    .field("genericTask" , task.getGenericTask())
	    .endObject()).get();

	}
	
	public void delete(long id) {
		// TODO Auto-generated method stub
		SearchRequestBuilder requestBuilder = esc.getClient().prepareSearch("gl").setTypes("task");
		SearchResponse searchResponse = requestBuilder.get();
		SearchHit[] searchHits = searchResponse.getHits().getHits();

		for (SearchHit sh : searchHits)
			esc.getClient().prepareDelete(sh.getIndex(), sh.getType(), Long.toString(id)).get();
	}


	public Task getTask(long id) {
		ObjectMapper mapper = new ObjectMapper();

		SearchRequestBuilder requestBuilder = esc.getClient().prepareSearch("gl").setTypes("task");
		SearchResponse searchResponse = requestBuilder.get();
		SearchHit[] searchHits = searchResponse.getHits().getHits();

		for (SearchHit sh : searchHits) {
			Task task = null;

			try {
				task = mapper.readValue(sh.sourceAsString(), Task.class);
				
				if(task.getId()==id){
					return task;
				}
			} catch (JsonParseException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	
	public List<Task> getTasksByMRO(long id) {
		List<Task> tasks = new ArrayList<Task>();
		ObjectMapper mapper = new ObjectMapper();

		SearchRequestBuilder requestBuilder = esc.getClient().prepareSearch("gl").setTypes("task");
		SearchResponse searchResponse = requestBuilder.get();
		SearchHit[] searchHits = searchResponse.getHits().getHits();

		for (SearchHit sh : searchHits) {
			Task task = null;

			try {
				task = mapper.readValue(sh.sourceAsString(), Task.class);
				MRO mro=task.getMro();
				if(mro.getId()==id){
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
				if(task.getIdPlane()==id){
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

	public List<Task> getTasks() {
		List<Task> tasks = new ArrayList<Task>();
		ObjectMapper mapper = new ObjectMapper();

		SearchRequestBuilder requestBuilder = esc.getClient().prepareSearch("gl").setTypes("task");
		SearchResponse searchResponse = requestBuilder.get();
		SearchHit[] searchHits = searchResponse.getHits().getHits();

		for (SearchHit sh : searchHits) {
			Task task = null;

			try {
				task = mapper.readValue(sh.sourceAsString(), Task.class);
				tasks.add(task);
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
}
