package repository;


import static org.junit.Assert.*;

import org.junit.Test;

import com.shop.model.Offer;
import com.shop.model.User;
import com.shop.repository.CustomerDAOImp;
import com.shop.repository.PaymentDAOImp;

public class CustomerDAOImpTest {
	
	CustomerDAOImp cDao = new CustomerDAOImp();
	PaymentDAOImp pDao = new PaymentDAOImp();
	
	
	@Test
	public void testViewAvailableItems() {
		//if the the sql executed correctly, viewAvailableItems() will return a List object
		assert(cDao.viewAvailableItems()) != null;
		
	}//passed
	
	@Test
	public void testMakeOffer() {
		Offer o = new Offer(5, 11, 50.00);

		assertTrue(cDao.makeOffer(o));
	}//passed
	
	@Test
	public void testViewOwned() {
		User u = new User();
		u.setUserID(1);
		
		assert(cDao.viewOwned(u.getUserID())) != null;
	}//passed
	
	@Test
	public void testViewRemainPayments() {
		User u = new User();
		u.setUserID(1);
		
		assert(pDao.viewRemainPayments(u)) != null;
	}//passed
	
	@Test
	public void testViewAllPayments() {
		User u = new User();
		u.setUserID(1);
		
		assert(pDao.viewAllPayments(u)) != null;
	}//passed
}
