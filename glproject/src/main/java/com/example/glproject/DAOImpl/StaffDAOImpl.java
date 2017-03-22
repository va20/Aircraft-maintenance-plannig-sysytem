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

import com.example.glproject.DAO.StaffDAO;
import com.example.glproject.businessobjects.Plane;
import com.example.glproject.businessobjects.Staff;
import com.example.glproject.persistence.ElasticSearchClient;
import com.fasterxml.jackson.core.JsonGenerationException;

public class StaffDAOImpl implements StaffDAO {
	private ElasticSearchClient esc = ElasticSearchClient.getInstance();

	public Staff getStaff(long id) {
		ObjectMapper mapper = new ObjectMapper();

		// query
		SearchRequestBuilder requestBuilder = esc.getClient().prepareSearch("gl").setTypes("staff");
		SearchResponse searchResponse = requestBuilder.get();
		SearchHit[] searchHits = searchResponse.getHits().getHits();

		for (SearchHit sh : searchHits) {
			Staff staff = null;

			try {
				
				staff = mapper.readValue(sh.sourceAsString(), Staff.class);
				if(staff.getId()==id)
					return staff;

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

	public List<Staff> getStaffs() {
		List<Staff> staffs = new ArrayList<Staff>();
		ObjectMapper mapper = new ObjectMapper();

		// query
		SearchRequestBuilder requestBuilder = esc.getClient().prepareSearch("gl").setTypes("staff");
		SearchResponse searchResponse = requestBuilder.get();
		SearchHit[] searchHits = searchResponse.getHits().getHits();

		for (SearchHit sh : searchHits) {
			Staff staff = null;

			try {
				// JSON to Staff
				staff = mapper.readValue(sh.sourceAsString(), Staff.class);
				staffs.add(staff);

			} catch (JsonParseException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return staffs;
	}

	public void addStaff(Staff s) {
		ObjectMapper mapper = new ObjectMapper();
		String json = null;

		try {
			json = mapper.writeValueAsString(s);

		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// indexing
		esc.getClient().prepareIndex("gl", "staff").setSource(json).get();
	}

	public void deleteStaffs() {
		SearchRequestBuilder requestBuilder = esc.getClient().prepareSearch("gl").setTypes("staff");
		SearchResponse searchResponse = requestBuilder.get();
		SearchHit[] searchHits = searchResponse.getHits().getHits();

		for (SearchHit sh : searchHits)
			//deleting one by one
			esc.getClient().prepareDelete(sh.getIndex(), sh.getType(), sh.getId()).get();
	}

	public void update(Staff staff) {
		// TODO Auto-generated method stub
		try {
			esc.getClient().prepareUpdate("gl","staff", Long.toString(staff.getId()))
			.setDoc(XContentFactory.jsonBuilder()
			.startObject()
			.field("id" ,staff.getId() )
			.field("firstname" ,staff.getFirstname() )
			.field("lastname" ,staff.getLastname() )
			.field("login" ,staff.getLogin() )
			.field("password" ,staff.getPassword() )
			.endObject()).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}