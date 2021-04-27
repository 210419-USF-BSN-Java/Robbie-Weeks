package com.shop.model;

public class Payment {
	private int paymentID;
	private int customerID;
	private int itemID;
	private String paymentStatus;
	private double paymentAmount;
	
	public Payment(int paymentID, int customerID, int itemID, String paymentStatus, double paymentAmount) {
		super();
		this.paymentID = paymentID;
		this.customerID = customerID;
		this.itemID = itemID;
		this.paymentStatus = paymentStatus;
		this.paymentAmount = paymentAmount;
	}
	
	public int getPaymentID() {
		return paymentID;
	}
	public void setPaymentID(int paymentID) {
		this.paymentID = paymentID;
	}
	public int getItemID() {
		return itemID;
	}
	public void setItemID(int itemID) {
		this.itemID = itemID;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public double getPaymentAmount() {
		return paymentAmount;
	}
	public void setPaymentAmount(double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}
	public int getCustomerID() {
		return customerID;
	}
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	
	
}
