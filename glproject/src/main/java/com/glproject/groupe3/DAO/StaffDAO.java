package com.glproject.groupe3.DAO;

import com.glproject.groupe3.businessobjects.Staff;

public interface StaffDAO extends DAO<Staff> {

	void update(Staff s);

	void deleteStaffs();
}
