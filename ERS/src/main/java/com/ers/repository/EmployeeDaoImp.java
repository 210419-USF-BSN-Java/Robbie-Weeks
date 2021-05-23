package com.ers.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ers.models.Reimbursment;
import com.ers.models.User;
import com.ers.util.ErsUtil;

public class EmployeeDaoImp implements EmployeeDao{

	@Override
	public User viewInfo(int userID) {
		User u = new User();

		try(Connection conn = ErsUtil.getConnection()){

			String sql = "SELECT * FROM ers_user WHERE ers_user_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);

			//set user's id to select.
			ps.setInt(1, userID);

			//print the result from ResultSet.
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				u = new User(
					rs.getInt("ers_user_id"),
					rs.getString("first_name"),
					rs.getString("last_name"),
					rs.getInt("user_role"),
					rs.getString("user_email")
						);
			}


		} catch (SQLException e) {
			
			e.printStackTrace();
		}	

		return u;
	}

	@Override
	public boolean updateInfo(User u) {
		boolean success = false;
		
		PreparedStatement ps;
		
		try(Connection conn = ErsUtil.getConnection()){

			//SQL to update columns of an existing item.
			String edit = "UPDATE ers_user SET (first_name, last_name, user_email) = (?,?,?) WHERE ers_user_id = ?;";
			ps = conn.prepareStatement(edit);
			
			//set the values
			ps.setString(1, u.getFirstName());
			ps.setString(2, u.getLastName());
			ps.setString(3, u.getEmail());
			ps.setInt(4, u.getUserID());
		
			if(ps.executeUpdate() == 1) {
				
				success = true;
			} 

		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return success;
	}

	@Override
	public boolean makeReim(Reimbursment r) {
		boolean success = false;
		
		PreparedStatement ps;
		
		try(Connection conn = ErsUtil.getConnection()){

			//SQL statement to insert a new reimbursement record into ers_reimbursement table.
			String reim = "INSERT INTO ers_reimbursement (reim_amount, reim_description, "
					+ "reim_receipt, reim_author, reim_resolver, reim_status_id, reim_type_id) "
					+ "VALUES (?,?,?,?,?,?,?);";
			ps = conn.prepareStatement(reim);

			//set the values
			ps.setDouble(1, r.getAmount());
			ps.setString(2, r.getDescription());
			ps.setString(3, r.getReceipt());
			ps.setInt(4, r.getAuthorID());
			ps.setInt(5, r.getResolverID());
			ps.setInt(6, r.getStatusID());
			ps.setInt(7, r.getTypeID());
			
			if(ps.executeUpdate() == 1) {
				success = true;
			} 

		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return success;
	}

	@Override
	public List<Reimbursment> viewPending(int userID) {
		List<Reimbursment> Reimbursments = new ArrayList<>();

		try(Connection conn = ErsUtil.getConnection()){

			String sql = "SELECT * FROM ers_reimbursement WHERE reim_author = ? AND reim_status_id = '1' ORDER BY reim_id";
			PreparedStatement ps = conn.prepareStatement(sql);

			//set user's id to select.
			ps.setInt(1, userID);

			//print the result from ResultSet.
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				Reimbursments.add(new Reimbursment(
						rs.getInt("reim_id"),
						rs.getDouble("reim_amount"),
						rs.getString("reim_submitted"),
						rs.getString("reim_resolved"),
						rs.getString("reim_description"),
						rs.getString("reim_receipt"),
						rs.getInt("reim_author"),
						rs.getInt("reim_resolver"),
						rs.getInt("reim_status_id"),
						rs.getInt("reim_type_id")
						));
			}


		} catch (SQLException e) {
			
			e.printStackTrace();
		}	

		return Reimbursments;
	}

	@Override
	public List<Reimbursment> viewResolved(int userID) {
		List<Reimbursment> Reimbursments = new ArrayList<>();

		try(Connection conn = ErsUtil.getConnection()){

			String sql = "SELECT * FROM ers_reimbursement WHERE reim_author = ? AND reim_status_id != '1' ORDER BY reim_id";
			PreparedStatement ps = conn.prepareStatement(sql);

			//set user's id to select.
			ps.setInt(1, userID);

			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				Reimbursments.add(new Reimbursment(
						rs.getInt("reim_id"),
						rs.getDouble("reim_amount"),
						rs.getString("reim_submitted"),
						rs.getString("reim_resolved"),
						rs.getString("reim_description"),
						rs.getString("reim_receipt"),
						rs.getInt("reim_author"),
						rs.getInt("reim_resolver"),
						rs.getInt("reim_status_id"),
						rs.getInt("reim_type_id")
						));
			}


		} catch (SQLException e) {
			
			e.printStackTrace();
		}	

		return Reimbursments;
	}

	@Override
	public List<Reimbursment> viewAllRequest(int userID) {
		List<Reimbursment> Reimbursments = new ArrayList<>();

		try(Connection conn = ErsUtil.getConnection()){

			//sql for select all awaiting payments that belongs to current customer ID.
			String sql = "SELECT * FROM ers_reimbursement WHERE reim_author = ? ORDER BY reim_id";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, userID);

			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				Reimbursments.add(new Reimbursment(
						rs.getInt("reim_id"),
						rs.getDouble("reim_amount"),
						rs.getString("reim_submitted"),
						rs.getString("reim_resolved"),
						rs.getString("reim_description"),
						rs.getString("reim_receipt"),
						rs.getInt("reim_author"),
						rs.getInt("reim_resolver"),
						rs.getInt("reim_status_id"),
						rs.getInt("reim_type_id")
						));
			}


		} catch (SQLException e) {
			
			e.printStackTrace();
		}	

		return Reimbursments;
	}

}
