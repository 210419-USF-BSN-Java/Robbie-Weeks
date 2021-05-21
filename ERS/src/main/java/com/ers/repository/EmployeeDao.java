package com.ers.repository;

import java.util.List;

import com.ers.models.Reimbursment;
import com.ers.models.User;

public interface EmployeeDao {
	//view employee information
	public User viewInfo(int empID);
	
	//update employee information
	public boolean updateInfo(User u);
	
	//create a ERS
	public boolean makeReim(Reimbursment r);
	
	//view pending ERS
	public List<Reimbursment> viewPending(int userID);
	
	//view resolved ERS
	public List<Reimbursment> viewResolved(int userID);
	
	//view all ERS
	public List<Reimbursment> viewAllRequest(int userID);
	
}
