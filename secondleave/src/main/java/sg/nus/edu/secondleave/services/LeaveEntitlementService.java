package sg.nus.edu.secondleave.services;


import java.util.Collection;
import java.util.List;

import sg.nus.edu.secondleave.model.Employee;
import sg.nus.edu.secondleave.model.LeaveEntitlement;
import sg.nus.edu.secondleave.util.TypeEnum;


public interface LeaveEntitlementService {
	//to fetch his entitlement of one type of leave like annual
	public LeaveEntitlement findTopByEmployeeAndType(Employee employee, TypeEnum leavetype);
	//to fetch his all entitlement objects with all kinds of leave type
	public List<LeaveEntitlement> findByEmployee(Employee employee);
	public List<LeaveEntitlement> findLeaveEntitlement();
	public Employee findEmpById(int employeeId);
	public LeaveEntitlement createLeaveEntitlement(LeaveEntitlement LeaveEnt);
	public LeaveEntitlement editLeaveEntitlement(LeaveEntitlement LeaveEnt);
	public Collection<LeaveEntitlement> setEntitlement(boolean isEmpProfessional,Employee emp);
	public void removeuserEnt(Employee employee);
	

	

}
