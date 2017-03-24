package com.example.glproject.DAOImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.search.SearchHit;

import com.example.glproject.DAO.DAOImpl;
import com.example.glproject.DAO.GenericTaskDAO;
import com.example.glproject.businessobjects.GenericTask;

public class GenericTaskDAOImpl extends DAOImpl<GenericTask> implements GenericTaskDAO {

	public GenericTaskDAOImpl(Class<GenericTask> typeT) {
		super(typeT);
	}

	public void update(GenericTask gt) {
		try {
			esc.getClient().prepareUpdate("gl", "mpd", gt.getTaskNumber())
					.setDoc(XContentFactory.jsonBuilder().startObject().field("applicability", gt.getApplicability())
							.field("descr", gt.getDescr()).field("duration", gt.getDuration())
							.field("hangar", gt.isHangar()).field("men", gt.getMen())
							.field("periodicity", gt.getPeriodicity()).field("zone", gt.getZone()).endObject())
					.get();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<GenericTask> getByType(String type) {
		List<GenericTask> genericTasks = new ArrayList<GenericTask>();
		ObjectMapper mapper = new ObjectMapper();

		SearchRequestBuilder requestBuilder = esc.getClient().prepareSearch("gl").setTypes("mpd");
		SearchResponse searchResponse = requestBuilder.get();
		SearchHit[] searchHits = searchResponse.getHits().getHits();

		for (SearchHit sh : searchHits) {
			GenericTask genericTask = null;

			try {
				genericTask = mapper.readValue(sh.sourceAsString(), GenericTask.class);
				// if(genericTask.getType == type)
				genericTasks.add(genericTask);

			} catch (JsonParseException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return genericTasks;
	}

	public GenericTask getGenericTask(String reference) {
		ObjectMapper mapper = new ObjectMapper();

		SearchRequestBuilder requestBuilder = esc.getClient().prepareSearch("gl").setTypes("mpd");
		SearchResponse searchResponse = requestBuilder.get();
		SearchHit[] searchHits = searchResponse.getHits().getHits();

		for (SearchHit sh : searchHits) {
			GenericTask genericTask = null;

			try {
				genericTask = mapper.readValue(sh.sourceAsString(), GenericTask.class);
				if (genericTask.getTaskNumber().equals(reference))
					return genericTask;

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
}
