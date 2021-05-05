package service;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.shop.service.Service;

public class ServiceTest {

	Service s = new Service();
	
	@Test
	public void testVerifyCredential() {
		//test the returned user type with a customer account.
		assertEquals("Customer", s.verifyCredential("robbie99", "wee"));
		
		//test the returned user type with an employee account
		assertNotEquals("Customer", s.verifyCredential("robbie10", "wee"));
		
	}
	
	@Test
	public void testRegister() {
		
		assertTrue(s.register("Customer"));
		
	}
	
	@Test
	public void testMakeOffer() {
		//return type is void.
		s.makeOffer(4, 2000);
	}
	
	
	@Test
	public void testAddItem() {
		//return type is void.
		s.addItem("Iphone100", "Brand new", 1000.0);
	}
	
	
}
