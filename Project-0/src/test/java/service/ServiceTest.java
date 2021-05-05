package service;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoJUnitRunner;

import com.shop.model.User;
import com.shop.repository.CustomerDAOImp;
import com.shop.repository.UserDAO;
import com.shop.repository.UserDAOImp;
import com.shop.service.Salt;
import com.shop.service.Service;

@RunWith(MockitoJUnitRunner.class)
public class ServiceTest {
	//Test all units with mockito methods.

	@Mock
	static UserDAO uDao;
	
	@Mock
	static Salt salt;
	User u = new User(1,"rob","wee","Customer");
	
	Service s = new Service();
//	@Mock
//	CustomerDAOImp cDao;

	
	@BeforeClass
	public static void setUp() {
		uDao = Mockito.mock(UserDAOImp.class);
		salt = Mockito.mock(Salt.class);
		
//		uDao = Mockito.mock(UserDAOImp.class);
		
		
		//Mock method call for testVerifyCredential()
		//When this method is being call, the return object is the instance variable u.
//		Mockito.when(ud.verifyCredential(u)).thenReturn(u);
		
//		//Mock method call for testRegister()
//		Mockito.when(uDao.checkUserName("rob")).thenReturn(true);
//		Mockito.when(uDao.registUserAccount(u, "Customer")).thenReturn(true);
//		
//		cDao = Mockito.mock(CustomerDAOImp.class);
//		//Mock method call for testViewAvalableItem()
//		List<Item> i = new ArrayList<>();
//		i.add(new Item(1, "Iphone100", "Brand New", "Available", 1000, 0));
//		List<Item> items;
//		Mockito.when(items = cDao.viewAvailableItems()).thenReturn(i);
	}
	
	@Test
	public void testVerifyCredential() {
		//call verifyCredential method then return the String userType of this User Object.
		
		String[] hashAndSalt = new String[2];
		
		Mockito.when(uDao.getHashAndSalt("rob")).thenReturn(hashAndSalt);
		Mockito.when(salt.verifyHashedPass("wee", hashAndSalt[0], hashAndSalt[1])).thenReturn(true);
		
		assertEquals("Customer", s.verifyCredential("rob", "wee"));
		
	}
	
//	@Test
//	public void testRegister() {
//		assertTrue(s.register("Customer"));
//	}
//	
//	@Test
//	public void testViewAvalableItem() {
//		s.viewAvalableItem();;
//	}
}
