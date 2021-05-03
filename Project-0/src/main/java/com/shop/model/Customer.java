package com.shop.model;

public class Customer extends User {
	
	private int customerID;
	

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
