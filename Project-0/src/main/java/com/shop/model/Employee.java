package com.shop.model;

public class Employee extends User {

	private int employeeID;
	
	//constructor for creating an employee account.
	public Employee(String userName, String passWord, String firstName, String lastName) {
		this.userName = userName;
		this.passWord = passWord;
		this.firstName = firstName;
		this.lastName = lastName;
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
