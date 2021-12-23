package sg.nus.edu.secondleave.services;


import java.util.List;

import sg.nus.edu.secondleave.model.Employee;
import sg.nus.edu.secondleave.model.LeaveEntitlement;


public interface LeaveEntitlementService {
	public List<LeaveEntitlement> findLeaveEntitlement();
	public Employee findEmpById(int employeeId);
	public LeaveEntitlement createLeaveEntitlement(LeaveEntitlement LeaveEnt);
	public LeaveEntitlement editLeaveEntitlement(LeaveEntitlement LeaveEnt);
	

	

}
