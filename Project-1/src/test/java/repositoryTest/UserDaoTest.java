package repositoryTest;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.*;

import com.ERS.models.User;
import com.ERS.repository.UserDao;
import com.ERS.repository.UserDaoImp;

public class UserDaoTest {
	
	UserDao ud = new UserDaoImp();
	
	@Test
	public void testRegistUserAccount() {
		User u = new User();
		
		//assertTrue(ud.registUserAccount(u));
		
	}//passed
	
	@Test
	public void testGetHashAndSalt() {
		String[] expect = new String[2];
		expect[0] = "1233";
		expect[1] = "3321";
		
		User u = new User();
		u.setUserName("rob");
		
		//assertArrayEquals(expect, ud.getHashAndSalt(u.getUserName()));
		
		u.setUserName("robc");
		//assertNotEquals(expect, ud.getHashAndSalt(u.getUserName()));
		
	}//passed

}
