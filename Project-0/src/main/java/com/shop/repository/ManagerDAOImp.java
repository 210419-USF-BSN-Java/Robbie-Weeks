package com.shop.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shop.model.Employee;
import com.shop.model.Item;
import com.shop.model.Payment;
import com.shop.model.User;
import com.shop.util.ShopUtilities;

public class ManagerDAOImp implements ManagerDAO {

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

	public List<Payment> viewSalesHistory() {
		
		List<Payment> payments = new ArrayList<>();

		try(Connection conn = ShopUtilities.getConnection()){

			//sql for select all payments from database.
			String viewSales = "SELECT * FROM shop_payments WHERE payment_status = 'completed' ORDER BY payment_id";
			PreparedStatement ps = conn.prepareStatement(viewSales);

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
