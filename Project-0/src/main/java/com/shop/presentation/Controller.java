package com.shop.presentation;

import java.util.Scanner;

import com.shop.model.Customer;
import com.shop.model.Employee;
import com.shop.model.Manager;
import com.shop.model.User;
import com.shop.service.Service;

public class Controller {
	
	Service s = new Service();
	Scanner sc = new Scanner(System.in);
	
	////////////////////////////////Main menu///////////////////////////////////////
	public void start() {
		
		System.out.println("Hi! Welcome to Jashop online shopping system, what would you like to do:");
		mainMenu();
	}
	
	public void mainMenu() {
		
		System.out.println("1. Login.");
		System.out.println("2. Regist for a customer account.");		
		System.out.println("Please enter the option number above.");
		String menuInput = sc.nextLine();
		
		if(menuInput.equals("1")) {
			
			login();
			
		} else if(menuInput.equals("2")) {
			
			//call register method to register an account.
			boolean success = s.register("Customer");
			if(success == true) {
				System.out.println("You have successfully register a customer account!");
				System.out.println("Please log in!");
			}
			
			login();
			
		} else {
			System.out.println("Please enter a valid option number.");
			mainMenu();

		}
		
	}
	
	public void login() {
		
		//ask for user's credential input.
		System.out.println("Pleas enter your username:");
		String userName = sc.nextLine();
		System.out.println("Pleas enter your password:");
		String passWord = sc.nextLine();
				
		//call veryfy method to match the login credential with database.
		String userType = s.verifyCredential(userName, passWord);
		if(userType == null) {
			System.out.println("Your login credential does not match the database record, please try again!");
			mainMenu();
		} else if(userType.equals("Customer")) {
			
			customerMenu();

		} else if(userType.equals("Employee")) {

			employeeMenu();
			
		} else if(userType.equals("Manager")) {

			managerMenu();

		} 
	}
	
////////////////////////////////Customer menu///////////////////////////////////////
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
			s.viewAvalableItem();

		} else if(customerOption.equals("2")) {
			offerOption();
			
		} else if(customerOption.equals("3")) {
			s.viewOffers();
			
		} else if(customerOption.equals("4")) {
			s.viewOwned();

		} else if(customerOption.equals("5")) {
			s.viewRemainPayments();

		} else if(customerOption.equals("6")) {
			s.viewAllPayments();

		} else if(customerOption.equals("7")) {
			//start fresh
			logOut();
			

		} else {
			System.out.println("Please enter a valid number.");
		}

		customerMenu();
		
	}
	
	public void offerOption() {
		System.out.println("Please enter the item ID: ");
		String itemID = sc.nextLine();
		System.out.println("Please enter the amount: ");
		String offerAmount = sc.nextLine();
		
		s.makeOffer(Integer.parseInt(itemID), Double.parseDouble(offerAmount));
	}
	
////////////////////////////////Employee menu///////////////////////////////////////

	
	public void employeeMenu() {
		System.out.println("1. Add an item to the shop.");
		System.out.println("2. View pending item offers.");
		System.out.println("3. Accept/reject an offer.");
		System.out.println("4. Remove item from the shop.");	
		System.out.println("5. Display all customer payments.");
		System.out.println("6. Edit an item.");
		System.out.println("7. Log out");
		System.out.println("Please enter the option number above.");
		String employeeOption = sc.nextLine();

		
		if (employeeOption.equals("1")) {
			addItem();

		} else if(employeeOption.equals("2")) {
			s.viewPending();
			
		} else if(employeeOption.equals("3")) {
			offerAction();
			
		} else if(employeeOption.equals("4")) {
			removeItem();

		} else if(employeeOption.equals("5")) {
			s.empViewAllPayments();

		} else if(employeeOption.equals("6")) {
			editItem();

		} else if(employeeOption.equals("7")) {
			//start fresh
			logOut();
			
		} else {
			System.out.println("Please enter a valid number.");
		}

		employeeMenu();
	}
	
	public void addItem() {
		//ask for item's field.
		System.out.println("Pleas enter item name:");
		String itemName = sc.nextLine();
		System.out.println("Pleas enter item description:");
		String itemDescription = sc.nextLine();
		System.out.println("Pleas enter item minimum price:");
		String minimunPrice = sc.nextLine();
		
		s.addItem(itemName, itemDescription, Double.parseDouble(minimunPrice));
	}
	
	public void editItem() {
		//ask for item's field.
		System.out.println("Pleas enter the item ID you want to edit:");
		String itemID = sc.nextLine();
		System.out.println("Pleas enter new item name:");
		String itemName = sc.nextLine();
		System.out.println("Pleas enter new item description:");
		String itemDescription = sc.nextLine();
		System.out.println("Pleas enter new item minimum price:");
		String minimunPrice = sc.nextLine();
		
		s.editItem(Integer.parseInt(itemID), itemName, itemDescription, Double.parseDouble(minimunPrice));
	}
	
	public void removeItem() {
		System.out.println("Pleas enter item id you would like to delete:");
		String itemID = sc.nextLine();
		
		s.removeItem(Integer.parseInt(itemID));
	}
	
	public void offerAction() {
		System.out.println("Please enter the offer ID: ");
		String offerID = sc.nextLine();
		System.out.println("Please enter your action: ");
		System.out.println("1. Accept the offer.");
		System.out.println("2. Reject the offer.");
		String option = sc.nextLine();
		
		if(option.equals("1")) {
			s.offerAction(Integer.parseInt(offerID), "Accepted");
		} else if(option.equals("2")) {
			s.offerAction(Integer.parseInt(offerID), "Rejected");
		} else {
			System.out.println("Invalid input.");
		}
	}
	
////////////////////////////////Manager menu///////////////////////////////////////
	public void managerMenu() {
		System.out.println("1. Create an employee acount.");
		System.out.println("2. View employee list.");
		System.out.println("3. Fire an employee.");
		System.out.println("4. View sales history.");
		System.out.println("7. Log out");
		System.out.println("Please enter the option number above.");
		String managerOption = sc.nextLine();

		
		if (managerOption.equals("1")) {
	
			boolean success = s.register("Employee");
			if(success == true) {
				System.out.println("You have successfully created an employee account!");
			}

		} else if(managerOption.equals("2")) {
			
			s.viewEmployee();
			
		} else if(managerOption.equals("3")) {
			System.out.println("Please enter the employee ID you would like to fire: ");
			String fireID = sc.nextLine();
			
			s.deleteEmployeeAccount(Integer.parseInt(fireID));
			
		} else if(managerOption.equals("4")) {
			s.viewSalesHistory();
			
		} else if(managerOption.equals("7")) {
			//start fresh
			logOut();
			

		} else {
			System.out.println("Please enter a valid number.");
		}

		managerMenu();
	}
	

////////////////////////////////Log out///////////////////////////////////////	
	public void logOut() {
		//reset all objects and call main menu.
		s = new Service();
		start();
	}
}
