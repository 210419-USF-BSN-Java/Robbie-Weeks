package com.shop.repository;

import java.util.List;

import com.shop.model.Employee;
import com.shop.model.Payment;

public interface ManagerDAO {
	
	public boolean makeEmployeeAccount(Employee e);
	
	public boolean deleteEmployeeAccount(int employeeID);
	
	//show log file of all transactions.
	public List<Payment> viewSalesHistory();
}
