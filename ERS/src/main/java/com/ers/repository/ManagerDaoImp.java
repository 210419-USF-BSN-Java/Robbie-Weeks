package com.ers.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ers.models.Reimbursment;
import com.ers.util.ErsUtil;

public class ManagerDaoImp implements ManagerDao{

	@Override
	public boolean requestAction(int reimID, int action, int managerID) {
		boolean success = false;
		
		try(Connection conn = ErsUtil.getConnection();) {
			
			//update offer status column and return the current row for later use.
			String setStatus = "UPDATE ers_reimbursement SET (reim_resolved, reim_resolver, reim_status_id) = (now(),?,?) where reim_id = ? RETURNING *;";
			PreparedStatement ps = conn.prepareStatement(setStatus);
			ps.setInt(1, managerID);
			ps.setInt(2, action);
			ps.setInt(3, reimID);
			
			//store the modified offer row into result set.
			ResultSet rs = ps.executeQuery();
			
			if(rs != null) {
				success = true;
			} 
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return success;
	}

	@Override
	public List<Reimbursment> viewAllPending() {
		List<Reimbursment> Reimbursments = new ArrayList<>();

		try(Connection conn = ErsUtil.getConnection()){

			//sql for select all awaiting payments that belongs to current customer ID.
			String sql = "SELECT * FROM ers_reimbursement WHERE reim_status_id = '1' ORDER BY reim_id";
			PreparedStatement ps = conn.prepareStatement(sql);

			//print the result from ResultSet.
			ResultSet rs = ps.executeQuery();

			//add payment objects into a payment list.
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
	public List<Reimbursment> viewAllResolved() {
		List<Reimbursment> Reimbursments = new ArrayList<>();

		try(Connection conn = ErsUtil.getConnection()){

			//sql for select all awaiting payments that belongs to current customer ID.
			String sql = "SELECT * FROM ers_reimbursement WHERE reim_status_id != '1'  ORDER BY reim_id";
			PreparedStatement ps = conn.prepareStatement(sql);

			//print the result from ResultSet.
			ResultSet rs = ps.executeQuery();

			//add payment objects into a payment list.
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
	public List<Reimbursment> viewAllRequestById(int userID) {
		List<Reimbursment> Reimbursments = new ArrayList<>();

		try(Connection conn = ErsUtil.getConnection()){

			//sql for select all awaiting payments that belongs to current customer ID.
			String sql = "SELECT * FROM ers_reimbursement WHERE reim_author = ?  ORDER BY reim_id";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, userID);

			//print the result from ResultSet.
			ResultSet rs = ps.executeQuery();

			//add payment objects into a payment list.
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
