package com.shop.model;

public class Item {
	
	private int itemID;
	private String itemName;
	private String itemDescription;
	private String itemStatus;
	private double itermMinimumPrice;
	private int itemOwnerID;
	
	//
	public Item(int itemID, String itemName, String itemDescription, String itemStatus, double itermMinimumPrice,
			int itemOwnerID) {
		super();
		this.itemID = itemID;
		this.itemName = itemName;
		this.itemDescription = itemDescription;
		this.itemStatus = itemStatus;
		this.itermMinimumPrice = itermMinimumPrice;
		this.itemOwnerID = itemOwnerID;
	}
	
	public int getItemID() {
		return itemID;
	}
	public void setItemID(int itemID) {
		this.itemID = itemID;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemDescription() {
		return itemDescription;
	}
	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}
	public String getItemStatus() {
		return itemStatus;
	}
	public void setItemStatus(String itemStatus) {
		this.itemStatus = itemStatus;
	}
	public double getItermMinimumPrice() {
		return itermMinimumPrice;
	}
	public void setItermMinimumPrice(double itermMinimumPrice) {
		this.itermMinimumPrice = itermMinimumPrice;
	}
	public int getItemOwnerID() {
		return itemOwnerID;
	}
	public void setItemOwnerID(int itemOwnerID) {
		this.itemOwnerID = itemOwnerID;
	}
	
	
	
}
