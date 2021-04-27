package com.shop.repository;

import java.util.List;

import com.shop.model.Item;
import com.shop.model.Payment;

public interface EmployeeDAO {
	
	//Add new item into shop
	public boolean addItem(Item i);
	
	//Accept or reject pending offer for an item.
	public boolean offerAction(int offerID, String action);
	
	//Remove an item from shop.
	public boolean removeItem(int itemID);
	
	//View all payments.
	public List<Payment> viewAllPayments();
	
}
