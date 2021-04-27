package com.shop.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import com.shop.model.Customer;
import com.shop.model.Employee;
import com.shop.util.ShopUtilities;



public class UserDAOImp implements UserDAO{

	public Customer verifyCredential(Customer c) {
		
		//Check if the user's credential matches in the customer database
		try(Connection conn = ShopUtilities.getConnection()){
			
			
			//select user_id if the credential exist in database
			String sql = "SELECT customer_id, customer_first_name, customer_last_name FROM shop_customer_account where customer_username = ? and customer_password = ?";
			PreparedStatement ps = conn.prepareStatement(sql);

			//set user's login credential to verify
			ps.setString(1, c.getUserName());
			ps.setString(2, c.getPassWord());


			//add result into resultset 
			ResultSet rs = ps.executeQuery();

			//pass the customer information from resultset to customer object c.
			if(rs != null) {
				while(rs.next()) { 
					c.setCustomerID(rs.getInt("customer_id"));
					c.setFirstName(rs.getString("customer_first_name"));
					c.setLastName(rs.getString("customer_last_name"));
				} 
			} else {
				System.out.println("Invalid credential.");
			}


		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		
		return c;
		
	}
	
	public Employee verifyCredential(Employee emp) {
		
		//Check if the user's credential matches in the employee database.
		try(Connection conn = ShopUtilities.getConnection()){
			
			
			//select user_id if the credential exist in database
			String sql = "SELECT employee_id, employee_first_name, employee_last_name FROM shop_customer_account where customer_username = ? and customer_password = ?";
			PreparedStatement ps = conn.prepareStatement(sql);

			//set user's login credential to verify
			ps.setString(1, emp.getUserName());
			ps.setString(2, emp.getPassWord());


			//add result into resultset 
			ResultSet rs = ps.executeQuery();

			//pass the customer information from resultset to customer object c.
			if(rs != null) {
				while(rs.next()) { 
					emp.setEmployeeID(rs.getInt("employee_id"));
					emp.setFirstName(rs.getString("employee_first_name"));
					emp.setLastName(rs.getString("employee_last_name"));
				} 
			} else {
				System.out.println("Invalid credential.");
			}


		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		
		return emp;
		
	}

	public boolean checkUserName(String userName) {
		
		// create a list of existing usernames.
				Set<String> userNames = new HashSet<>();  


				//Check if application's username is already existed in database
				try(Connection conn = ShopUtilities.getConnection()){

					//
					String sql = "SELECT user_username FROM shop_customer_account";
					PreparedStatement ps = conn.prepareStatement(sql);

					//add all usernames into resultset 
					ResultSet rs = ps.executeQuery();

					//pass all username from resultset to userNames List
					while(rs.next()) { 
						userNames.add(rs.getString("user_username"));
					}

				} catch (SQLException e) {
					
					e.printStackTrace();
				}

				if(userNames.contains(userName)) {
					return true;
				} else {
					return false;
				}
	}

	public boolean registUserAccount(Customer c) {
		
		boolean success = false;
		
		PreparedStatement ps;
		
		try(Connection conn = ShopUtilities.getConnection()){
			
			//SQL statement to insert a new customer into shop_customer_account table.
			String regist = "insert into shop_customer_account (customer_username, customer_password, first_name, last_name) values (?,?,?,?);";
			ps = conn.prepareStatement(regist);
			
			//set the values
			ps.setString(1, c.getUserName());
			ps.setString(2, c.getPassWord());
			ps.setString(3, c.getFirstName());
			ps.setString(4, c.getLastName());
			
			//check if the sql statement effect the database.
			if(ps.executeUpdate() == 1) {
				success = true;
			} else {
				System.out.println("Failed to register an account! Please contact our customer representitive for more information.");
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return success;
	}

}
