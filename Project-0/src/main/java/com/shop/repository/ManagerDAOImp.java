package com.shop.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shop.model.Employee;
import com.shop.model.Offer;
import com.shop.model.Payment;
import com.shop.model.User;
import com.shop.util.ShopUtilities;

public class ManagerDAOImp implements ManagerDAO {
	@Override
	public boolean makeEmployeeAccount(Employee emp) {
		
		boolean success = false;
		
		PreparedStatement ps;
		
		try(Connection conn = ShopUtilities.getConnection()){

			//SQL statement to insert a new account into shop_employee table.
			String addEmployee = "INSERT INTO shop_employee_account (employee_username, employee_password, employee_firstname, employee_lastname) VALUES (?,?,?,?);";
			ps = conn.prepareStatement(addEmployee);

			//set the values
			ps.setString(1, emp.getUserName());
			ps.setString(2, emp.getPassWord());
			ps.setString(3, emp.getFirstName());
			ps.setString(4, emp.getLastName());
			
			if(ps.executeUpdate() == 1) {
				success = true;
			} 

		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		return success;
	}

	@Override
	public List<User> viewUsers(String userType){
		List<User> users = new ArrayList<>();
		
		try(Connection conn = ShopUtilities.getConnection()){

			String viewUsers = "SELECT * FROM shop_user WHERE user_type = ? ORDER BY user_id";
			PreparedStatement ps = conn.prepareStatement(viewUsers);
			ps.setString(1, userType);

			//print the result from sql to ResultSet
			ResultSet rs = ps.executeQuery();

			//call constructor to create an Customer object
			while(rs.next()) {
				users.add(new User(
						rs.getInt("user_id"),
						rs.getString("first_name"),
						rs.getString("last_name"),
						rs.getString("user_type")
						));
			}


		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		return users;
	}
	@Override
	public boolean deleteEmployeeAccount(int employeeID) {
		
		boolean success = false;
		
		try(Connection conn = ShopUtilities.getConnection();) {
			//need to use delete SQL
			String deleteEmployee = "DELETE FROM shop_user WHERE user_ID = ?;";
			PreparedStatement ps = conn.prepareStatement(deleteEmployee);
			ps.setInt(1, employeeID);
			
			if(ps.executeUpdate() == 1) {
				success = true;
			} 
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return success;
	}
	@Override
	public List<Offer> viewSalesHistory() {
		
		List<Offer> acceptedOffers = new ArrayList<Offer>();
		
		try(Connection conn = ShopUtilities.getConnection()){

			//sql for select all pending offers from item_offer table.
			String sql = "SELECT * FROM shop_offer WHERE offer_status = ? ORDER BY item_id";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, "Accepted");

			//print the result from sql to ResultSet
			ResultSet rs = ps.executeQuery();

			//add offer objects into offer list.
			while(rs.next()) {
				acceptedOffers.add(new Offer(
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
		return acceptedOffers;
	}

}
