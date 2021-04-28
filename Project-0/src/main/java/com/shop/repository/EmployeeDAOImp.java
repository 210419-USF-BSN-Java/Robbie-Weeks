package com.shop.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.shop.model.Item;
import com.shop.model.Payment;
import com.shop.util.ShopUtilities;

public class EmployeeDAOImp implements EmployeeDAO{

	public boolean addItem(Item i) {
		
		boolean success = false;
		
		PreparedStatement ps;
		
		try(Connection conn = ShopUtilities.getConnection()){

			//SQL statement to insert a new account into shop_customer table.
			String addItem = "insert into shop_items (item_name, item_description, item_status, item_minimum_price) values (?,?,?,?);";
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
		
		
		return success;
		
	}

	public boolean offerAction(int offerID, String action) {
		
		boolean success = false;
		
		try(Connection conn = ShopUtilities.getConnection();) {
			String setStatus = "update shop_offers set offer_status = ? where offer_id = ?;";
			PreparedStatement ps = conn.prepareStatement(setStatus);
			ps.setString(1, action);
			ps.setInt(2, offerID);
			
			if(ps.executeUpdate() == 1) {
				success = true;
			} 
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return success;
	}

	public boolean removeItem(int itemID) {
		
		boolean success = false;
		
		try(Connection conn = ShopUtilities.getConnection();) {
			//need to use delete SQL
			String deleteItem = "delete from shop_items where item_ID = ?;";//need to validate the syntax.
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

	public List<Payment> viewAllPayments() {
		
		List<Payment> payments = new ArrayList<>();

		try(Connection conn = ShopUtilities.getConnection()){

			//sql for select all payments from database.
			String sql = "SELECT * FROM shop_payments order by payment_id";
			PreparedStatement ps = conn.prepareStatement(sql);

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
