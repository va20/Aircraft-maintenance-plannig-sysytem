package com.example.glproject.DAOImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.action.support.WriteRequest.RefreshPolicy;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;

import com.example.glproject.DAO.StaffDAO;
import com.example.glproject.businessobjects.Staff;
import com.example.glproject.persistence.ElasticSearchClient;
import com.fasterxml.jackson.core.JsonGenerationException;

public class StaffDAOImpl implements StaffDAO {
	private ElasticSearchClient esc = ElasticSearchClient.getInstance();

	public Staff getStaff(long id) {
		GetResponse response = ElasticSearchClient.getClient().prepareGet("staff1", "simple1", String.valueOf(id))
				.get();
		Map<String, Object> source = response.getSource();
		System.out.println(source);

		return new Staff();
	}

	public List<Staff> getStaffMembers() {
		List<Staff> staffs = new ArrayList<Staff>();

		/*
		 * SearchResponse response =
		 * ElasticSearchClient.getClient().prepareSearch().setTypes()
		 * .setSearchType(SearchType.DFS_QUERY_THEN_FETCH).setQuery(
		 * QueryBuilders.termQuery("type", "staff")) .setExplain(true).get();
		 * 
		 * SearchHit[] res = response.getHits().getHits();
		 * System.out.println(res.length);
		 * 
		 * for (SearchHit hit : res) {
		 * System.out.println("------------------------------"); Map<String,
		 * Object> result = hit.getSource(); staffs.add((Staff) result); }
		 */

		return staffs;
	}

	public void addStaff(Staff s) {
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = null;
		try {
			json = ow.writeValueAsString(s);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		IndexRequestBuilder indexRequest = ElasticSearchClient.getClient().prepareIndex("staff", "chris")
				.setId(Long.toString(s.getId())).setSource(json);
		indexRequest.setRefreshPolicy(RefreshPolicy.IMMEDIATE).execute().actionGet();
	}
}
