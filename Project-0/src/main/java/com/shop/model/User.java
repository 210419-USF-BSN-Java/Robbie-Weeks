package com.shop.model;

public class User {
	
	private int userID;
	private String userName;
	private String passWord;
	private String firstName;
	private String lastName;
	private String userType;
	private String salt;
	
	public User() {
		
	}

	public User(String userName, String passWord) {
		super();
		this.userName = userName;
		this.passWord = passWord;
	}
	
	
	//Constuctor for viewUsers method.
	public User(int userID, String firstName, String lastName, String userType) {
		super();
		this.userID = userID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userType = userType;
	}

	public User(String userName, String passWord, String firstName, String lastName, String salt) {
		super();
		this.userName = userName;
		this.passWord = passWord;
		this.firstName = firstName;
		this.lastName = lastName;
		this.salt = salt;
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
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}

	@Override
	public String toString() {
		return "User [userID = " + userID + ", userName = " + userName + ", passWord = " + passWord + ", firstName = "
				+ firstName + ", lastName = " + lastName + ", userType = " + userType + "]";
	}
}
