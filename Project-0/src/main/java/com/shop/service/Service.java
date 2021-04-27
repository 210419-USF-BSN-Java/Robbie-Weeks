package com.shop.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.shop.model.Customer;
import com.shop.model.Employee;
import com.shop.model.Item;
import com.shop.model.Manager;
import com.shop.model.Offer;
import com.shop.repository.CustomerDAOImp;
import com.shop.repository.UserDAOImp;

public class Service {
	Customer c;
	Employee e;
	Manager m;
	
	
	UserDAOImp uDao = new UserDAOImp();
	CustomerDAOImp cDao = new CustomerDAOImp();
	
	Scanner sc = new Scanner(System.in);
	
	public Customer setCustomer(String userName, String passWord) {
		
		Customer c = new Customer();
		c.setUserName(userName);
		c.setPassWord(passWord);
		
		return c;
	}
	
	public Employee setEmployee(String userName, String passWord) {
		
		Employee e = new Employee();
		e.setUserName(userName);
		e.setPassWord(passWord);
		
		return e;
	}
	
	public Manager setManager(String userName, String passWord) {
		
		return null;
		
	}
	
	//return a verified customer object, 
	public Customer verifyCustomer(Customer c) {
		
		return uDao.verifyCredential(c);
		
	}
	
	public Employee verifyEmployee(Employee e) {
		
		return uDao.verifyCredential(e);
		
	}
	
	public Manager verifyManager(Manager m) {
		
		return null;
		
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
	
	public void viewAvalableItem() {
		List<Item> items = new ArrayList<>();
		items = cDao.viewAvailableItems();
		
		for(Item i : items) {
			System.out.println(i);
		}
	}
	
	public void makeOffer(int customerID, int itemID, double offerAmount) {
		boolean success;
		Offer o = new Offer(customerID, itemID, offerAmount);
		
		success = cDao.makeOffer(o);
		
		if(success == true) {
			System.out.println("You have successfully made an offer to this item!");
		} else {
			System.out.println("Failed to make an offer! Please contact our customer representitive for more information.");
		}
		
	}
	
	public void viewOwned() {
		
	}
	
}
