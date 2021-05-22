package repositoryTest;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.*;

import com.ers.models.User;
import com.ers.repository.UserDao;
import com.ers.repository.UserDaoImp;
import com.ers.service.Salt;

public class UserDaoTest {
	
	UserDao ud = new UserDaoImp();
	Salt salt = new Salt();
	
	
//	@Test
//	public void testRegistUserAccount() {
//		User u = new User();
//		u.setUserName("rob");
//		u.setUserRole(1);
//		String[] pass = salt.saltHashing("wee");
//		u.setPassWord(pass[0]);
//		u.setSalt(pass[1]);
//		
//		System.out.println(u);
//		assertTrue(ud.registUserAccount(u));
//		
//	}//passed
//	
//	@Test
//	public void testGetHashAndSalt() {
//		String[] expect = new String[2];
//		expect[0] = "1233";
//		expect[1] = "3321";
//		
//		User u = new User();
//		u.setUserName("rob");
//
//		
//		//assertArrayEquals(expect, ud.getHashAndSalt(u.getUserName()));
//		
//		u.setUserName("robc");
//		//assertNotEquals(expect, ud.getHashAndSalt(u.getUserName()));
//		
//	}//passed

}
