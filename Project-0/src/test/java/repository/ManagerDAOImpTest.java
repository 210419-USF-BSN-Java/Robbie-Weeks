package repository;

import static org.junit.Assert.*;
import org.junit.*;

import com.shop.model.Employee;
import com.shop.repository.ManagerDAOImp;

public class ManagerDAOImpTest {
	
	ManagerDAOImp mDao = new ManagerDAOImp();
	
//	@Test
//	public void testMakeEmployeeAccount() {
//		User u = new Employee("Rob1", "Wee", "Robbie", "Weeks");
//		
//		assertTrue(mDao.makeEmployeeAccount(e));
//		
//	}//passed
	
	@Test
	public void testDeleteEmployeeAccount() {
		
		assertTrue(mDao.deleteEmployeeAccount(1));
		
	}//passed
	
	@Test
	public void testViewSalesHistory() {
		
		assert(mDao.viewSalesHistory()) != null;
		
	}//passed
}
