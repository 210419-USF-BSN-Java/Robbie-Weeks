package repositoryTest;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.*;

import com.ERS.models.User;
import com.ERS.repository.EmployeeDao;
import com.ERS.repository.EmployeeDaoImp;

public class EmployeeDaoTest {

	EmployeeDao ed = new EmployeeDaoImp();
	
	@Test
	public void testViewInfo() {
		
		//assertNotNull(ed.viewInfo(1));
		//System.out.println(ed.viewInfo(1));
		
	}//passed
	
	@Test
	public void testUpdateInfo() {
		User u = new User(1, "rob", "wee", "333@gmail.com");
		
		//assertTrue(ed.updateInfo(u));
		//System.out.println(ed.viewInfo(1));
		
	}//passed
	
}
