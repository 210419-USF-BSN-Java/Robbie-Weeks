package com.ers.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ers.delegates.EmployeeDelegate;
import com.ers.delegates.ManagerDelegate;
import com.ers.delegates.UserDelegate;

public class RequestHelper {
	
	UserDelegate ude = new UserDelegate();
	
	public void processGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EmployeeDelegate ede = new EmployeeDelegate(request);
		ManagerDelegate mde = new ManagerDelegate(request);
		
		String path = request.getServletPath();
		
		switch(path) {
		case "/viewPending":
			ede.viewPending(request, response);
			break;
		case "/viewResolved":
			ede.viewSolved(request, response);
			break;
		case "/getInfo":
			ede.viewInfo(request, response);
			break;
		case "/viewAllPending":
			mde.viewAllPending(request, response);
			break;
		case "/viewAllResolved":
			mde.viewAllResolved(request, response);
			break;
		case "/viewAllEmployee":
			mde.viewAllEmployee(request, response);
			break;
		case "/viewAllRquestById":
			mde.viewAllRquestById(request, response);;
			break;
		default:
			response.sendError(405);
		}
	}
	
	
	public void processPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		EmployeeDelegate ede = new EmployeeDelegate(request);
		ManagerDelegate mde = new ManagerDelegate(request);
		
		String path = request.getServletPath();
		
		switch(path) {
		case "/login":
			ude.verifyCredential(request, response);
			break;
		case "/submitReim":
			ede.addReim(request, response);
			break;
		case "/updateInfo":
			ede.updateInfo(request, response);
			break;
		case "/reimAction":
			mde.requestAction(request, response);
			break;
		default:
			response.sendError(405);
		}
	}
}
