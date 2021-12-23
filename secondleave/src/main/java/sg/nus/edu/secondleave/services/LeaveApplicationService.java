package sg.nus.edu.secondleave.services;

import java.util.List;
import java.util.Optional;

import sg.nus.edu.secondleave.model.Employee;
import sg.nus.edu.secondleave.model.LeaveApplication;
import sg.nus.edu.secondleave.util.LeaveEnum;

public interface LeaveApplicationService {

	public List<LeaveApplication> findLeaveApplications();
	
	public List<LeaveApplication> findLeaveApplicationsByEmployeeId(int id);
	
	public Optional<LeaveApplication> getLeaveApplication(int id);
	
	public Optional<Employee> getEmployee(int id);

	void saveLeaveApplication(Optional<LeaveApplication> leaveApp);
	
	// this one is for fetching all enum leaveType 
	//& return a String list to show as select list in pages; 
	public List<String> findAllLeaveType();

	
	void updateLeaveApplication(int Id, String leaveEnum);

	void saveLeaveApplication(LeaveApplication leaveApp);
}
