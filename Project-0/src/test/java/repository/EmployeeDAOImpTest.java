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
	public void testEditItem() {
		//test an exist item id
		Item i = new Item(8, "Samsung", "Second hand", 500.0);
		
		assertTrue(eDao.editItem(i));
		
		//test to edit a non-exist item ID.
		i.setItemID(999999);
		assertFalse(eDao.editItem(i));
	}
	
	@Test
	public void testOfferAction() {
		//test to accept an offer
		assertTrue(eDao.offerAction(1, "accept"));
		
	}//passed
	
	@Test
	public void testRemoveItem() {
		
		assertFalse(eDao.removeItem(1));
		
	}//passed
	
	@Test
	public void testViewAllPayments() {
		
		assert(eDao.viewAllPayments()) != null;
		
	}//passed

}
