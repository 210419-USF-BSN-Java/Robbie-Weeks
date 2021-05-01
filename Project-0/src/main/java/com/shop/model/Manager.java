package com.shop.model;

public class Manager extends User{
	
	public Manager(String userName, String passWord, String firstName, String lastName) {
		super(userName, passWord, firstName, lastName);
	}

	private int managerID;

	public int getManagerID() {
		return managerID;
	}

	public void setManagerID(int managerID) {
		this.managerID = managerID;
	}

	@Override
	public String toString() {
		super.toString();
		return "Manager [managerID=" + managerID + "]";
	}
	
	
}
