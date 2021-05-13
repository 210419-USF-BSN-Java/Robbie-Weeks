package com.ERS.delegates;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ERS.models.User;
import com.ERS.repository.UserDao;
import com.ERS.repository.UserDaoImp;
import com.ERS.service.Salt;

public class UserDelegate {
	
	private UserDao ud = new UserDaoImp();
	private Salt salt = new Salt();
	
	public void verifyCredential(HttpServletRequest request, HttpServletResponse response) throws IOException {
		User u;
		
		String userName = request.getParameter("username");
		String passWord = request.getParameter("password"); 
		
		String[] passAndSalt = ud.getHashAndSalt(userName);
		
		boolean verifyResult = salt.verifyHashedPass(passWord, passAndSalt[0], passAndSalt[1]);
		
		if(verifyResult == true) {
			u = ud.getUserInfo(userName);
			String token = u.getUserName() + ":" + u.getUserRole();
			response.setStatus(200);
			response.setHeader("Authorization", token);
		} else {
			response.sendError(401);
		}
		
		
	}
	
	public boolean isAuthorized(HttpServletRequest request) {
		String authToken = request.getHeader("Authorization");
		// check to see if there is an auth header
		if (authToken != null) {
			String[] tokenArr = authToken.split(":");
			// if the token is formatted the way we expect, we can take the id from it and
			// query for that user
			if (tokenArr.length == 2) {
				String userName = tokenArr[0];
				String userRole = tokenArr[1];

				// check to see if there is a valid user and if that user is the appropriate
				// role in their token
				User u = ud.getUserInfo(userName);
				if (u != null && u.getUserRole() == Integer.parseInt(userRole)) {
					return true;
				}

			}
		}
		return false;
	}
	
	
}
	
