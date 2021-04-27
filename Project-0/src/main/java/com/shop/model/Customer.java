package com.shop.model;

public class Customer extends User {
	
	private int customerID;
	
	public Customer() {
		super();
	}
	
	public Customer(String firstName, String lastName, String userName, String passWord) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.passWord = passWord;
		
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	@Override
	public String toString() {
		super.toString();
		return "Customer [customerID=" + customerID + "]";
	}

	
	
	
}
