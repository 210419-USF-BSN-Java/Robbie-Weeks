package com.shop.presentation;

import java.util.Scanner;

import com.shop.model.Customer;
import com.shop.model.Employee;
import com.shop.model.Manager;
import com.shop.model.User;
import com.shop.service.Service;

public class Controller {
	Customer c;
	Employee e;
	Manager m;
	Service s = new Service();
	
	String menuInput;
	
	Scanner sc = new Scanner(System.in);
	
	public void start() {
		
		System.out.println("Hi! Welcome to Jashop online shopping system, what would you like to do?:");
		mainMenu();
	}
	
	public void mainMenu() {
		
		System.out.println("1. Log in as customer.");
		System.out.println("2. Log in as employee.");
		System.out.println("3. Log in as maneger.");
		System.out.println("4. Regist for a customer account.");		
		System.out.println("Please enter the option number above.");
		menuInput = sc.nextLine();
		
		login(menuInput);
	}
	
	public void login(String menuInput) {
		
		if(menuInput.equals("1")) {
			
			//ask for user's credential input.
			System.out.println("Pleas enter your customer username:");
			String userName = sc.nextLine();
			System.out.println("Pleas enter your customer password:");
			String passWord = sc.nextLine();

			c = s.setCustomer(userName, passWord);
			
			//call veryfy method to match the login credential with database.
			c = s.verifyCustomer(c);
			
			if(c.getCustomerID() != 0) {
				customerMenu();
			} else {
				System.out.println("Your login credential does not match the database record, please enter again!");
				login(menuInput);
			}

		} else if(menuInput.equals("2")) {
			
			//ask for user's credential input.
			System.out.println("Pleas enter your employee username:");
			String userName = sc.nextLine();
			System.out.println("Pleas enter your employee password:");
			String passWord = sc.nextLine();

			e = s.setEmployee(userName, passWord);
			
			//call veryfy method to match the login credential with database.
			e = s.verifyEmployee(e);
			
			if(e.getEmployeeID() != 0) {
				employeeMenu();
			} else {
				System.out.println("Your login credential does not match the database record, please enter again!");
				login(menuInput);
			}

		} else if(menuInput.equals("3")) {
			
			//ask for user's credential input.
			System.out.println("Pleas enter your manager username:");
			String userName = sc.nextLine();
			System.out.println("Pleas enter your manager password:");
			String passWord = sc.nextLine();

			m = s.setManager(userName, passWord);
			
			//call veryfy method to match the login credential with database.
			e = s.verifyEmployee(e);
			
			if(e.getEmployeeID() != 0) {
				employeeMenu();
			} else {
				System.out.println("Your login credential does not match the database record, please enter again!");
				login(menuInput);
			}

		} else if(menuInput.equals("4")){
			
			//call register method to register an account.
			boolean success = s.register();
			if(success == true) {
				System.out.println("You have successfully register a customer account!");
				System.out.println("Please log in!");
			}
			
			login("1");

		} else {
			System.out.println("Please enter a valid number from menu options.");
			mainMenu();
		}
	}
	
	public void customerMenu() {
		System.out.println("1. View available items.");
		System.out.println("2. Make an offer to a item.");
		System.out.println("3. View status of my offers.");
		System.out.println("4. View owned item.");	
		System.out.println("5. Display remaining payments");
		System.out.println("6. Display all payments");
		System.out.println("7. Log out");
		System.out.println("Please enter the option number above.");
		String customerOption = sc.nextLine();

		
		if (customerOption.equals("1")) {
			

		} else if(customerOption.equals("2")) {
			
			
		} else if(customerOption.equals("3")) {
			
			
		} else if(customerOption.equals("4")) {
			

		} else if(customerOption.equals("5")) {
			

		} else if(customerOption.equals("5")) {
			

		} else if(customerOption.equals("7")) {
			//start fresh
			logOut();
			

		} else {
			System.out.println("Please enter a valid number.");
		}

		customerMenu();
		
	}
	
	public void employeeMenu() {
		
	}
	
	public void managerMenu() {
		
	}
	
	public void logOut() {
		//reset all objects and call main menu.
		start();
	}
}
