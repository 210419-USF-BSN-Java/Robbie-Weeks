package service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.junit.BeforeClass;
import org.junit.Test;

import com.shop.model.Item;
import com.shop.model.User;
import com.shop.repository.CustomerDAOImp;
import com.shop.repository.UserDAOImp;
import com.shop.service.Service;

public class ServiceTest {
	//Test all units with mockito methods.
	
	static Service s;
	static User u;
	@Mock
	static UserDAOImp uDao;
	@Mock
	static CustomerDAOImp cDao;

	
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
		
		cDao = Mockito.mock(CustomerDAOImp.class);
		//Mock method call for testViewAvalableItem()
		List<Item> i = new ArrayList<>();
		i.add(new Item(1, "Iphone100", "Brand New", "Available", 1000, 0));
		List<Item> items;
		Mockito.when(items = cDao.viewAvailableItems()).thenReturn(i);
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
	
	@Test
	public void testViewAvalableItem() {
		s.viewAvalableItem();;
	}
}
