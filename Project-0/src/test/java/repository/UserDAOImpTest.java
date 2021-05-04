package repository;

import static org.junit.Assert.*;

import org.junit.*;

import com.shop.model.User;
import com.shop.repository.UserDAOImp;

public class UserDAOImpTest {
	
	UserDAOImp uDao = new UserDAOImp();
	
	@Test
	public void testCheckUserName() {
		
		assertTrue(uDao.checkUserName("rob1"));
		
	}//passed
	
//	@Test
//	public void testRegistUserAccount() {
//		User u = new User("rob1", "wee", "Robbie", "Weeks");
//		
//		assertTrue(uDao.registUserAccount(u,"Customer"));
//		
//	}//passed
	
	@Test
	public void testVerifyCredential() {
		User u = new User("rob1", "wee");
		
		assertNotNull(uDao.getUserInfo("asdas"));
		
	}//passed
	
}
