package service;

import static org.junit.Assert.*;

import org.junit.*;

import com.shop.service.Salt;

public class SaltTest {
	
	Salt salt = new Salt();
	
	@Test
	public void testVerifyHashedPass(){
		String password = "wee";
		String hasedPassword = "1ed1bf1f21a41691341e61831db1571261121dc1ba14c1a11001701aa1c71311a018813c11213c1c01dc1d519515b180";
		String existSalt = "612fb9fa-dbd5-4182-9c6b-293e0cd23b69";
		
		
		//compare if the hashedPassword is equals to hased password+salt.
		assertEquals(true, salt.verifyHashedPass(password, hasedPassword, existSalt));
		
		//compare a same password but different salt.
		String existSalt2 = "I am some random salt.";
		assertEquals(false, salt.verifyHashedPass(password, hasedPassword, existSalt2));
		
	}
	
	@Test
	public void testSaltHashing(){
		String password = "password";
		
		//test if the hash saltHashing() actually hash a password with salt.
		String[] hashPassAndSalt = salt.saltHashing(password);
		
		System.out.println("Hashed password: " + hashPassAndSalt[0]);
		System.out.println("Salt: " + hashPassAndSalt[1]);
		
	}
}
