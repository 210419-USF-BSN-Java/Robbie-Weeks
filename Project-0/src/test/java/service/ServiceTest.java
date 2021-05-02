package service;

import static org.junit.Assert.*;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.junit.BeforeClass;
import org.junit.Test;

import com.shop.model.User;
import com.shop.repository.UserDAOImp;
import com.shop.service.Service;

public class ServiceTest {
	
	static Service s;
	static User u;
	@Mock
	static UserDAOImp uDao;

	
	@BeforeClass
	public static void setUp() {
		s = new Service();
		uDao = Mockito.mock(UserDAOImp.class);
		u = new User(1,"rob","wee","Customer");
		
		//Mock method call for testVerifyCredential()
		//When this method is being call, the return object is the instance variable u.
		Mockito.when(u = uDao.verifyCredential(u)).thenReturn(u);
		
		//Mock method call for testRegister()
		Mockito.when(uDao.checkUserName("rob")).thenReturn(true);
		Mockito.when(uDao.registUserAccount(u, "Customer")).thenReturn(true);
		
	}
	
	@Test
	public void testVerifyCredential() {
		//call verifyCredential method then return the String userType of this User Object.
		assertEquals("Customer", s.verifyCredential("robc", "wee"));
		
	}
	
	@Test
	public void testRegister() {
		assertTrue(s.register("Customer"));
	}
}
