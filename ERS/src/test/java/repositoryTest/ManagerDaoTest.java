package repositoryTest;

import static org.junit.Assert.assertTrue;

import org.junit.*;

import com.ers.repository.ManagerDao;
import com.ers.repository.ManagerDaoImp;

public class ManagerDaoTest {

	ManagerDao md = new ManagerDaoImp();
	
	@Test
	public void testRequestAction() {
		
		//assertTrue(md.requestAction(1, 2, 2));
		
	}//passed
}
