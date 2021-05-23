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

		double amount = Double.parseDouble(request.getParameter("amount"));
		String type = request.getParameter("type");
		String description = request.getParameter("description");
		int typeID = 0;
		
		switch (type) {
		case "Lodging":
			typeID = 1;
			break;
		case "Travel":
			typeID = 2;
			break;
		case "Food":
			typeID = 3;
			break;
		case "Other":
			typeID = 4;
			break;
		}
		
		Reimbursment reim = new Reimbursment(amount, description, userID, 1, typeID);
	
		ed.makeReim(reim);
	}
	
	public void viewPending(HttpServletRequest request, HttpServletResponse response) {
			
		List<Reimbursment> pendingList = ed.viewPending(userID);
		
		String pendingJson = null;
		try {
			pendingJson = mapper.writeValueAsString(pendingList);
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		response.setStatus(200);
		response.setHeader("pendingList", pendingJson);
		
	}
	
	public void viewSolved(HttpServletRequest request, HttpServletResponse response) {
			
		List<Reimbursment> resolvedList = ed.viewPending(userID);
		
		String resolvedJson = null;
		try {
			resolvedJson = mapper.writeValueAsString(resolvedList);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		response.setStatus(200);
		response.setHeader("resolvedList", resolvedJson);

	}
	
	public void viewInfo(HttpServletRequest request, HttpServletResponse response) {

		User u = ed.viewInfo(userID);
	
		String userJson = null;
		try {
			userJson = mapper.writeValueAsString(u);

		} catch (JsonProcessingException e) {
			
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
