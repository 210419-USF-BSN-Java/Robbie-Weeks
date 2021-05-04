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
		
		assertFalse(uDao.checkUserName("asdasdasasd"));
		
	}//passed
	
	@Test
	public void testRegistUserAccount() {
		User u = new User();
		
		assertTrue(uDao.registUserAccount(u,"Customer"));
		
	}//passed
	
	@Test
	public void testGetUserInfo() {
		
		assertNotNull(uDao.getUserInfo("rob1"));
		
	}//passed
	
	@Test
	public void testGetHashAndSalt() {
		String[] expect = new String[2];
		expect[0] = "1ed1bf1f21a41691341e61831db1571261121dc1ba14c1a11001701aa1c71311a018813c11213c1c01dc1d519515b180";
		expect[1] = "612fb9fa-dbd5-4182-9c6b-293e0cd23b69";
		
		User u = new User();
		u.setUserName("robbie99");
		
		assertArrayEquals(expect, uDao.getHashAndSalt(u));
		
		u.setUserName("robc");
		assertNotEquals(expect, uDao.getHashAndSalt(u));
		
	}
}
