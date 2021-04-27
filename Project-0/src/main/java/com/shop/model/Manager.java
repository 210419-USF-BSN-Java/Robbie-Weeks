package com.shop.model;

public class Manager extends User{
	
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
