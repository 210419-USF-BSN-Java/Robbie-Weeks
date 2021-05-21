package delegatesTest;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.RequestFacade;
import org.apache.catalina.connector.ResponseFacade;
import org.apache.catalina.servlets.DefaultServlet;
import org.junit.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.ers.delegates.EmployeeDelegate;
import com.ers.models.Reimbursment;
import com.ers.repository.EmployeeDao;
import com.ers.repository.EmployeeDaoImp;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class EmployeeDegateTest {
	
	
	@Mock
	static HttpServletRequest request = Mockito.mock(RequestFacade.class);
	
	@Mock
	static HttpServletResponse response = Mockito.mock(ResponseFacade.class);
	
	@Mock
	static EmployeeDao ed = Mockito.mock(EmployeeDaoImp.class);
	
	@InjectMocks
	static EmployeeDelegate ede;
	
	ObjectMapper mapper = new ObjectMapper();
	
	@BeforeClass
	public static void setUp() {
		//fake token for EmployeeDelegate constructor.
		String token = "11:robbie:1";
		Mockito.when(request.getHeader("Authorization")).thenReturn(token);
		ede = new EmployeeDelegate(request);
		
		
		//fake pending reimbursement for viewPending() and viewSolved().
		List<Reimbursment> pendingList = new ArrayList<>();
		pendingList.add(new Reimbursment(500.00, "flight ticket", 11, 1, 1, 1));
		
		Mockito.when(ed.viewPending(11)).thenReturn(pendingList);
		Mockito.when(ed.viewResolved(11)).thenReturn(pendingList);
		
	}
	
//	@Test
//	public void addReimTest() throws JsonMappingException, JsonProcessingException {
//		//fake reimbursement request for addReim().
//		String reimRequest = "{\"amount\" : \"500\", \"description\" : \"flight\", \"typeID\" : \"1\"}";
//		Mockito.when(request.getHeader("reimRequest")).thenReturn(reimRequest);
//		
//		Reimbursment reim = mapper.readValue(reimRequest, Reimbursment.class); 
//		Mockito.when(ed.makeReim(reim)).thenReturn(true);
//		
//		//ede.addReim(request, response);
//		
//	}//passed
	
	@Test
	public void viewPendingTest() {
		
		ede.viewPending(request, response);
		
	}
	
	@Test
	public void viewSolvedTest() {
		
		ede.viewSolved(request, response);
		
	}
	
	@Test
	public void viewInfoTest() {
		
		
		ede.viewInfo(request, response);
		
	}
	
//	@Test
//	public void updateInfoTest() {
//		
//		//fake new user information for updateInfo().
//		Mockito.when(request.getParameter("firstName")).thenReturn("Robbie");
//		Mockito.when(request.getParameter("lastName")).thenReturn("Weeks");
//		Mockito.when(request.getParameter("email")).thenReturn("123456@gmail.com");
//		//Mockito.when(ed.updateInfo(u)).thenReturn(true);
//		
//		ede.updateInfo(request, response);
//		
//	}
	
	
}
