package com.shop.repository;

import java.util.List;

import com.shop.model.Item;
import com.shop.model.Offer;

public interface CustomerDAO {
	
	//show all items that the state is not owned.
	public List<Item> viewAvailableItems();
	
	//make an offer to a available item.
	public boolean makeOffer(Offer o);
	
	//show all items the customer owned.
	public List<Item> viewOwned(int userID);
	
	//show all offers the customer made.
	public List<Offer> viewOffers(int userID);
}
