package com.glproject.groupe3.DAOImpl;

import com.glproject.groupe3.DAO.DAOImpl;
import com.glproject.groupe3.DAO.StaffDAO;
import com.glproject.groupe3.businessobjects.Staff;

public class StaffDAOImpl extends DAOImpl<Staff> implements StaffDAO {

	public StaffDAOImpl(Class<Staff> staffClass) {
		super(staffClass);
	}
}