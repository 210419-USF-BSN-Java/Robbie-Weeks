package com.shop.model;

public class Item {
	
	private int itemID;
	private String itemName;
	private String itemDescription;
	private String itemStatus;
	private double itermMinimumPrice;
	private int itemOwnerID;
	
	//all-arg constructor
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
	
	//constructor for employee to add item.
	public Item(String itemName, String itemDescription, Double minimumPrice) {
		this.itemName = itemName;
		this.itemDescription = itemDescription;
		this.itermMinimumPrice = minimumPrice;
	}
	
	//constructor for employee to edit item.
	public Item(int itemID, String itemName, String itemDescription, Double minimumPrice) {
		this.itemID = itemID;
		this.itemName = itemName;
		this.itemDescription = itemDescription;
		this.itermMinimumPrice = minimumPrice;
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

	@Override
	public String toString() {
		return "Item [itemID = " + itemID + ", itemName = " + itemName + ", itemDescription = " + itemDescription
				+ ", itemStatus = " + itemStatus + ", itermMinimumPrice = " + itermMinimumPrice + "]";
	}

	
	
	
	
}
