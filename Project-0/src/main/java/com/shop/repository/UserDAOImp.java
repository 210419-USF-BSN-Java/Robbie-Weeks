package com.shop.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import com.shop.model.User;
import com.shop.util.ShopUtilities;



public class UserDAOImp implements UserDAO{
	
	@Override
	public User verifyCredential(User u) {
		
		//Check if the user's credential matches in the database record.
		try(Connection conn = ShopUtilities.getConnection()){
			
			//select user information from database if the credential matches the record.
			String sql = "SELECT user_id, first_name, last_name, user_type FROM shop_user where user_name = ? and user_password = ?";
			PreparedStatement ps = conn.prepareStatement(sql);

			//set user's login credential to verify
			ps.setString(1, u.getUserName());
			ps.setString(2, u.getPassWord());


			//add result into resultset 
			ResultSet rs = ps.executeQuery();

			//pass the user information from resultset to an User object.
			if(rs != null) {
				while(rs.next()) { 
					u.setUserID(rs.getInt("user_id"));
					u.setFirstName(rs.getString("first_name"));
					u.setLastName(rs.getString("last_name"));
					u.setUserType(rs.getString("user_type"));
				} 
			} //else {
				//System.out.println("Invalid credential.");
			//}


		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		
		return u;
		
	}
	
	@Override
	public boolean checkUserName(String userName) {
		
		// create a list of existing usernames.
		Set<String> userNames = new HashSet<>();  


		//Check if application's username is already existed in database
		try(Connection conn = ShopUtilities.getConnection()){

			//select all existing usernames.
			String sql = "SELECT user_name FROM shop_user";
			PreparedStatement ps = conn.prepareStatement(sql);

			//add all usernames into resultset 
			ResultSet rs = ps.executeQuery();

			//pass all username from resultset to userNames List
			while(rs.next()) { 
				userNames.add(rs.getString("user_name"));
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
	
	@Override
	public boolean registUserAccount(User u, String userType) {
		
		boolean success = false;
		
		PreparedStatement ps;
		
		try(Connection conn = ShopUtilities.getConnection()){
			
			//SQL statement to insert a new user record into shop_user table.
			String regist = "insert into shop_user (user_name, user_password, first_name, last_name, user_type) values (?,?,?,?,?);";
			ps = conn.prepareStatement(regist);
			
			//set the values
			ps.setString(1, u.getUserName());
			ps.setString(2, u.getPassWord());
			ps.setString(3, u.getFirstName());
			ps.setString(4, u.getLastName());
			ps.setString(5, userType);
			
			//check if the sql statement effect the database.
			if(ps.executeUpdate() == 1) {
				success = true;
			} else {
				System.out.println("Failed to register an account! Please contact our tech representitive for more information.");
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return success;
	}

}
