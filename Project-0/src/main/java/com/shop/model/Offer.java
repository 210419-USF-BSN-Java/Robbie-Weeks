package com.shop.model;

public class Offer {
	
	private int offerID;
	private int customerID;
	private int itemID;
	private double offerAmount;
	private String offerStatus;
	
	//constructor for makeOffer method
	public Offer(int customerID, int itemID, double offerAmount) {
		this.customerID = customerID;
		this.itemID = itemID;
		this.offerAmount = offerAmount;
	}
	public int getOfferID() {
		return offerID;
	}
	public void setOfferID(int offerID) {
		this.offerID = offerID;
	}
	public int getCustomerID() {
		return customerID;
	}
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	public double getOfferAmount() {
		return offerAmount;
	}
	public void setOfferAmount(double offerAmount) {
		this.offerAmount = offerAmount;
	}
	public String getOfferStatus() {
		return offerStatus;
	}
	public void setOfferStatus(String offerStatus) {
		this.offerStatus = offerStatus;
	}

}

