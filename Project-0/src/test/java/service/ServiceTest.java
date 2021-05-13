package service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.shop.model.User;
import com.shop.repository.UserDAO;
import com.shop.repository.UserDAOImp;
import com.shop.service.Salt;
import com.shop.service.Service;

public class ServiceTest {
	UserDAO ud = Mockito.mock(UserDAOImp.class);
	Salt salt = Mockito.mock(Salt.class);
	Service s = new Service(salt, ud);
	
	@Test
	public void testVerifyCredential() {
		String[] str = new String[2];
		User i = new User(1,"rob","wee", "Customer");
		String userName = "rob123sdfsd";
		Mockito.when(ud.getHashAndSalt(userName)).thenReturn(str);
		Mockito.when(salt.verifyHashedPass("wee", str[0], str[1])).thenReturn(true);
		Mockito.when(ud.getUserInfo(userName)).thenReturn(i);
		
		//test the returned user type with a customer account type.
		assertEquals("Customer", s.verifyCredential(userName, "wee"));
		
	}
	
	
//	@Test
//	public void testRegister() {
//		//this method will require console input.
//		assertTrue(s.register("Customer"));
//		
//	}
//	
//	@Test
//	public void testMakeOffer() {
//		//return type is void.
//		s.makeOffer(4, 2000);
//	}
//	
//	
//	@Test
//	public void testAddItem() {
//		//return type is void.
//		s.addItem("Iphone100", "Brand new", 1000.0);
//	}
	
	
}
