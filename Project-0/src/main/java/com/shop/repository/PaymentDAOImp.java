package com.shop.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shop.model.Payment;
import com.shop.model.User;
import com.shop.util.ShopUtilities;

public class PaymentDAOImp implements PaymentDAO{

	@Override
	public boolean createPayment(Payment p) {
		boolean success = false;
		
		PreparedStatement ps;
		
		try(Connection conn = ShopUtilities.getConnection()){

			//SQL statement to insert payment record into shop_payment table.
			String addItem = "INSERT INTO shop_payments (item_id, payment_status, payment_amount, customer_id, remaining_payment, remaining_terms) VALUES (?,?,?,?,?,?);";
			ps = conn.prepareStatement(addItem);

			//set the values
			ps.setInt(1, p.getItemID());
			ps.setString(2, p.getPaymentStatus());
			ps.setDouble(3, p.getPaymentAmount());
			ps.setInt(4, p.getCustomerID());
			ps.setDouble(5, p.getRemainPayment());
			ps.setInt(6, p.getRemainTerms());
			
			if(ps.executeUpdate() == 1) {
				
				success = true;
			} 

		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return success;
	}

	@Override
	public boolean payWeeklyPayment() {
		boolean success = false;
		
		PreparedStatement ps;
		
		try(Connection conn = ShopUtilities.getConnection()){

			//SQL to update remaining payment and terms column, reduce the remaining amount and terms.
			String pay = "UPDATE shop_payments SET (remaining_payment, remaining_terms)"
					+ " = (remaining_payment - payment_amount/5, remaining_terms - 1)"
					+ " WHERE remaining_terms > 0;";
			ps = conn.prepareStatement(pay);
		
			ps.executeUpdate();
			
			//SQL to update payment status when the payment is complete(after the weekly payment is made).
			String complete = "UPDATE shop_payments SET payment_status = 'Complete' WHERE remaining_payment = 0;";
			ps = conn.prepareStatement(complete);
			ps.executeUpdate();
			
			success = true;

		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return success;
	}
	
	@Override
	public List<Payment> viewRemainPayments(User u) {
		
		List<Payment> payments = new ArrayList<>();

		try(Connection conn = ShopUtilities.getConnection()){

			//sql for select all awaiting payments that belongs to current customer ID.
			String sql = "SELECT * FROM shop_payments WHERE customer_ID = ? AND payment_status = 'Awaiting' ORDER BY payment_id";
			PreparedStatement ps = conn.prepareStatement(sql);

			//set user's id to select.
			ps.setInt(1, u.getUserID());

			//print the result from ResultSet.
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

		return payments;
		
	}
	
	@Override
	public List<Payment> viewAllPayments(User u) {
		
		List<Payment> payments = new ArrayList<>();

		try(Connection conn = ShopUtilities.getConnection()){

			//sql for select all payments that belongs to current customer ID.
			String sql = "SELECT * FROM shop_payments WHERE customer_ID = ? ORDER BY payment_id";
			PreparedStatement ps = conn.prepareStatement(sql);

			//set user's id to select.
			ps.setInt(1, u.getUserID());

			//print the result to ResultSet.
			ResultSet rs = ps.executeQuery();

			////add payment objects into a payment list.
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

		return payments;
		
	}

}
