package com.ERS.repository;

import com.ERS.models.Employee;
import com.ERS.models.Reimbursment;
import com.ERS.models.User;

public interface EmployeeDao {
	

	
	//view employee information
	public User viewInfo(int empID);
	
	
	//update employee information
	public boolean updateInfo(User u);
	
	
	//receive email
	
	
}
