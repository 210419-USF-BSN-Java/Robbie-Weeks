package com.shop.model;

public class Payment {
	private int paymentID;
	private int customerID;
	private int itemID;
	private String paymentStatus;
	private double paymentAmount;
	private double remainPayment;
	private int remainTerms;
	
	public Payment() {
		super();
	}

	
	//constructor for createPayment method.
	public Payment(int customerID, int itemID, String paymentStatus, double paymentAmount,
			double remainPayment, int remainTerms) {
		super();
		this.customerID = customerID;
		this.itemID = itemID;
		this.paymentStatus = paymentStatus;
		this.paymentAmount = paymentAmount;
		this.remainPayment = remainPayment;
		this.remainTerms = remainTerms;
	}

	public Payment(int paymentID, int customerID, int itemID, String paymentStatus, double paymentAmount) {
		super();
		this.paymentID = paymentID;
		this.customerID = customerID;
		this.itemID = itemID;
		this.paymentStatus = paymentStatus;
		this.paymentAmount = paymentAmount;
	}
	
	
	
	public Payment(int paymentID, int customerID, int itemID, String paymentStatus, double paymentAmount,
			double remainPayment, int remainTerms) {
		super();
		this.paymentID = paymentID;
		this.customerID = customerID;
		this.itemID = itemID;
		this.paymentStatus = paymentStatus;
		this.paymentAmount = paymentAmount;
		this.remainPayment = remainPayment;
		this.remainTerms = remainTerms;
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

	public double getRemainPayment() {
		return remainPayment;
	}

	public void setRemainPayment(double remainPayment) {
		this.remainPayment = remainPayment;
	}

	public int getRemainTerms() {
		return remainTerms;
	}

	public void setRemainTerms(int remainTerms) {
		this.remainTerms = remainTerms;
	}

	@Override
	public String toString() {
		return "Payment [paymentID=" + paymentID + ", customerID=" + customerID + ", itemID=" + itemID
				+ ", paymentStatus=" + paymentStatus + ", paymentAmount=" + paymentAmount + ", remainPayment="
				+ remainPayment + ", remainTerms=" + remainTerms + "]";
	}
	
	
	
}
