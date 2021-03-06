package com.glproject.groupe3.DAO;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.glproject.groupe3.persistence.ElasticSearchClient;
import com.glproject.groupe3.util.Constants;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.search.SearchHit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class DAOImpl<T> implements DAO<T> {
	protected ElasticSearchClient esc = ElasticSearchClient.getInstance();
	private Class<T> typeT;

	public DAOImpl(Class<T> typeT) {
		this.typeT = typeT;
	}

	public T get(String type, String id) {
		ObjectMapper mapper = new ObjectMapper();
		GetResponse response = esc.getClient().prepareGet(Constants.GL, type, id).get();

		T t = null;

		try {
			t = mapper.readValue(response.getSourceAsString(), typeT);

		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return t;
	}

	public void add(T obj, String type, String id) {
		ObjectMapper mapper = new ObjectMapper();
		String json = null;

		try {
			json = mapper.writeValueAsString(obj);
			System.out.println(json);

		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		esc.getClient().prepareIndex(Constants.GL, type, id).setSource(json).get();
	}

	public void update(UpdateRequest updateReq) {
		try {
			esc.getClient().update(updateReq).get();

		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}

	public void delete(String type, String id) {
		esc.getClient().prepareDelete(Constants.GL, type, id).get();
	}

	public List<T> getAll(String type) {
		List<T> list = new ArrayList<T>();
		ObjectMapper mapper = new ObjectMapper();

		SearchRequestBuilder requestBuilder = esc.getClient().prepareSearch(Constants.GL).setTypes(type)
				.setScroll(new TimeValue(60000)).setSize(100);

		SearchResponse searchResponse = requestBuilder.get();
		SearchHit[] searchHits = searchResponse.getHits().getHits();

		for (SearchHit sh : searchHits) {
			T t = null;

			try {
				t = mapper.readValue(sh.sourceAsString(), typeT);
				list.add(t);
				System.out.println(t.toString());

			} catch (JsonParseException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	public void deleteAll(String type) {
		SearchRequestBuilder requestBuilder = esc.getClient().prepareSearch(Constants.GL).setTypes(type);
		SearchResponse searchResponse = requestBuilder.get();
		SearchHit[] searchHits = searchResponse.getHits().getHits();

		for (SearchHit sh : searchHits) {
			esc.getClient().prepareDelete(sh.getIndex(), sh.getType(), sh.getId()).get();
		}
	}
}