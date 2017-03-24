package com.example.glproject.DAOImpl;

import java.io.IOException;

import org.elasticsearch.common.xcontent.XContentFactory;

import com.example.glproject.DAO.DAOImpl;
import com.example.glproject.DAO.StaffDAO;
import com.example.glproject.businessobjects.Staff;

public class StaffDAOImpl extends DAOImpl<Staff> implements StaffDAO {

	public StaffDAOImpl(Class<Staff> typeT) {
		super(typeT);
	}

	public void update(Staff staff) {
		try {
			esc.getClient().prepareUpdate("gl", "staff", Long.toString(staff.getId()))
					.setDoc(XContentFactory.jsonBuilder().startObject().field("id", staff.getId())
							.field("firstname", staff.getFirstname()).field("lastname", staff.getLastname())
							.field("login", staff.getLogin()).field("password", staff.getPassword()).endObject())
					.get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void deleteStaffs() {
	}
}