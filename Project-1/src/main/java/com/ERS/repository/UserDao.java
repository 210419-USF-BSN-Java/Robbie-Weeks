package com.ERS.repository;

import com.ERS.models.User;

public interface UserDao {
	
	//verify username and password from database.
	public User getUserInfo(String userName);
	
	//check if the username is already existing in database.
	public boolean checkUserName(String userName);
	
	//register an user.
	public boolean registUserAccount(User u);

	public String[] getHashAndSalt(String userName);
}
