package com.ers.delegates;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ers.models.Reimbursment;
import com.ers.models.User;
import com.ers.repository.ManagerDao;
import com.ers.repository.ManagerDaoImp;

import com.ers.repository.UserDao;
import com.ers.repository.UserDaoImp;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ManagerDelegate {
	
	private ManagerDao md = new ManagerDaoImp();
	private UserDao ud = new UserDaoImp();
	private ObjectMapper om = new ObjectMapper();
	
	private int managerID;
	
	public ManagerDelegate(HttpServletRequest request) {
		fetchSession(request);
	}
	
	public void fetchSession(HttpServletRequest request) {
		
		String token = request.getHeader("Authorization");
		
		if(token != null) {
			String[] userInfo = token.split(":");
			managerID = Integer.parseInt(userInfo[0]);
		}
	}
	
	public void requestAction(HttpServletRequest request, HttpServletResponse response) {
		
		int reimID = Integer.parseInt(request.getParameter("reimID"));
		int action = Integer.parseInt(request.getParameter("action"));
		
		boolean success = md.requestAction(reimID, action, managerID);
		
		if(success == true) {
			response.setStatus(200);
		}
		
	}

	
	public void viewAllPending(HttpServletRequest request, HttpServletResponse response) {
		
		List<Reimbursment> pendingList = md.viewAllPending();
		
		String pendingJson = null;
		try {
			pendingJson = om.writeValueAsString(pendingList);

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		response.setStatus(200);
		response.setHeader("pendingList", pendingJson);
	}
	
	public void viewAllResolved(HttpServletRequest request, HttpServletResponse response) {
		
		List<Reimbursment> resolvedList = md.viewAllResolved();
		
		String resolvedJson = null;
		try {
			resolvedJson = om.writeValueAsString(resolvedList);
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		response.setStatus(200);
		response.setHeader("resolvedList", resolvedJson);
	}
	
	public void viewAllEmployee(HttpServletRequest request, HttpServletResponse response) {
		
		List<User> employeeList = ud.viewAllEmployee();
		
		String allEmployee = null;
		try {
			allEmployee = om.writeValueAsString(employeeList);
			System.out.println(allEmployee);
			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.setStatus(200);
		response.setHeader("employeeList", allEmployee);
	}
	
	public void viewAllRquestById(HttpServletRequest request, HttpServletResponse response) {
		
		int employeeID = Integer.parseInt(request.getHeader("employeeID"));
		
		List<Reimbursment> requestList = md.viewAllRequestById(employeeID);
		
		String requestJson = null;
		try {
			requestJson = om.writeValueAsString(requestList);
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		response.setStatus(200);
		response.setHeader("requestList", requestJson);
		
	}

}
