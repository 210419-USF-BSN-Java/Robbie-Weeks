package com.ERS.repository;

import java.util.List;

import com.ERS.models.Reimbursment;

public interface ReimbursmentDao {
	//create a ERS
	public boolean makeReim(Reimbursment r);
	
	//view pending ERS
	public List<Reimbursment> viewPending(int userID);
	
	//view resolved ERS
	public List<Reimbursment> viewResolved(int userID);
	
	//view all ERS
	public List<Reimbursment> viewAllRequest(int userID);
	
	//upload an image about their ERS
	
	//view all pending reimbursment
	public List<Reimbursment> viewAllPending();
	
	//view all resolved and request and which manager id took action.
	public List<Reimbursment> viewAllResolved();
	
	
	
}
