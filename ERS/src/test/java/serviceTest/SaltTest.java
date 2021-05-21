package serviceTest;

import static org.junit.Assert.*;

import org.junit.*;

import com.ers.service.Salt;


public class SaltTest {
	
	Salt salt = new Salt();
	
	@Test
	public void testVerifyHashedPass(){
		String password = "wee";
		String hasedPassword = "a1d56bec678dbd84991b48b14f719d643cea0c2465e6b17b6edd4ae297ebecfb";
		String existSalt = "ab02d4b3-b0bc-41c5-8ef9-472c435f5279";
		
		
		//compare if the hashedPassword is equals to hased password+salt.
		assertEquals(true, salt.verifyHashedPass(password, hasedPassword, existSalt));
		
		//compare a same password but different salt that not associate with the username.
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
