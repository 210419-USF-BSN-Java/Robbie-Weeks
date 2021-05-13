package com.ERS.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ERS.models.Employee;
import com.ERS.models.Reimbursment;
import com.ERS.models.User;
import com.ERS.util.ErsUtil;

public class EmployeeDaoImp implements EmployeeDao{

	@Override
	public User viewInfo(int userID) {
		User u = new User();

		try(Connection conn = ErsUtil.getConnection()){

			//sql for select all awaiting payments that belongs to current customer ID.
			String sql = "SELECT * FROM ers_user WHERE ers_user_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);

			//set user's id to select.
			ps.setInt(1, userID);

			//print the result from ResultSet.
			ResultSet rs = ps.executeQuery();
			
//			if(rs == null) {
//				return null;
//			}

			//add payment objects into a payment list.
			while(rs.next()) {
				u = new User(
					rs.getInt("ers_user_id"),
					rs.getString("ers_username"),
					rs.getString("ers_password"),
					rs.getString("first_name"),
					rs.getString("last_name"),
					rs.getInt("user_role"),
					rs.getString("user_email"),
					rs.getString("user_salt")
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

}
