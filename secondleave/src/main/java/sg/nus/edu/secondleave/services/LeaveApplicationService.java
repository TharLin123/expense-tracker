package sg.nus.edu.secondleave.services;

import java.util.List;
import java.util.Optional;

import sg.nus.edu.secondleave.model.Employee;
import sg.nus.edu.secondleave.model.LeaveApplication;

public interface LeaveApplicationService {

	public List<LeaveApplication> findLeaveApplications();
	
	public Optional<LeaveApplication> getLeaveApplication(int id);
	
	public Optional<Employee> getEmployee(int id);

	void saveLeaveApplication(LeaveApplication leaveApp);
	
	// this one is for fetching all enum leaveType 
	//& return a String list to show as select list in pages; 
	public List<String> findAllLeaveType();
}
