package com.shop.model;

public class Employee extends User {

	private int employeeID;

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
