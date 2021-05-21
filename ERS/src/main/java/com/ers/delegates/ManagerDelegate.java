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
	
	private int managerID = 1;
	
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
		
		//use jackson to parse the user object and add to request:
		String pendingJson = null;
		try {
			pendingJson = om.writeValueAsString(pendingList);
			System.out.println(pendingJson);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		response.setStatus(200);
		response.setHeader("pendingReim", pendingJson);
	}
	
	public void viewAllResolved(HttpServletRequest request, HttpServletResponse response) {
		
		List<Reimbursment> resolvedList = md.viewAllResolved();
		
		//use jackson to parse the user object and add to request:
		String resolvedJson = null;
		try {
			resolvedJson = om.writeValueAsString(resolvedList);
			System.out.println(resolvedJson);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.setStatus(200);
		response.setHeader("solvedReim", resolvedJson);//parsed user object
	}
	
	public void viewAllEmployee(HttpServletRequest request, HttpServletResponse response) {
		
		List<User> employeeList = ud.viewAllEmployee();
		
		//use jackson to parse the user object and add to request:
		String allEmployee = null;
		try {
			allEmployee = om.writeValueAsString(employeeList);
			System.out.println(allEmployee);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.setStatus(200);
		response.setHeader("allEmployee", allEmployee);//parsed user object
	}

}
