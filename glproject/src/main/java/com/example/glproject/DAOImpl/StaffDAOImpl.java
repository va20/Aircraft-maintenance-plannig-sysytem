package com.example.glproject.DAOImpl;

import com.example.glproject.DAO.DAOImpl;
import com.example.glproject.DAO.StaffDAO;
import com.example.glproject.businessobjects.Flight;
import com.example.glproject.businessobjects.Staff;
import com.example.glproject.persistence.ElasticSearchClient;
import com.fasterxml.jackson.core.JsonGenerationException;
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

public class StaffDAOImpl extends DAOImpl<Staff> implements StaffDAO {
	private ElasticSearchClient esc = ElasticSearchClient.getInstance();

	public StaffDAOImpl(Class<Staff> staffClass) {
		super(staffClass);
	}


	public Staff getStaff(long id) {
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

	public void update(Staff s) {

	}

	public void deleteStaffs() {
		SearchRequestBuilder requestBuilder = esc.getClient().prepareSearch("gl").setTypes("staff");
		SearchResponse searchResponse = requestBuilder.get();
		SearchHit[] searchHits = searchResponse.getHits().getHits();

		for (SearchHit sh : searchHits)
			//deleting one by one
			esc.getClient().prepareDelete(sh.getIndex(), sh.getType(), sh.getId()).get();
	}

	public Staff get(long id, String type) {
		return null;
	}

	public void add(Staff obj, String type) {

	}

	public void update(UpdateRequest updateReq) {

	}

	public void delete(long id, String type) {

	}

	public List<Staff> getAll(String type) {
		return null;
	}

	public Flight getFlight(String commercial) {
		return null;
	}

	public List<Flight> getFlights() {
		return null;
	}
}