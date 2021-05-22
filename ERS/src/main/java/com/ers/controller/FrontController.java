package com.ers.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.servlets.DefaultServlet;

import com.ers.delegates.UserDelegate;

public class FrontController extends DefaultServlet{
	
	RequestHelper rh = new RequestHelper();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		String path = request.getServletPath();
//		if (path.startsWith("/static/")) {
//			super.doGet(request, response);
//		} else {
			rh.processGet(request, response);
//		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("inside dopost");
		rh.processPost(request, response);

	}
}
