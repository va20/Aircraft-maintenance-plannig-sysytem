package com.example.glproject.DAOImpl;

import com.example.glproject.DAO.DAOImpl;
import com.example.glproject.DAO.StaffDAO;
import com.example.glproject.businessobjects.Staff;
import com.example.glproject.persistence.ElasticSearchClient;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;

public class StaffDAOImpl extends DAOImpl<Staff> implements StaffDAO {
	private ElasticSearchClient esc = ElasticSearchClient.getInstance();

	public StaffDAOImpl(Class<Staff> staffClass) {
		super(staffClass);
	}

	public void update(Staff s) {

	}

	public void deleteStaffs() {
		SearchRequestBuilder requestBuilder = esc.getClient().prepareSearch("gl").setTypes("staff");
		SearchResponse searchResponse = requestBuilder.get();
		SearchHit[] searchHits = searchResponse.getHits().getHits();

		for (SearchHit sh : searchHits)
			// deleting one by one
			esc.getClient().prepareDelete(sh.getIndex(), sh.getType(), sh.getId()).get();
	}
}