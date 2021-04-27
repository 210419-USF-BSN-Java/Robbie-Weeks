package com.shop.repository;

import java.util.List;

import com.shop.model.Customer;
import com.shop.model.Item;
import com.shop.model.Offer;
import com.shop.model.Payment;

public interface CustomerDAO {
	
	//show all items that the state is not owned.
	public List<Item> viewAvailableItems();
	
	//make an offer to a available item.
	public boolean makeOffer(Offer o);
	
	//show all items the customer owned.
	public List<Item> viewOwned(Customer c);
	
	//show remaining payments for an item.
	public List<Payment> viewRemainPayments(Customer c);
	
	//Show all payments.
	public List<Payment> viewAllPayments(Customer c);
	
}
