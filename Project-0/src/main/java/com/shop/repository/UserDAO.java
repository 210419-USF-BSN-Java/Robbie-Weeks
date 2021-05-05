package com.shop.repository;

import com.shop.model.User;

public interface UserDAO {
	
	//verify username and password from database.
	public User getUserInfo(String userName);
	
	//check if the username is already existing in database.
	public boolean checkUserName(String userName);
	
	//register an user.
	public boolean registUserAccount(User u, String userType);

	public String[] getHashAndSalt(String userName);
	
}
