package com.example.glproject.DAOImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.get.MultiGetItemResponse;
import org.elasticsearch.action.get.MultiGetResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.support.WriteRequest.RefreshPolicy;

import com.example.glproject.DAO.StaffDAO;
import com.example.glproject.businessobjects.Staff;
import com.example.glproject.persistence.ElasticSearchClient;
import com.fasterxml.jackson.core.JsonGenerationException;

public class StaffDAOImpl implements StaffDAO {
	private ElasticSearchClient esc = ElasticSearchClient.getInstance();

	public Staff getStaff(long id) {
		return null;
	}

	public List<Staff> getStaffMembers() {
		List<Staff> staffs = new ArrayList<Staff>();

		MultiGetResponse multiGetItemResponses = ElasticSearchClient.getClient().prepareMultiGet().add("gl", "staff")
				.get();

		for (MultiGetItemResponse itemResponse : multiGetItemResponses) {
			GetResponse response = itemResponse.getResponse();
			if (response.isExists()) {
				String json = response.getSourceAsString();
				// ajouter member dans la liste
				System.out.println(json);
			}
		}

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

		IndexRequestBuilder indexRequest = ElasticSearchClient.getClient().prepareIndex("gl", "staff")
				.setId(Long.toString(s.getId())).setSource(json);
		/*
		 * IndexResponse response =
		 * ElasticSearchClient.getClient().prepareIndex("staff", "chris")
		 * .setSource(json) .get();
		 */
		indexRequest.setRefreshPolicy(RefreshPolicy.IMMEDIATE).execute().actionGet();
	}
}