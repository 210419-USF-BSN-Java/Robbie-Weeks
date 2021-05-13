package repositoryTest;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.*;

import com.ERS.models.Reimbursment;
import com.ERS.repository.ReimbursmentDao;
import com.ERS.repository.ReimbursmentDaoImp;

public class ReimbursementDaoTest {
	
	ReimbursmentDao rd = new ReimbursmentDaoImp();
	static Reimbursment r;
	
	@BeforeClass
	public static void setUp() {
		r = new Reimbursment(200.57, "fly ticket", 1, 2, 2, 1);
	}
	
	@Test
	public void testMakeReim() {
		
		//assertTrue(rd.makeReim(r));
		
	}//passed
	
	@Test
	public void testViewPending() {
		
		//assertNotNull(rd.viewPending(1));
		
	}//passed
	
	@Test
	public void testViewResolved() {
		
		//assertNotNull(rd.viewResolved(1));
		
	}//passed
	
	@Test
	public void testViewAllRequest() {
		
		//assertNotNull(rd.viewAllRequest(1));
		
	}//passed
	
	@Test
	public void testViewAllPending() {
		
		//assertNotNull(rd.viewAllPending());
		//System.out.println(rd.viewAllPending());
		
	}//passed
	
	@Test
	public void testViewAllResolved() {
		
		//assertNotNull(rd.viewAllResolved());
		//System.out.println(rd.viewAllResolved());
		
	}//passed
	
	
}
