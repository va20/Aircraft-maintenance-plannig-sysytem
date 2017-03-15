package com.example.glproject.DAO;

import java.util.List;

import com.example.glproject.businessobjects.Staff;

public interface StaffDAO {

	Staff getStaff(long id);

	List<Staff> getStaffMembers();

	void addStaff(Staff s);
}
