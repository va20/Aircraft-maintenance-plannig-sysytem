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

import com.example.glproject.DAO.PlaneDAO;
import com.example.glproject.businessobjects.Plane;
import com.example.glproject.persistence.ElasticSearchClient;
import com.fasterxml.jackson.core.JsonGenerationException;

public class PlaneDAOImpl implements PlaneDAO {
	private ElasticSearchClient esc = ElasticSearchClient.getInstance();

	public Plane getPlane(long id) {
		ObjectMapper mapper = new ObjectMapper();

		// query
		SearchRequestBuilder requestBuilder = esc.getClient().prepareSearch("gl").setTypes("plane");
		SearchResponse searchResponse = requestBuilder.get();
		SearchHit[] searchHits = searchResponse.getHits().getHits();

		for (SearchHit sh : searchHits) {
			Plane plane = null;

			try {
				// JSON to Plane
				plane = mapper.readValue(sh.sourceAsString(), Plane.class);
				if(plane.getId()==id)
					return plane;

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

	public List<Plane> getPlanes() {
		List<Plane> planes = new ArrayList<Plane>();
		ObjectMapper mapper = new ObjectMapper();

		// query
		SearchRequestBuilder requestBuilder = esc.getClient().prepareSearch("gl").setTypes("plane");
		SearchResponse searchResponse = requestBuilder.get();
		SearchHit[] searchHits = searchResponse.getHits().getHits();

		for (SearchHit sh : searchHits) {
			Plane plane = null;

			try {
				// JSON to Plane
				plane = mapper.readValue(sh.sourceAsString(), Plane.class);
				planes.add(plane);

			} catch (JsonParseException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return planes;
	}

	public void add(Plane p) {
		ObjectMapper mapper = new ObjectMapper();
		String json = null;

		try {
			json = mapper.writeValueAsString(p);

		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// indexing
		esc.getClient().prepareIndex("gl", "plane").setSource(json).get();
	}

	public void updatePlane(Plane plane) {
		try {
			esc.getClient().prepareUpdate("gl","plane", Long.toString(plane.getId()))
			.setDoc(XContentFactory.jsonBuilder()
			.startObject()
			.field("id" , plane.getId())
			.field("type" , plane.getType())
			.endObject()).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void deletePlane(long id) {
		SearchRequestBuilder requestBuilder = esc.getClient().prepareSearch("gl").setTypes("plane");
		SearchResponse searchResponse = requestBuilder.get();
		SearchHit[] searchHits = searchResponse.getHits().getHits();

		for (SearchHit sh : searchHits)			
			esc.getClient().prepareDelete(sh.getIndex(), sh.getType(), Long.toString(id)).get();
	}

	public void deletePlanes() {

	}
}
