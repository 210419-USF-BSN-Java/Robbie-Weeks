package com.shop.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shop.model.Item;
import com.shop.model.Offer;
import com.shop.util.ShopUtilities;

public class CustomerDAOImp implements CustomerDAO{
	
	@Override
	public List<Item> viewAvailableItems() {
		
		List<Item> items = new ArrayList<>();

		try(Connection conn = ShopUtilities.getConnection()){

			//sql for select all available items from shop_item.
			String sql = "SELECT * FROM shop_item WHERE item_status = 'Available' ORDER BY item_id";
			PreparedStatement ps = conn.prepareStatement(sql);

			//print the result from ResultSet.
			ResultSet rs = ps.executeQuery();

			//add item objects into item list.
			while(rs.next()) {
				items.add(new Item(
						rs.getInt("item_ID"),
						rs.getString("item_name"),
						rs.getString("item_description"),
						rs.getString("item_Status"),
						rs.getDouble("item_minimum_price"),
						rs.getInt("item_Owner_ID")
						));
			}


		} catch (SQLException e) {
			
			e.printStackTrace();
		}	
		
		//return item list.
		return items;
	}
	
	@Override
	public boolean makeOffer(Offer o) {
		
		boolean success = false;
		
		PreparedStatement ps;
		
		try(Connection conn = ShopUtilities.getConnection()){

			//SQL statement to insert a new offer record into shop_offers table.
			String offer = "INSERT INTO shop_offer (customer_id, offer_amount, offer_status, item_id) VALUES (?,?,?,?);";
			ps = conn.prepareStatement(offer);

			//set the values
			ps.setInt(1, o.getCustomerID());
			ps.setDouble(2, o.getOfferAmount());
			ps.setString(3, "Pending");
			ps.setInt(4, o.getItemID());
			
			if(ps.executeUpdate() == 1) {
				
				success = true;
				
			} 

		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return success;
	}
	
	@Override
	public List<Offer> viewOffers(int userID){
		
		List<Offer> offers = new ArrayList<>();

		try(Connection conn = ShopUtilities.getConnection()){

			//sql for select all offers that belongs to current customer ID.
			String sql = "SELECT * FROM shop_offer WHERE customer_id = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, userID);

			//print the result to ResultSet.
			ResultSet rs = ps.executeQuery();

			//add offer objects into offer list.
			while(rs.next()) {
				offers.add(new Offer(
						rs.getInt("offer_ID"),
						rs.getInt("customer_id"),
						rs.getInt("item_id"),
						rs.getDouble("offer_amount"),
						rs.getString("offer_status")
						));
			}


		} catch (SQLException e) {
			
			e.printStackTrace();
		}	

		return offers;
	}
	
	@Override
	public List<Item> viewOwned(int userID) {
		
		List<Item> items = new ArrayList<>();

		try(Connection conn = ShopUtilities.getConnection()){

			//sql for select all owned item that belongs to current customer ID.
			String sql = "SELECT * FROM shop_item WHERE item_owner_ID = ? ORDER BY item_id";
			PreparedStatement ps = conn.prepareStatement(sql);

			//set user's id to select
			ps.setInt(1, userID);

			//print the result from sql to ResultSet
			ResultSet rs = ps.executeQuery();

			//add item objects into item list.
			while(rs.next()) {
				items.add(new Item(
						rs.getInt("item_ID"),
						rs.getString("item_name"),
						rs.getString("item_description"),
						rs.getString("item_Status"),
						rs.getDouble("item_minimum_price"),
						rs.getInt("item_Owner_ID")
						));
			}


		} catch (SQLException e) {
			
			e.printStackTrace();
		}	

		return items;
	}

}
