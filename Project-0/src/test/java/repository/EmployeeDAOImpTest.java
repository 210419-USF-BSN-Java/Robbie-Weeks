package repository;

import static org.junit.Assert.*;

import org.junit.*;

import com.shop.model.Item;
import com.shop.repository.EmployeeDAOImp;

public class EmployeeDAOImpTest {
	
	EmployeeDAOImp eDao = new EmployeeDAOImp();
	
	@Test
	public void testAddItem() {
		Item i = new Item("Iphone", "Brand new", 1000.00);
		assertTrue(eDao.addItem(i));
	}//passed
	
	@Test
	public void testOfferAction() {
		
		assertTrue(eDao.offerAction(1, "accept"));
		
	}//passed
	
	@Test
	public void testRemoveItem() {
		
		assertTrue(eDao.removeItem(1));
		
	}//passed
	
	@Test
	public void testViewAllPayments() {
		
		assert(eDao.viewAllPayments()) != null;
		
	}//passed

}
