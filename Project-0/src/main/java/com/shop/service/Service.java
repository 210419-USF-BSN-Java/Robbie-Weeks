package com.shop.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.shop.model.Item;
import com.shop.model.Offer;
import com.shop.model.Payment;
import com.shop.model.User;
import com.shop.repository.CustomerDAO;
import com.shop.repository.CustomerDAOImp;
import com.shop.repository.EmployeeDAO;
import com.shop.repository.EmployeeDAOImp;
import com.shop.repository.ManagerDAO;
import com.shop.repository.ManagerDAOImp;
import com.shop.repository.PaymentDAO;
import com.shop.repository.PaymentDAOImp;
import com.shop.repository.UserDAO;
import com.shop.repository.UserDAOImp;

public class Service {
	Logger log = Logger.getLogger(Service.class);
	User u = new User();
	UserDAO uDao = new UserDAOImp();
	CustomerDAO cDao = new CustomerDAOImp();
	EmployeeDAO eDao = new EmployeeDAOImp();
	ManagerDAO mDao = new ManagerDAOImp();
	PaymentDAO pDao = new PaymentDAOImp();
	Salt salt = new Salt();
	Scanner sc = new Scanner(System.in);
	
	public Service (){
		
	}
	
	public Service (Salt salt, UserDAO uDao){
		this.uDao = uDao;
		this.salt = salt;
	}
	

	//Verify hashed password
	public String verifyCredential(String userName, String passWord) {

		String[] hashAndSalt = uDao.getHashAndSalt(userName);
		
		//call veryfyHashedPass() with password input, hashed password, and user salt.
		boolean verifyResult = salt.verifyHashedPass(passWord, hashAndSalt[0], hashAndSalt[1]);
		
		if(verifyResult == true) {
			//get user info.
			u = uDao.getUserInfo(userName);
			System.out.println("\nWelcome, " + u.getFirstName() + " " + u.getLastName() + ".");
			System.out.println("What would you like to do: \n");
			
			log.info("The user ID: " + u.getUserID() + " has loged in as : " + u.getUserType() + " .");
			
			return u.getUserType();
			
		} else {
			
			return null;
		}

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
			System.out.println("\nPleas enter a username you would like to register:");
			userName = sc.nextLine();
			
		} while(userName.length() < 1);

		//boolean result of checking if the username exist in database, true if the username already exist.
		exist = uDao.checkUserName(userName);

		//loop until the user enter a valid username that do not exist in database.
		while(exist == true) {
			System.out.println("\nThis username is already exist, please enter a new username: ");
			userName = sc.nextLine();
			exist = uDao.checkUserName(userName);
		} 
		
		//do-while loop prevent if the user accidently hit enter without an input.
		do {
			System.out.println("\nPleas enter password:");
			passWord = sc.nextLine();
			
		} while(passWord.length() < 1);
		
		//do-while loop prevent if the user accidently hit enter without an input.
		do {
			System.out.println("\nPleas enter first name:");
			firstName = sc.nextLine();
			
		} while(firstName.length() < 1);
		
		//do-while loop prevent if the user accidently hit enter without an input.
		do {
			System.out.println("\nPleas enter last name:");
			lastName = sc.nextLine();
			
		} while(lastName.length() < 1);
		
		//create salt with password and return a String array with 1) hashed password. 2) the salt generated for this password.
		String[] hashAndSalt = salt.saltHashing(passWord);
		
		//create an User object with username, hashed password, firstname, lastname, and salt.
		User u = new User(userName,hashAndSalt[0],firstName,lastName, hashAndSalt[1]);
		
		//uDao.registUserAccount() returns the success result of creating user record in database.
		boolean success = uDao.registUserAccount(u, userType);
		
		if(success == true) {
			log.info("The user has registed a customer account with username: " + userName +  " .");
		}
		
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
			log.info("The customer ID: " + o.getCustomerID() + " made an offer on item ID: " + o.getItemID() + " for "+ o.getOfferAmount() + " dollars.");
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
	
	//call DAO method to create an payment list that has all completed and incomplete payments, then print.
	public void viewAllPayments() {
		List<Payment> payments = new ArrayList<>();
		payments = pDao.viewAllPayments(u);
		
		for(Payment p : payments) {
			System.out.println(p);
		}
	}
	
	//
	public double calculateWeeklyPayments() {
		List<Payment> payments = new ArrayList<>();
		payments = pDao.viewRemainPayments(u);
		
		double totalWeeklyPayment = 0;
		
		for(Payment p : payments) {
			System.out.println(p);
			totalWeeklyPayment += p.getRemainPayment()/p.getRemainTerms();
		}
		
		return totalWeeklyPayment;
	
	}
	
	public void payWeeklyPayment() {
		boolean success = pDao.payWeeklyPayment();
		
		if(success == true) {
			System.out.println("You have paid your weekly payment.");
		} else {
			System.out.println("You have failed to pay your weekly payment.");
		}
	}
	
	///////////////////////////////////////////Begin employee service/////////////////////////////////////////
	
	//fulfill a new item object and call DAO method to create an item record in databse.
	public void addItem(String itemName, String itemDescription, Double minimumPrice) {
		boolean success;
		Item i = new Item(itemName, itemDescription, minimumPrice);
		
		success = eDao.addItem(i);
		
		if(success == true) {
			log.info("The employee ID: " + u.getUserID() + " has added an item into database. Item name: " 
					+ itemName + ", item description: "+ itemDescription + ", minimumPrice: " + minimumPrice + "dollars.");
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
			log.info("The employee ID: " + u.getUserID() + " has edited an item of database. Item id: " + itemID + ", item name: " + itemName 
					+ ", item description: "+ itemDescription + ", minimumPrice: " + minimumPrice + "dollars.");
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
			log.info("The employee ID: " + u.getUserID() + " has made an decision on offer id: " 
					+ offerID + ", offer decision: "+ action + ".");
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
			log.info("The employee ID: " + u.getUserID() + " has removed an item from databse. Item id: " 
					+ itemID + ".");
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
			log.info("The manager ID: " + u.getUserID() + " has fired an employee and deleted the employee account from databse. Employee id: " 
					+ employeeID + ".");
			System.out.println("You have successfully deleted an employee account!");
		} else {
			System.out.println("The employee ID does not exist, please enter another item ID.");
		}
	}
	
	//call DAO method to create an offer list that has all offers and their status.
	public void viewSalesHistory() {
		List<Offer> acceptedOffers = new ArrayList<>();
		acceptedOffers = mDao.viewSalesHistory();
		
		for(Offer o : acceptedOffers) {
			System.out.println(o);
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



