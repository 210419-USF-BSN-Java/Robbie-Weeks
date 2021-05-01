package com.shop.model;

public class Employee extends User {

	private int employeeID;
	
	public Employee() {
		
	}
	
	//constructor for creating an employee account.
	public Employee(String userName, String passWord, String firstName, String lastName) {
		super(userName, passWord, firstName, lastName);
	}

	public int getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}

	@Override
	public String toString() {
		super.toString();
		return "Employee [employeeID=" + employeeID + "]";
	}
	
	
	
}
