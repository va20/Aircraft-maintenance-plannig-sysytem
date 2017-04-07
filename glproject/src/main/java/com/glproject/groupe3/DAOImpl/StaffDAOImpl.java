package com.glproject.groupe3.DAOImpl;

import com.glproject.groupe3.DAO.DAOImpl;
import com.glproject.groupe3.DAO.StaffDAO;
import com.glproject.groupe3.businessobjects.Staff;
import com.glproject.groupe3.persistence.ElasticSearchClient;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;

public class StaffDAOImpl extends DAOImpl<Staff> implements StaffDAO {

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