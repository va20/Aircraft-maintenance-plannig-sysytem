package com.example.glproject.DAOImpl;

import java.io.IOException;

import org.elasticsearch.common.xcontent.XContentFactory;

import com.example.glproject.DAO.DAOImpl;
import com.example.glproject.DAO.PlaneDAO;
import com.example.glproject.businessobjects.Plane;

public class PlaneDAOImpl extends DAOImpl<Plane> implements PlaneDAO {

	public PlaneDAOImpl(Class<Plane> typeT) {
		super(typeT);
	}

	public void updatePlane(Plane plane) {
		try {
			esc.getClient().prepareUpdate("gl", "plane", Long.toString(plane.getId())).setDoc(XContentFactory
					.jsonBuilder().startObject().field("id", plane.getId()).field("type", plane.getType()).endObject())
					.get();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
