package com.shop.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class Salt {
	
	public String saltHashing(String password) {
		String hashedPass = "";
		
		//generate a random salt String with UUID.
		String salt = UUID.randomUUID().toString();
		
		//Concat password and salt String.
		String passSalt = password + salt; 
		
		try {
			//convert passSalt's characters into byte code array.
			byte[] bytePassWord = passSalt.getBytes();
			
			//Specify the digest algorithm verson, 1, 256, 312, or 512.
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			
			//hash the binary password.
			byte[] hashedBytePass = md.digest(bytePassWord);
			
			//convert each character in byte array into a String using StringBuilder append function.
			StringBuilder build = new StringBuilder();
			for(int i=0; i< hashedBytePass.length ;i++) {
				build.append(Integer.toString(hashedBytePass[i]));         
	        }
			
			//convert the StringBuilder type to String.
			hashedPass = new String(build);
			
		} catch(NoSuchAlgorithmException e){
			e.printStackTrace();
		}
	
		return hashedPass;
	}
	
}
