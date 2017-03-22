package com.example.glproject.DAO;

import java.util.List;

import com.example.glproject.businessobjects.Staff;

public interface StaffDAO {

	Staff getStaff(long id);

	List<Staff> getStaffs();

	void addStaff(Staff s);
	
	void update(Staff s);
	
	void deleteStaffs();
}
