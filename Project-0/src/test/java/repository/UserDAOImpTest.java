package repository;

import static org.junit.Assert.*;

import org.junit.*;

import com.shop.model.Customer;
import com.shop.repository.UserDAOImp;

public class UserDAOImpTest {
	
	UserDAOImp uDao = new UserDAOImp();
	
	@Test
	public void testCheckUserName() {
		
		assertFalse(uDao.checkUserName("rob1"));
		
	}//passed
	
//	@Test
//	public void testRegistUserAccount() {
//		Customer c = new Customer("rob1", "wee", "Robbie", "Weeks");
//		
//		assertTrue(uDao.registUserAccount(u));
//		
//	}//passed
//	
//	@Test
//	public void testVerifyCredential() {
//		Customer c = new Customer("rob1", "wee");
//		
//		assertNotNull(uDao.verifyCredential(c));
//		
//	}//passed
	
}
