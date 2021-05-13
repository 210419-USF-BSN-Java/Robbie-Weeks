package com.ERS.models;

public class User {
	private int userID;
	private String userName;
	private String passWord;
	private String firstName;
	private String lastName;
	private int userRole;
	private String salt;
	private String email;
	
	public User() {
		
	}

	public User(String userName, String passWord) {
		super();
		this.userName = userName;
		this.passWord = passWord;
	}
	
	
	//Constuctor for viewUsers method.
	public User(int userID, String firstName, String lastName, int userRole) {
		super();
		this.userID = userID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userRole = userRole;
	}

	public User(String userName, String passWord, String firstName, String lastName, String salt) {
		super();
		this.userName = userName;
		this.passWord = passWord;
		this.firstName = firstName;
		this.lastName = lastName;
		this.salt = salt;
	}
	
	public User(int userID, String userName, String passWord, String firstName, String lastName, int userRole,
			String salt, String email) {
		super();
		this.userID = userID;
		this.userName = userName;
		this.passWord = passWord;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userRole = userRole;
		this.salt = salt;
		this.email = email;
	}

	

	public User(int userID, String firstName, String lastName, String email) {
		super();
		this.userID = userID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getUserRole() {
		return userRole;
	}

	public void setUserRole(int userRole) {
		this.userRole = userRole;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "User [userID=" + userID + ", userName=" + userName + ", passWord=" + passWord + ", firstName="
				+ firstName + ", lastName=" + lastName + ", userType=" + userRole + ", salt=" + salt + ", email="
				+ email + "]";
	}
	
	
}
