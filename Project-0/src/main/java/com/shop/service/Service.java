package com.shop.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.shop.model.Customer;
import com.shop.model.Employee;
import com.shop.model.Item;
import com.shop.model.Manager;
import com.shop.model.Offer;
import com.shop.model.Payment;
import com.shop.repository.CustomerDAOImp;
import com.shop.repository.EmployeeDAOImp;
import com.shop.repository.ManagerDAOImp;
import com.shop.repository.UserDAOImp;

public class Service {
	Customer c;
	Employee e;
	Manager m;
	
	UserDAOImp uDao = new UserDAOImp();
	CustomerDAOImp cDao = new CustomerDAOImp();
	EmployeeDAOImp eDao = new EmployeeDAOImp();
	ManagerDAOImp mDao = new ManagerDAOImp();
	
	Scanner sc = new Scanner(System.in);
	
	//return a verified customer object, 
	public boolean verifyCustomer(String userName, String passWord) {
		boolean pass = false;
		
		c.setUserName(userName);
		c.setPassWord(passWord);
		
		c = uDao.verifyCredential(c);
		
		if(c.getCustomerID() >= 1) {
			pass = true;
		}
		
		return pass;
	}
	
	public boolean verifyEmployee(String userName, String passWord) {
		boolean pass = false;
		
		e.setUserName(userName);
		e.setPassWord(passWord);
		
		e = uDao.verifyCredential(e);
		
		if(e.getEmployeeID() >= 1) {
			pass = true;
		}
		
		return pass;
	}
	
	public boolean verifyManager(String userName, String passWord) {
		boolean pass = false;
		
		m.setUserName(userName);
		m.setPassWord(passWord);
		
		//m = uDao.verifyCredential(m);
		
		if(m.getManagerID() >= 1) {
			pass = true;
		}
		
		return pass;
	}
	
	public boolean register() {
		boolean exist;
		String userName;
		String passWord;
		String firstName;
		String lastName;
		
		do {
			//ask for a new username.
			System.out.println("Pleas enter a username you would like to register:");
			userName = sc.nextLine();
			
		} while(userName.length() < 1);

		//boolean result of checking if the username exist in database, true if the username already exist.
		exist = uDao.checkUserName(userName);

		//loop until the user enter a valid username that do not exist in database.
		while(exist == true) {
			System.out.println("This username is already exist, please enter a new username: ");
			userName = sc.nextLine();
			exist = uDao.checkUserName(userName);
		} 
		
		//
		do {
			System.out.println("Pleas enter your password:");
			passWord = sc.nextLine();
			
		} while(passWord.length() < 1);
		
		do {
			System.out.println("Pleas enter your first name:");
			firstName = sc.nextLine();
			
		} while(firstName.length() < 1);
		
		do {
			System.out.println("Pleas enter your last name:");
			lastName = sc.nextLine();
			
		} while(lastName.length() < 1);
		
		Customer c = new Customer(firstName,lastName,userName,passWord);
		
		boolean success = uDao.registUserAccount(c);
		
		return success;
		
	}
	
///////////////////////////////////////////Begin customer methods/////////////////////////////////////////
	
	public void viewAvalableItem() {
		List<Item> items = new ArrayList<>();
		items = cDao.viewAvailableItems();
		
		for(Item i : items) {
			System.out.println(i);
		}
	}
	
	public void makeOffer(int itemID, double offerAmount) {
		boolean success;
		Offer o = new Offer(c.getCustomerID(), itemID, offerAmount);
		
		success = cDao.makeOffer(o);
		
		if(success == true) {
			System.out.println("You have successfully made an offer to this item!");
		} else {
			System.out.println("Failed to make an offer! Please contact our customer representitive for more information.");
		}
		
	}
	
	public void viewOwned() {
		List<Item> items = new ArrayList<>();
		items = cDao.viewOwned(c);
		
		for(Item i : items) {
			System.out.println(i);
		}
	}
	
	public void viewRemainPayments() {
		List<Payment> payments = new ArrayList<>();
		payments = cDao.viewRemainPayments(c);
		
		for(Payment p : payments) {
			System.out.println(p);
		}
	}
	
	public void viewAllPayments() {
		List<Payment> payments = new ArrayList<>();
		payments = cDao.viewAllPayments(c);
		
		for(Payment p : payments) {
			System.out.println(p);
		}
	}
	
	///////////////////////////////////////////Begin employee methods/////////////////////////////////////////
	
	public void addItem(String itemName, String itemDescription, Double minimumPrice) {
		boolean success;
		Item i = new Item(itemName, itemDescription, minimumPrice);
		
		success = eDao.addItem(i);
		
		if(success == true) {
			System.out.println("You have successfully added an item!");
		} else {
			System.out.println("Failed to add an item! Please contact a tech representitive for more information.");
		}
	}
	
	public void offerAction(int offerID, String action) {
		boolean success;
		
		success = eDao.offerAction(offerID, action);
		
		if(success == true) {
			System.out.println("You have successfully accpted this offer, other offers for this item will automatically turn down.");
		} else {
			System.out.println("The offer ID does not exist or not in pending status, please enter another offer ID.");
		}
	}
	
	public void removeItem(int itemID) {
		boolean success;
		
		success = eDao.removeItem(itemID);
		
		if(success == true) {
			System.out.println("You have successfully deleted an item!");
		} else {
			System.out.println("The item ID does not exist or not in pending status, please enter another item ID.");
		}
	}
	
	public void empViewAllPayments() {
		List<Payment> payments = new ArrayList<>();
		payments = eDao.viewAllPayments();
		
		for(Payment p : payments) {
			System.out.println(p);
		}
	}
	
	///////////////////////////////////////////Begin manager methods/////////////////////////////////////////
	
	public void makeEmployeeAccount(String userName, String passWord, String firstName, String lastName) {
		boolean success;
		e = new Employee (userName, passWord, firstName, lastName);
		
		success = mDao.makeEmployeeAccount(e);
		
		if(success == true) {
			System.out.println("You have successfully created an employee account!");
		} else {
			System.out.println("Failed to create an employee account! Please contact a tech representitive for more information.");
		}
	}
	
	public void deleteEmployeeAccount(int employeeID) {
		boolean success;
		success = mDao.deleteEmployeeAccount(employeeID);
		
		if(success == true) {
			System.out.println("You have successfully deleted an employee account!");
		} else {
			System.out.println("The employee ID does not exist, please enter another item ID.");
		}
	}
	
	public void viewSalesHistory() {
		List<Payment> payments = new ArrayList<>();
		payments = mDao.viewSalesHistory();
		
		for(Payment p : payments) {
			System.out.println(p);
		}
	}
	
}



