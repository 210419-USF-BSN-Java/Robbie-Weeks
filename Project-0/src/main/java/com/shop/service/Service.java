package com.shop.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.shop.model.Item;
import com.shop.model.Offer;
import com.shop.model.Payment;
import com.shop.model.User;
import com.shop.repository.CustomerDAOImp;
import com.shop.repository.EmployeeDAOImp;
import com.shop.repository.ManagerDAOImp;
import com.shop.repository.PaymentDAOImp;
import com.shop.repository.UserDAOImp;

public class Service {

	User u;
	
	UserDAOImp uDao = new UserDAOImp();
	CustomerDAOImp cDao = new CustomerDAOImp();
	EmployeeDAOImp eDao = new EmployeeDAOImp();
	ManagerDAOImp mDao = new ManagerDAOImp();
	PaymentDAOImp pDao = new PaymentDAOImp();
	
	Scanner sc = new Scanner(System.in);
	
	//return a verified user object, 
	public String verifyCredential(String userName, String passWord) {
		u = new User (userName,passWord);
		
		//uDao.verifyCredential() method returns a User object.
		//It has user ID if the credential matches the database record.
		u = uDao.verifyCredential(u);
		
		//check if the returned user object has userID.
		if(u.getUserID() > 0) {
			System.out.println("Hi " + u.getFirstName() + " what would you like to do:");
		}
		
		return u.getUserType();
	}
	
	//Fulfill an new user object's fields and call DAO method to create a record in database.
	public boolean register(String userType) {
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
		
		//do-while loop prevent if the user accidently hit enter without an input.
		do {
			System.out.println("Pleas enter password:");
			passWord = sc.nextLine();
			
		} while(passWord.length() < 1);
		
		//do-while loop prevent if the user accidently hit enter without an input.
		do {
			System.out.println("Pleas enter first name:");
			firstName = sc.nextLine();
			
		} while(firstName.length() < 1);
		
		//do-while loop prevent if the user accidently hit enter without an input.
		do {
			System.out.println("Pleas enter last name:");
			lastName = sc.nextLine();
			
		} while(lastName.length() < 1);
		
		User u = new User(userName,passWord,firstName,lastName);
		
		//uDao.registUserAccount() returns the success result of creating user record in database.
		boolean success = uDao.registUserAccount(u, userType);
		
		return success;
		
	}
	
///////////////////////////////////////////Begin customer service/////////////////////////////////////////
	
	//call DAO method to create an available item list, then print the elements from returned list object.
	public void viewAvalableItem() {
		List<Item> items = new ArrayList<>();
		items = cDao.viewAvailableItems();
		
		for(Item i : items) {
			System.out.println(i);
		}
	}
	
	//call DAO method to create an offer record into database then print the result of execution.
	public void makeOffer(int itemID, double offerAmount) {
		boolean success;
		Offer o = new Offer(u.getUserID(), itemID, offerAmount);
		
		//cDao.makeOffer() returns the execution result of SQL create statement.
		success = cDao.makeOffer(o);
		
		if(success == true) {
			System.out.println("You have successfully made an offer to this item!");
		} else {
			System.out.println("Failed to make an offer! Please contact our customer representitive for more information.");
		}
		
	}
	
	//call DAO method to create an offer list, then print the returned offer list.
	public void viewOffers() {
		List<Offer> offers = new ArrayList<>();
		
		//cDao.viewOffers() returns the offer list from databse
		offers = cDao.viewOffers(u.getUserID());
		
		for(Offer o : offers) {
			System.out.println(o);
		}
	}
	
	//call DAO method to create an item list that shows all owned items of this customer ID, then print them.
	public void viewOwned() {
		List<Item> items = new ArrayList<>();
		
		items = cDao.viewOwned(u.getUserID());
		
		for(Item i : items) {
			System.out.println(i);
		}
	}
	
	//call DAO method to create an payment list that has all incomplete payments, then print.
	public void viewRemainPayments() {
		List<Payment> payments = new ArrayList<>();
		
		payments = pDao.viewRemainPayments(u);
		
		for(Payment p : payments) {
			System.out.println(p);
		}
	}
	
	////call DAO method to create an payment list that has all completed and incomplete payments, then print.
	public void viewAllPayments() {
		List<Payment> payments = new ArrayList<>();
		payments = pDao.viewAllPayments(u);
		
		for(Payment p : payments) {
			System.out.println(p);
		}
	}
	
	///////////////////////////////////////////Begin employee service/////////////////////////////////////////
	
	//fulfill a new item object and call DAO method to create an item record in databse.
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
	
	//fulfull a item object and call DAO method to replace an existing item record in database.
	public void editItem(int itemID, String itemName, String itemDescription, double minimumPrice) {
		boolean success;
		Item i = new Item(itemID, itemName, itemDescription, minimumPrice);
		
		success = eDao.editItem(i);
		
		if(success == true) {
			System.out.println("You have successfully edited this item!");
		} else {
			System.out.println("Failed to edit this item! Please contact a tech representitive for more information.");
		}
	}
	
	//call DAO method with offer ID and the action to this offer.
	public void offerAction(int offerID, String action) {
		boolean success;
		
		success = eDao.offerAction(offerID, action);
		
		if(success == true) {
			System.out.println("You have successfully accpted this offer, other offers for this item will automatically turn down.");
		} else {
			System.out.println("The offer ID does not exist or not in pending status, please enter another offer ID.");
		}
	}
	
	//call viewPending() method to create an offer list that all the offer status is "Pending", then print the elements.
	public void viewPending() {
		List<Offer> pending = eDao.viewPending();
		
		for(Offer o : pending) {
			System.out.println(o);
		}
	}
	
	//call DAO method to remove an item record from databse.
	public void removeItem(int itemID) {
		boolean success;
		
		success = eDao.removeItem(itemID);
		
		if(success == true) {
			System.out.println("You have successfully deleted an item!");
		} else {
			System.out.println("The item ID does not exist or not in pending status, please enter another item ID.");
		}
	}
	
	//method for the employee to view all payments history that has all payment records of all users.
	public void empViewAllPayments() {
		List<Payment> payments = new ArrayList<>();
		payments = eDao.viewAllPayments();
		
		for(Payment p : payments) {
			System.out.println(p);
		}
	}
	
	///////////////////////////////////////////Begin manager service/////////////////////////////////////////
	
	//call DAO method to delete an employee user record.
	public void deleteEmployeeAccount(int employeeID) {
		boolean success;
		
		success = mDao.deleteEmployeeAccount(employeeID);
		
		if(success == true) {
			System.out.println("You have successfully deleted an employee account!");
		} else {
			System.out.println("The employee ID does not exist, please enter another item ID.");
		}
	}
	
	//call DAO method to create an offer list that has all offers and their status.
	public void viewSalesHistory() {
		List<Payment> payments = new ArrayList<>();
		payments = mDao.viewSalesHistory();
		
		for(Payment p : payments) {
			System.out.println(p);
		}
	}
	
	//call DAO method to view all employee users.
	public void viewEmployee() {
		String user = "Employee";
		
		List<User> employees = mDao.viewUsers(user);
		
		for(User u : employees) {
			System.out.println(u);
		}
	}
	
}



