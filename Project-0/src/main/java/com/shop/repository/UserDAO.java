package com.shop.repository;

import com.shop.model.Customer;
import com.shop.model.Employee;
import com.shop.model.User;

public interface UserDAO {
	
	public Customer verifyCredential(Customer c);
	
	public Employee verifyCredential(Employee e);
	
	public boolean checkUserName(String userName);
	
	public boolean registUserAccount(Customer c);
	
}
