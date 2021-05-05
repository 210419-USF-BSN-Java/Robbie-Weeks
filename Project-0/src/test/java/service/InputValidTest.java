package service;

import static org.junit.Assert.*;

import org.junit.*;

import com.shop.service.InputValidation;

public class InputValidTest {

	InputValidation iv = new InputValidation();
	
	@Test
	public void testDoubleValidation() {
		//this method only return true if the input is positive double that > 0.
		
		//test with an actualy double.
		assertTrue(iv.doubleValidation("100.5"));
		
		//test with a negative number.
		assertFalse(iv.doubleValidation("-100.5"));
		
		//test with a mix String.
		assertFalse(iv.doubleValidation("asdasd-100.5"));
	}
	
	@Test
	public void testIdValidation() {
		//this method only return true if the input is positive integer that > 0.
		
		//test with an positive integer.
		assertTrue(iv.idValidation("100"));
		
		//test with an double.
		assertFalse(iv.idValidation("100.5"));
		
		//test with a negative integer.
		assertFalse(iv.doubleValidation("-100"));
		
		//test with a mix String.
		assertFalse(iv.doubleValidation("asdasd-100.5"));
	}
}
