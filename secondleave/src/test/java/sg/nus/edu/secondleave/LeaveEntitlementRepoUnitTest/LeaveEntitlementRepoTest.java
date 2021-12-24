package sg.nus.edu.secondleave.LeaveEntitlementRepoUnitTest;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import sg.nus.edu.secondleave.SecondleaveApplication;
import sg.nus.edu.secondleave.model.Employee;
import sg.nus.edu.secondleave.model.LeaveEntitlement;
import sg.nus.edu.secondleave.repo.LeaveEntitlementRepository;
import sg.nus.edu.secondleave.services.EmployeeServiceImpl;
import sg.nus.edu.secondleave.util.TypeEnum;



@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = SecondleaveApplication.class)
@TestMethodOrder(OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class LeaveEntitlementRepoTest {
	@Autowired
	LeaveEntitlementRepository leRepo;
	@Autowired
	EmployeeServiceImpl empService;
	
	@Test
	@Order(1)
	public void testFetchLeaveEntitlement()
	{
		Employee targetEmp = empService.findEmpById(2);
		LeaveEntitlement le = leRepo.findTopByEmployeeAndType(targetEmp, TypeEnum.ANNUAL);
		System.out.println(le.getEntitlement());
	}
}
