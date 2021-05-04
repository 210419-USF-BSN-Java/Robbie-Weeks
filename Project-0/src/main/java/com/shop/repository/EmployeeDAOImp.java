package com.shop.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.shop.model.Item;
import com.shop.model.Offer;
import com.shop.model.Payment;
import com.shop.util.ShopUtilities;

public class EmployeeDAOImp implements EmployeeDAO{
	
	@Override
	public boolean addItem(Item i) {
		
		boolean success = false;
		
		PreparedStatement ps;
		
		try(Connection conn = ShopUtilities.getConnection()){

			//SQL statement to insert a new item into shop_item table.
			String addItem = "INSERT INTO shop_item (item_name, item_description, item_status, item_minimum_price) VALUES (?,?,?,?);";
			ps = conn.prepareStatement(addItem);

			//set the values
			ps.setString(1, i.getItemName());
			ps.setString(2, i.getItemDescription());
			ps.setString(3, "Available");
			ps.setDouble(4, i.getItermMinimumPrice());
			
			if(ps.executeUpdate() == 1) {
				
				success = true;
			} 

		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		//return the result of SQL statement execution.
		return success;
		
	}
	
	@Override
	public List<Offer> viewPending(){
		
		List<Offer> pendingOffers = new ArrayList<Offer>();
		
		try(Connection conn = ShopUtilities.getConnection()){

			//sql for select all pending offers from item_offer table.
			String sql = "SELECT * FROM shop_offer WHERE offer_status = ? ORDER BY item_id";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, "Pending");

			//print the result from sql to ResultSet
			ResultSet rs = ps.executeQuery();

			//add offer objects into offer list.
			while(rs.next()) {
				pendingOffers.add(new Offer(
						rs.getInt("offer_id"),
						rs.getInt("customer_id"),
						rs.getInt("item_id"),
						rs.getDouble("offer_amount"),
						rs.getString("offer_status")
						));
			}


		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		//return offer list.
		return pendingOffers;
	}
	
	@Override
	public boolean offerAction(int offerID, String action) {
		
		boolean success = false;
		
		try(Connection conn = ShopUtilities.getConnection();) {
			
			//update offer status column and return the current row for later use.
			String setStatus = "UPDATE shop_offer SET offer_status = ? where offer_id = ? RETURNING *;";
			PreparedStatement ps = conn.prepareStatement(setStatus);
			ps.setString(1, action);
			ps.setInt(2, offerID);
			
			//store the modified offer row into result set.
			ResultSet rs = ps.executeQuery();
			
			if(rs != null) {
				success = true;
			} 
			
			//If the action is "Accept", then all other offers' status on this item will update to "Rejected". 
			//This item's owner and status column will also be updated.
			//Lastly, call the paymentDao method to create a new payment record for this item.
			if(action.equals("Accepted")) {
				//reject SQL that turn down all other offers.
				String reject = "UPDATE shop_offer SET offer_status = 'Rejected' where item_id = ? and offer_status = 'Pending';";
				PreparedStatement rejectOffer = conn.prepareStatement(reject);
				
				//SQL to change the ownership of item ID and customer ID.
				String owner = "UPDATE shop_item SET item_owner_id = ?, item_status = 'Owned' WHERE item_id = ?;";
				PreparedStatement ownerChange = conn.prepareStatement(owner);
				
				while(rs.next()) {
					//complete the reject SQL statement.
					rejectOffer.setInt(1, rs.getInt("item_id"));
					
					//complete the owner SQL statement.
					ownerChange.setInt(1, rs.getInt("customer_id"));
					ownerChange.setInt(2, rs.getInt("item_id"));
					
					//Fill a new payment object and call the createPayment method with this object.
					Payment p = new Payment();
					p.setItemID(rs.getInt("item_id"));
					p.setPaymentAmount(rs.getInt("offer_amount"));
					p.setCustomerID(rs.getInt("customer_id"));
					p.setRemainPayment(rs.getInt("offer_amount"));
					p.setPaymentStatus("Awaiting");
					p.setRemainTerms(5);
					
					PaymentDAO pDao = new PaymentDAOImp();
					pDao.createPayment(p);
					
					
				}
				
				//execute the reject SQL to turn down other offers.
				rejectOffer.executeUpdate();
				
				//execute the reject SQL to change the owner ID of this item.
				ownerChange.executeUpdate();

			} 
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return success;
	}
	
	@Override
	public boolean removeItem(int itemID) {
		
		boolean success = false;
		
		try(Connection conn = ShopUtilities.getConnection();) {
			//Delete an item record from shop_item table.
			String deleteItem = "DELETE FROM shop_item WHERE item_ID = ?;";
			PreparedStatement ps = conn.prepareStatement(deleteItem);
			ps.setInt(1, itemID);
			
			if(ps.executeUpdate() == 1) {
				success = true;
			} 
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return success;
		
	}
	
	@Override
	public List<Payment> viewAllPayments() {
		
		List<Payment> payments = new ArrayList<>();

		try(Connection conn = ShopUtilities.getConnection()){

			//sql for select all payments records from shop_payment table.
			String sql = "SELECT * FROM shop_payments ORDER BY payment_id";
			PreparedStatement ps = conn.prepareStatement(sql);

			//print the result to ResultSet.
			ResultSet rs = ps.executeQuery();

			//add payment objects into a payment list.
			while(rs.next()) {
				payments.add(new Payment(
						rs.getInt("payment_ID"),
						rs.getInt("customer_ID"),
						rs.getInt("item_ID"),
						rs.getString("payment_status"),
						rs.getDouble("payment_amount"),
						rs.getDouble("remaining_payment"),
						rs.getInt("remaining_terms")
						));
			}


		} catch (SQLException e) {
			
			e.printStackTrace();
		}	
		
		//return the payment list.
		return payments;
		
	}
	
	@Override
	public boolean editItem(Item i) {
		boolean success = false;
		
		PreparedStatement ps;
		
		try(Connection conn = ShopUtilities.getConnection()){

			//SQL to update columns of an existing item.
			String edit = "UPDATE shop_item SET (item_name, item_description, item_minimum_price) = (?,?,?) WHERE item_id = ?;";
			ps = conn.prepareStatement(edit);
			
			//set the values
			ps.setString(1, i.getItemName());
			ps.setString(2, i.getItemDescription());
			ps.setDouble(3, i.getItermMinimumPrice());
			ps.setInt(4, i.getItemID());
		
			if(ps.executeUpdate() == 1) {
				
				success = true;
			} 

		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return success;
	}
}
