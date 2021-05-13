package repositoryTest;

import static org.junit.Assert.assertTrue;

import org.junit.*;

import com.ERS.repository.ManagerDao;
import com.ERS.repository.ManagerDaoImp;

public class ManagerDaoTest {

	ManagerDao md = new ManagerDaoImp();
	
	@Test
	public void testRequestAction() {
		
		//assertTrue(md.requestAction(1, 2, 2));
		
	}//passed
}
