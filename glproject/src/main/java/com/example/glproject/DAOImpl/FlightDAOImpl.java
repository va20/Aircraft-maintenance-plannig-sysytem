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
import org.joda.time.DateTime;

import com.example.glproject.DAO.DAOImpl;
import com.example.glproject.DAO.FlightDAO;
import com.example.glproject.businessobjects.Flight;

public class FlightDAOImpl extends DAOImpl<Flight> implements FlightDAO {

	public FlightDAOImpl(Class<Flight> typeT) {
		super(typeT);
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
				// if (flight.getDepTime() == day)
				// flights.add(flight);
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
				// if (flight.getArrTime() == day)
				// flights.add(flight);
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
}
