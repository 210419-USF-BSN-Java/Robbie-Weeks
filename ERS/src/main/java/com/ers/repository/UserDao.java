package com.ers.repository;

import java.util.List;

import com.ers.models.User;

public interface UserDao {
	
	//verify username and password from database.
	public User getUserInfo(String userName);
	
	//register an user.
	public boolean registUserAccount(User u);

	public String[] getHashAndSalt(String userName);
	
	public List<User> viewAllEmployee();
}
