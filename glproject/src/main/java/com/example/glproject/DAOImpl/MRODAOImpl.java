package com.example.glproject.DAOImpl;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.search.SearchHit;

import com.example.glproject.DAO.MRODao;
import com.example.glproject.businessobjects.MRO;
import com.example.glproject.persistence.ElasticSearchClient;
import com.fasterxml.jackson.core.JsonGenerationException;

public class MRODAOImpl implements MRODao {
	private ElasticSearchClient esc = ElasticSearchClient.getInstance();

	public void add(MRO mro) {
		// TODO Auto-generated method stub
		ObjectMapper mapper = new ObjectMapper();
		String json = null;

		try {
			json = mapper.writeValueAsString(mro);

		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// indexing
		esc.getClient().prepareIndex("gl", "mro").setSource(json).get();
	}

	public void delete(long id) {
		// TODO Auto-generated method stub
		SearchRequestBuilder requestBuilder = esc.getClient().prepareSearch("gl").setTypes("mro");
		SearchResponse searchResponse = requestBuilder.get();
		SearchHit[] searchHits = searchResponse.getHits().getHits();

		for (SearchHit sh : searchHits)			
			esc.getClient().prepareDelete(sh.getIndex(), sh.getType(), Long.toString(id)).get();
	}

	public void update(MRO mro){
		try {
			esc.getClient().prepareUpdate("gl","mro", Long.toString(mro.getId()))
			.setDoc(XContentFactory.jsonBuilder()
			.startObject()
			.field("id" , mro.getId())
			.field("list_task" , mro.getList_task())
			.endObject()).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public MRO getByid(long id) {
		ObjectMapper mapper = new ObjectMapper();

		// query
		SearchRequestBuilder requestBuilder = esc.getClient().prepareSearch("gl").setTypes("mro");
		SearchResponse searchResponse = requestBuilder.get();
		SearchHit[] searchHits = searchResponse.getHits().getHits();

		for (SearchHit sh : searchHits) {
			MRO mro = null;

			try {
				// JSON to Plane
				mro = mapper.readValue(sh.sourceAsString(), MRO.class);
				if(mro.getId()==id)
					return mro;

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
