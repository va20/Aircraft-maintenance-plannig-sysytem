package com.example.glproject.DAO;

import com.example.glproject.businessobjects.Staff;

public interface StaffDAO extends DAO<Staff> {

	void update(Staff s);

	void deleteStaffs();
}
