package service;

import static org.junit.Assert.*;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.junit.BeforeClass;
import org.junit.Test;

import com.shop.model.User;
import com.shop.repository.UserDAO;
import com.shop.repository.UserDAOImp;
import com.shop.service.Service;

public class ServiceTest {
	
	static Service s;
	//static UserDAOImp uDao;
	static User u;
	@Mock
	static UserDAOImp uDao;
	String userName = "rob";
	
	@BeforeClass
	public static void setUp() {
		s = new Service();
		uDao = Mockito.mock(UserDAOImp.class);
		u = new User(1,"rob","wee","Customer");
		
		//When this method is being call, the return object is the instance variable u.
		Mockito.when(uDao.verifyCredential(u)).thenReturn(u);
		Mockito.when(uDao.checkUserName("rob")).thenReturn(true);
		Mockito.when(uDao.registUserAccount(u, "Customer")).thenReturn(true);
		
	}
	
	@Test
	public void testVerifyCredential() {
		//call verifyCredential method then return the String userType of this User Object.
		assertEquals("Customer", s.verifyCredential(userName, userName));
		
	}
//	
//	@Test
//	public void testRegister() {
//		assertTrue(s.register("Customer"));
//	}
}
