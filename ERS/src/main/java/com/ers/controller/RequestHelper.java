package com.ers.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ers.delegates.UserDelegate;

public class RequestHelper {
	
	UserDelegate ude = new UserDelegate();
	
	public void processGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// determine if this is a record based request
//		String path = request.getServletPath();
//		if(path.startsWith("/api/")) {
//			// we will authenticate the token here
//			if(!authDelegate.isAuthorized(request)) {
//				response.sendError(401);
//				return;
//			}
//			
//			String record = path.substring(5);
//			if(record.startsWith("users")) {
//				userDelegate.getUsers(request, response);
//			} else {
//				response.sendError(404, "Request Record(s) Not Found");	
//			}
//			
//		} else {
//			viewDelegate.resolveView(request, response);
//		}
		System.out.println("inside request helper: doGet");
		
		String path = request.getServletPath();
		System.out.println(path);
		
		switch(path) {
		case "/viewPending":
			
			break;
		case "/viewResolved":
			
			break;
		case "/viewInfo":
			
			break;
		case "/viewAllPending":
			
			break;
		case "/viewAllResolved":
			
			break;
		case "/viewAllEmployee":
			
			break;
		case "/viewReimById":
			
			break;
		default:
			response.sendError(405);
		}
	}
	
	
	public void processPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		System.out.println("inside request helper: doPost");
		
		String path = request.getServletPath();
		System.out.println(path);
		
		switch(path) {
		case "/login":
			ude.verifyCredential(request, response);
			break;
		case "/addReim":
			
			break;
		case "/updateInfo":
			
			break;
		case "/reimAction":
			
			break;
		default:
			response.sendError(405);
		}
	}
}
