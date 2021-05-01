package repository;


import static org.junit.Assert.assertTrue;

import org.junit.*;

import com.shop.model.Customer;
import com.shop.model.Offer;
import com.shop.repository.CustomerDAOImp;

public class CustomerDAOImpTest {
	
	CustomerDAOImp cDao = new CustomerDAOImp();
	
	
//	@Test
//	public void testViewAvailableItems() {
//		
//		assert(cDao.viewAvailableItems()) != null;
//		
//	}//passed
//	
//	@Test
//	public void testMakeOffer() {
//		Offer o = new Offer(5, 11, 50.00);
//		
//		assertTrue(cDao.makeOffer(o));
//	}//passed
//	
	//@Test
//	public void testViewOwned() {
//		Customer c = new Customer();
//		c.setCustomerID(1);
//		
//		assert(cDao.viewOwned(c)) != null;
//	}//passed
//	
//	@Test
//	public void testViewRemainPayments() {
//		Customer c = new Customer();
//		c.setCustomerID(1);
//		
//		assert(cDao.viewRemainPayments(c)) != null;
//	}//passed
//	
//	@Test
//	public void testViewAllPayments() {
//		Customer c = new Customer();
//		c.setCustomerID(1);
//		
//		assert(cDao.viewAllPayments(c)) != null;
//	}//passed
}
