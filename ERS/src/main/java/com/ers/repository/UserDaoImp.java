package com.ers.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.ers.models.User;
import com.ers.util.ErsUtil;


public class UserDaoImp implements UserDao{
	
	public String[] getHashAndSalt(String userName) {
		String[] hashAndSalt = new String[2];
		System.out.println("Inside UserDaoImp");
		try(Connection conn = ErsUtil.getConnection()){
			
			String getHash = "SELECT ers_password, user_salt FROM ers_user WHERE ers_username = ?";
			PreparedStatement ps = conn.prepareStatement(getHash);
			
			//set user's login credential to verify
			ps.setString(1, userName);
			
			//add result into Resultset.
			ResultSet rs = ps.executeQuery();
			System.out.println("Going to Set hashAndSalt");
			if (rs != null) {
				while (rs.next()) {
					//first index stores the hashed password, second index stores the user salt.
					hashAndSalt[0] = rs.getString("ers_password");
					hashAndSalt[1] = rs.getString("user_salt");
					System.out.println("Setting hashAndSalt");
				}
			} else {
				System.out.println("fail to set");
			}

		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		
		return hashAndSalt;
	}
	
	@Override
	public User getUserInfo(String userName) {
		User u = new User();
		//Check if the user's credential matches in the database record.
		try(Connection conn = ErsUtil.getConnection()){
			
			//select user information from database if the credential matches the record.
			String sql = "SELECT ers_user_id, first_name, last_name, user_email, user_role FROM ers_user where ers_username = ?";
			PreparedStatement ps = conn.prepareStatement(sql);

			//set user's login credential to verify
			ps.setString(1, userName);

			//add result into resultset 
			ResultSet rs = ps.executeQuery();

			//pass the user information from resultset to an User object.
			if(rs != null) {
				while(rs.next()) { 
					u.setUserID(rs.getInt("ers_user_id"));
					u.setFirstName(rs.getString("first_name"));
					u.setLastName(rs.getString("last_name"));
					u.setEmail(rs.getString("user_email"));
					u.setUserRole(rs.getInt("user_role"));
					
				} 
			}


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
		try(Connection conn = ErsUtil.getConnection()){

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
	public boolean registUserAccount(User u) {
		
		boolean success = false;
		
		PreparedStatement ps;
		
		try(Connection conn = ErsUtil.getConnection()){
			
			//SQL statement to insert a new user record into shop_user table.
			String regist = "insert into ers_user (ers_username, ers_password, first_name, last_name, user_email, user_role, user_salt)"
					+ " values (?,?,?,?,?,?,?);";
			ps = conn.prepareStatement(regist);
			
			//set the values
			ps.setString(1, u.getUserName());
			ps.setString(2, u.getPassWord());
			ps.setString(3, u.getFirstName());
			ps.setString(4, u.getLastName());
			ps.setString(5, u.getEmail());
			ps.setInt(6, u.getUserRole());
			ps.setString(7, u.getSalt());
			
			
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

	@Override
	public List<User> viewAllEmployee() {
		// TODO Auto-generated method stub
		return null;
	}

}
