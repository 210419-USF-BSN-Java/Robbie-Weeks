package com.shop.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shop.model.Customer;
import com.shop.model.Item;
import com.shop.model.Offer;
import com.shop.model.Payment;
import com.shop.util.ShopUtilities;

public class CustomerDAOImp implements CustomerDAO{

	public List<Item> viewAvailableItems() {
		
		List<Item> items = new ArrayList<>();

		try(Connection conn = ShopUtilities.getConnection()){

			//sql for select all owned item that belongs to current customer ID.
			String sql = "SELECT * FROM shop_item WHERE item_status = 'staging' order by item_id";
			PreparedStatement ps = conn.prepareStatement(sql);

			//print the result from sql to ResultSet
			ResultSet rs = ps.executeQuery();

			//call constructor to create an Customer object
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

	public boolean makeOffer(Offer o) {
		
		boolean success = false;
		
		PreparedStatement ps;
		
		try(Connection conn = ShopUtilities.getConnection()){

			//SQL statement to insert a new account into shop_offers table.
			String offer = "insert into shop_customer_offer (customer_id, offer_amount, offer_status) values (?,?,?);";
			ps = conn.prepareStatement(offer);

			//set the values
			ps.setInt(1, o.getCustomerID());
			ps.setDouble(2, o.getOfferAmount());
			ps.setString(3, o.getOfferStatus());
			
			if(ps.executeUpdate() == 1) {
				
				success = true;
				
			} 

		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		return success;
	}

	public List<Item> viewOwned(Customer c) {
		
		List<Item> items = new ArrayList<>();

		try(Connection conn = ShopUtilities.getConnection()){

			//sql for select all owned item that belongs to current customer ID.
			String sql = "SELECT * FROM shop_item WHERE item_owner_ID = ? and item_status = 'owned' order by item_id";
			PreparedStatement ps = conn.prepareStatement(sql);

			//set user's id to select
			ps.setInt(1, c.getCustomerID());

			//print the result from sql to ResultSet
			ResultSet rs = ps.executeQuery();

			//call constructor to create an item object
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

	public List<Payment> viewRemainPayments(Customer c) {
		
		List<Payment> payments = new ArrayList<>();

		try(Connection conn = ShopUtilities.getConnection()){

			//sql for select all payments that belongs to current customer ID.
			String sql = "SELECT * FROM shop_payments WHERE customer_ID = ? and payment_status = 'waiting' order by payment_id";
			PreparedStatement ps = conn.prepareStatement(sql);

			//set user's id to select
			ps.setInt(1, c.getCustomerID());

			//print the result from sql to ResultSet
			ResultSet rs = ps.executeQuery();

			//call constructor to create an Customer object
			while(rs.next()) {
				payments.add(new Payment(
						rs.getInt("payment_ID"),
						rs.getInt("customer_ID"),
						rs.getInt("item_ID"),
						rs.getString("payment_status"),
						rs.getDouble("payment_amount")
						));
			}


		} catch (SQLException e) {
			
			e.printStackTrace();
		}	

		return payments;
		
	}

	public List<Payment> viewAllPayments(Customer c) {
		
		List<Payment> payments = new ArrayList<>();

		try(Connection conn = ShopUtilities.getConnection()){

			//sql for select all payments that belongs to current customer ID.
			String sql = "SELECT * FROM shop_payments WHERE customer_ID = ? order by payment_id";
			PreparedStatement ps = conn.prepareStatement(sql);

			//set user's id to select
			ps.setInt(1, c.getCustomerID());

			//print the result from sql to ResultSet
			ResultSet rs = ps.executeQuery();

			//call constructor to create an Customer object
			while(rs.next()) {
				payments.add(new Payment(
						rs.getInt("payment_ID"),
						rs.getInt("customer_ID"),
						rs.getInt("item_ID"),
						rs.getString("payment_status"),
						rs.getDouble("payment_amount")
						));
			}


		} catch (SQLException e) {
			
			e.printStackTrace();
		}	

		return payments;
		
	}

}
