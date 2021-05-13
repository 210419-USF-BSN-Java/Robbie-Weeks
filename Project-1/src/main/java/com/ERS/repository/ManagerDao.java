package com.ERS.repository;

public interface ManagerDao {
	
	//approve/deny pending reimbursment
	public boolean requestAction(int reimID, int action, int managerID);
	
}
