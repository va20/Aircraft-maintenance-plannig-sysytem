package com.glproject.groupe3.DAOImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//import org.glassfish.hk2.utilities.reflection.Logger;
import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;

import com.glproject.groupe3.DAO.DAOImpl;
import com.glproject.groupe3.DAO.GenericTaskDAO;
import com.glproject.groupe3.businessobjects.GenericTask;
import com.glproject.groupe3.util.Constants;

public class GenericTaskDAOImpl extends DAOImpl<GenericTask> implements GenericTaskDAO {
	private static final Logger logger = Logger.getLogger(GenericTaskDAOImpl.class);

	public GenericTaskDAOImpl(Class<GenericTask> typeT) {
		super(typeT);
	}

	// public void update(GenericTask gt) {
	// try {
	// esc.getClient().prepareUpdate("gl", "mpd", gt.getTaskNumber())
	// .setDoc(XContentFactory.jsonBuilder().startObject().field("applicability",
	// gt.getApplicability())
	// .field("descr", gt.getDescr()).field("duration", gt.getDuration())
	// .field("hangar", gt.isHangar()).field("men", gt.getMen())
	// .field("periodicity", gt.getPeriodicity()).field("zone",
	// gt.getZone()).endObject())
	// .get();
	//
	// } catch (IOException e) {
	// logger.error("Error" + e.toString());
	// }
	// }

	public List<GenericTask> getMPDByTypeOfPlane(String type) {
		List<GenericTask> genericTasks = new ArrayList<GenericTask>();
		ObjectMapper mapper = new ObjectMapper();

		SearchRequestBuilder requestBuilder = esc.getClient().prepareSearch(Constants.GL)
				.setTypes(Constants.GENERIC_TASKS);
		SearchResponse searchResponse = requestBuilder.get();
		SearchHit[] searchHits = searchResponse.getHits().getHits();

		for (SearchHit sh : searchHits) {
			GenericTask genericTask = null;

			try {
				genericTask = mapper.readValue(sh.sourceAsString(), GenericTask.class);
				if (genericTask.getType().equals(type))
					genericTasks.add(genericTask);

			} catch (JsonParseException e) {
				logger.error("Error" + e.toString());
			} catch (JsonMappingException e) {
				logger.error("Error" + e.toString());
			} catch (IOException e) {
				logger.error("Error" + e.toString());
			}
		}

		return genericTasks;
	}
}
