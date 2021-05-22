package com.ers.repository;

import java.util.List;

import com.ers.models.Reimbursment;

public interface ManagerDao {
	
	//approve/deny pending reimbursment
	public boolean requestAction(int reimID, int action, int managerID);
	
	//view all pending reimbursment
	public List<Reimbursment> viewAllPending();
	
	//view all resolved and request and which manager id took action.
	public List<Reimbursment> viewAllResolved();
	
	//view all request history of an employee ID.
	public List<Reimbursment> viewAllRequestById(int userID);
}
