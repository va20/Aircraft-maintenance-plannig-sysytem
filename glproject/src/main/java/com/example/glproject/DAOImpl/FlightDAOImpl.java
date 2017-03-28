package com.example.glproject.DAOImpl;

import com.example.glproject.DAO.DAOImpl;
import com.example.glproject.DAO.FlightDAO;
import com.example.glproject.businessobjects.Flight;
import com.example.glproject.persistence.ElasticSearchClient;
import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.search.SearchHit;
import org.joda.time.DateTime;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FlightDAOImpl extends DAOImpl<Flight> implements FlightDAO {
	private static final Logger logger = Logger.getLogger(FlightDAOImpl.class);
	private ElasticSearchClient esc = ElasticSearchClient.getInstance();

	public FlightDAOImpl(Class<Flight> flightClass) {
		super(flightClass);
	}


	public void add(Flight flight) {
		ObjectMapper mapper = new ObjectMapper();
		String json = null;

		try {
			json = mapper.writeValueAsString(flight);

		} catch (Exception e) {
			logger.error("Unable to convert Flight to JSON object",e);
		}

		esc.getClient().prepareIndex("gl", "flight").setSource(json).get();
	}

	public void update(Flight flight) {
		try {
			esc.getClient().prepareUpdate("gl", "flight", Long.toString(flight.getIdPlane()))
					.setDoc(XContentFactory.jsonBuilder().startObject().field("arrAirport", flight.getArrAirport())
							.field("arrTime", flight.getArrTime()).field("commercial", flight.getCommercial())
							.field("depAirport", flight.getDepAirport()).field("depTime", flight.getDepTime())
							.field("idPlane", flight.getIdPlane()).endObject())
					.get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void delete(long idPlane) {
		SearchRequestBuilder requestBuilder = esc.getClient().prepareSearch("gl").setTypes("flight");
		SearchResponse searchResponse = requestBuilder.get();
		SearchHit[] searchHits = searchResponse.getHits().getHits();

		for (SearchHit sh : searchHits)
			esc.getClient().prepareDelete(sh.getIndex(), sh.getType(), Long.toString(idPlane)).get();
	}

	public Flight getFlight(String commercial) {
		ObjectMapper mapper = new ObjectMapper();

		SearchRequestBuilder requestBuilder = esc.getClient().prepareSearch("gl").setTypes("flight");
		SearchResponse searchResponse = requestBuilder.get();
		SearchHit[] searchHits = searchResponse.getHits().getHits();

		for (SearchHit sh : searchHits) {
			Flight flight = null;

			try {
				flight = mapper.readValue(sh.sourceAsString(), Flight.class);

				if (flight.getCommercial().equals(commercial)) {
					return flight;
				}
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

	public List<Flight> getFlights() {
		ObjectMapper mapper = new ObjectMapper();
		List<Flight> flights = new ArrayList<Flight>();
		SearchRequestBuilder requestBuilder = esc.getClient().prepareSearch("gl").setTypes("flight");
		SearchResponse searchResponse = requestBuilder.get();
		SearchHit[] searchHits = searchResponse.getHits().getHits();

		for (SearchHit sh : searchHits) {
			Flight flight = null;

			try {
				flight = mapper.readValue(sh.sourceAsString(), Flight.class);
				flights.add(flight);
			} catch (JsonParseException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return flights;
	}

	public List<Flight> getByDayDep(DateTime day) {
		ObjectMapper mapper = new ObjectMapper();
		List<Flight> flights = new ArrayList<Flight>();
		SearchRequestBuilder requestBuilder = esc.getClient().prepareSearch("gl").setTypes("flight");
		SearchResponse searchResponse = requestBuilder.get();
		SearchHit[] searchHits = searchResponse.getHits().getHits();

		for (SearchHit sh : searchHits) {
			Flight flight = null;

			try {
				flight = mapper.readValue(sh.sourceAsString(), Flight.class);
				if (flight.getDepTime() == day)
					flights.add(flight);
			} catch (JsonParseException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return flights;
	}

	public List<Flight> getByDayArr(DateTime day) {
		ObjectMapper mapper = new ObjectMapper();
		List<Flight> flights = new ArrayList<Flight>();
		SearchRequestBuilder requestBuilder = esc.getClient().prepareSearch("gl").setTypes("flight");
		SearchResponse searchResponse = requestBuilder.get();
		SearchHit[] searchHits = searchResponse.getHits().getHits();

		for (SearchHit sh : searchHits) {
			Flight flight = null;

			try {
				flight = mapper.readValue(sh.sourceAsString(), Flight.class);
				if (flight.getArrTime() == day)
					flights.add(flight);
			} catch (JsonParseException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return flights;
	}

	public Flight get(long id, String type) {
		return null;
	}

	public void add(Flight obj, String type) {

	}

	public void update(UpdateRequest updateReq) {

	}

	public void delete(long id, String type) {

	}

	public List<Flight> getAll(String type) {
		return null;
	}
}
