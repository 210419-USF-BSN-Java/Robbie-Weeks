package com.shop.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidation {

	//This method is create to validate if the user input is positive number that can have 5 decimals.
	public boolean doubleValidation(String input){ 
		Pattern pattern = Pattern.compile("^[0-9]+(.[0-9]{1,5})?$"); 
		Matcher isNum = pattern.matcher(input);
		if( !isNum.matches() ){
			System.out.println("Invalid input, please enter a positive number.");
			return false; 
		} 
		return true; 
	}
	
	//This method is create to validate if the user input is positive number that can have 5 decimals.
	public boolean idValidation(String input){ 
		Pattern pattern = Pattern.compile("^[1-9]+([0-9]{1,5})?$"); 
		Matcher isNum = pattern.matcher(input);
		if( !isNum.matches() ){
			System.out.println("Invalid input, please enter a positive integer.");
			return false; 
		} 
		return true; 
	}
	
}
