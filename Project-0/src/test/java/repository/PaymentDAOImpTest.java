package repository;

import org.junit.*;
import static org.junit.Assert.*;

import com.shop.model.Payment;
import com.shop.model.User;
import com.shop.repository.PaymentDAO;
import com.shop.repository.PaymentDAOImp;

public class PaymentDAOImpTest {
	
	PaymentDAO pDao = new PaymentDAOImp();
	
	@Test
	public void testCreatePayment() {
		Payment p = new Payment(99, 99, "Completed", 2000.5, 0.0, 0);
		
		assertTrue(pDao.createPayment(p));
		
	}
	
	@Test
	public void testPayWeeklyPayment() {
		assertTrue(pDao.payWeeklyPayment());
	}
	
	@Test
	public void testViewRemainPayments() {
		User u = new User();
		u.setUserID(1);
		assert(pDao.viewRemainPayments(u)) != null;
		//print statements
	}
	
	@Test
	public void testViewAllPayments() {
		User u = new User();
		u.setUserID(1);
		assert(pDao.viewRemainPayments(u)) != null;
		//print statements
	}
}
