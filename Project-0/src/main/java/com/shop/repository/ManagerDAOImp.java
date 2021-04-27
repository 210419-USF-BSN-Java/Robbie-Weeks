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
import com.shop.util.ShopUtilities;

public class ManagerDAOImp implements ManagerDAO {

	public boolean makeEmployeeAccount(Employee emp) {
		
		boolean success = false;
		
		PreparedStatement ps;
		
		try(Connection conn = ShopUtilities.getConnection()){

			//SQL statement to insert a new account into shop_employee table.
			String addEmployee = "insert into shop_employee (employee_username, employee_password, employee_firstname, employee_lastname) values (?,?,?,?);";
			ps = conn.prepareStatement(addEmployee);

			//set the values
			ps.setString(1, emp.getUserName());
			ps.setString(2, emp.getPassWord());
			ps.setString(3, emp.getFirstName());
			ps.setString(4, emp.getLastName());
			
			if(ps.executeUpdate() == 1) {
				System.out.println("You have successfully created an employee account!");
				
				success = true;
			} else {
				System.out.println("Failed to create an employee account! Please contact our customer representitive for more information.");
			}

		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		return success;
	}

	public boolean deleteEmployeeAccount(int employeeID) {
		
		boolean success = false;
		
		try(Connection conn = ShopUtilities.getConnection();) {
			//need to use delete SQL
			String deleteEmployee = "delete from shop_employee where employee_ID = ?;";
			PreparedStatement ps = conn.prepareStatement(deleteEmployee);
			ps.setInt(1, employeeID);
			
			if(ps.executeUpdate() == 1) {
				System.out.println("You have successfully deleted an employee account!");
				success = true;
			} else {
				System.out.println("The employee ID does not exist, please enter another item ID.");
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
			String viewSales = "SELECT * FROM shop_payments where payment_status = 'completed' order by payment_id";
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
