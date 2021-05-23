package delegatesTest;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.RequestFacade;
import org.apache.catalina.connector.ResponseFacade;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.ers.delegates.EmployeeDelegate;
import com.ers.delegates.ManagerDelegate;
import com.ers.models.Reimbursment;
import com.ers.models.User;
import com.ers.repository.EmployeeDao;
import com.ers.repository.EmployeeDaoImp;
import com.ers.repository.ManagerDao;
import com.ers.repository.ManagerDaoImp;
import com.ers.repository.UserDao;
import com.ers.repository.UserDaoImp;

public class ManagerDelegateTest {
	@Mock
	static HttpServletRequest request = Mockito.mock(RequestFacade.class);
	
	@Mock
	static HttpServletResponse response = Mockito.mock(ResponseFacade.class);
	
	@Mock
	static ManagerDao md = Mockito.mock(ManagerDaoImp.class);
	
	@Mock
	static UserDao ud = Mockito.mock(UserDaoImp.class);
	
	@InjectMocks
	static ManagerDelegate mde;
	
	@BeforeClass
	public static void setUp() {
		//fake token for EmployeeDelegate constructor.
		String token = "11:robbie:1";
		Mockito.when(request.getHeader("Authorization")).thenReturn(token);
		mde = new ManagerDelegate(request);
		
	}
	
	@Test
	public void requestActionTest() {
		//fake reimbursement request for addReim().
		Mockito.when(request.getParameter("reimID")).thenReturn("1");
		Mockito.when(request.getParameter("action")).thenReturn("1");
		Mockito.when(md.requestAction(1, 1, 11)).thenReturn(true);

		mde.requestAction(request, response);;
		
	}//passed
	
	@Test
	public void viewAllPendingTest() {
		List<Reimbursment> fakePending = new ArrayList<>();
		fakePending.add(new Reimbursment());
		
		Mockito.when(md.viewAllPending()).thenReturn(fakePending);
		
		mde.viewAllPending(request, response);
		
	}//passed
	
	@Test
	public void viewAllResolvedTest() {
		List<Reimbursment> fakeResolved = new ArrayList<>();
		fakeResolved.add(new Reimbursment());
		
		Mockito.when(md.viewAllResolved()).thenReturn(fakeResolved);
		
		mde.viewAllResolved(request, response);
		
	}//passed

	@Test
	public void viewAllEmployeeTest() {
		List<User> fakeEmployees = new ArrayList<>();
		fakeEmployees.add(new User());
		
		Mockito.when(ud.viewAllEmployee()).thenReturn(fakeEmployees);
		
		mde.viewAllEmployee(request, response);
		
	}//passed
	
	@Test
	public void viewAllRquestByIdTest() {
		
		Mockito.when(request.getHeader("employeeID")).thenReturn("11");
		
		mde.viewAllRquestById(request, response);
		
	}//passed
}
