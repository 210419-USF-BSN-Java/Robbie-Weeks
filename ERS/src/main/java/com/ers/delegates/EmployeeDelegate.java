package com.ers.delegates;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ers.models.Reimbursment;
import com.ers.models.User;
import com.ers.repository.EmployeeDao;
import com.ers.repository.EmployeeDaoImp;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class EmployeeDelegate {
	
	private EmployeeDao ed = new EmployeeDaoImp();
	ObjectMapper mapper = new ObjectMapper();
	
	private int userID;
	
	public EmployeeDelegate(HttpServletRequest request) {
		fetchSession(request);
	}
	
	public void fetchSession(HttpServletRequest request) {
		
		String token = request.getHeader("Authorization");
		
		if(token != null) {
			String[] userInfo = token.split(":");
			userID = Integer.parseInt(userInfo[0]);
		}
	}
	
	public void addReim(HttpServletRequest request, HttpServletResponse response) {

		String reimRequest = request.getHeader("reimRequest");
		Reimbursment reim = null;
		
		try {
			reim = mapper.readValue(reimRequest, Reimbursment.class);
			reim.setAuthorID(userID);// authorID
			reim.setStatusID(1);// 1 = Pending
			
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		boolean success = ed.makeReim(reim);
		System.out.println(reim);
		
	}
	
	public void viewPending(HttpServletRequest request, HttpServletResponse response) {
			
		List<Reimbursment> pendingList = ed.viewPending(userID);
		System.out.println(pendingList);
		
		String pendingJson = null;
		try {
			pendingJson = mapper.writeValueAsString(pendingList);
			System.out.println(pendingJson);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		response.setStatus(200);
		response.setHeader("pendingReim", pendingJson);
		
	}
	
	public void viewSolved(HttpServletRequest request, HttpServletResponse response) {
			
		List<Reimbursment> resolvedList = ed.viewPending(userID);
		
		//use jackson to parse the user object and add to request:
		String resolvedJson = null;
		try {
			resolvedJson = mapper.writeValueAsString(resolvedList);
			System.out.println(resolvedJson);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.setStatus(200);
		response.setHeader("resolvedReim", resolvedJson);

	}
	
	public void viewInfo(HttpServletRequest request, HttpServletResponse response) {

		User u = ed.viewInfo(userID);
			
		//use jackson to parse the user object and add to request:
		String userJson = null;
		try {
			userJson = mapper.writeValueAsString(u);
			System.out.println(userJson);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.setStatus(200);
		response.setHeader("userInfo", userJson);
		
	}
	
	public void updateInfo(HttpServletRequest request, HttpServletResponse response) {

		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		
		User u = new User(userID, firstName, lastName, email);
		System.out.println(u);
		boolean success = ed.updateInfo(u);
		
		if(success == true) {
			response.setStatus(200);
		}
		
	}
}
