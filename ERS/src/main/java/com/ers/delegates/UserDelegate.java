package com.ers.delegates;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ers.models.User;
import com.ers.repository.UserDao;
import com.ers.repository.UserDaoImp;
import com.ers.service.Salt;

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
			String token = u.getUserID() + ":" + u.getFirstName() + ":" + u.getUserRole();
			response.setStatus(200);
			response.setHeader("Authorization", token);
			
		} else {
			response.sendError(401);
		}	
	}
}
	
