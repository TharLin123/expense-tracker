package sg.nus.edu.secondleave.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import sg.nus.edu.secondleave.model.Employee;
import sg.nus.edu.secondleave.model.LeaveApplication;

public interface LeaveApplicationService {

	public List<LeaveApplication> findLeaveApplications();
	
	public List<LeaveApplication> findLeaveApplicationsForApproval();
	
	public Optional<LeaveApplication> getLeaveApplication(int id);
	
	public Optional<Employee> getEmployee(int id);
	
	// this one is for fetching all enum leaveType 
	//& return a String list to show as select list in pages; 
	public List<String> findAllLeaveType();
	
	void updateLeaveApplication(int Id, String leaveEnum);

	void saveLeaveApplication(LeaveApplication leaveApp);
	
	void updateLeaveApplication(LeaveApplication leaveApplication);

	List<LeaveApplication> listAll();

	Page<LeaveApplication> findLeaveApplications(int pageNum);

	List<LeaveApplication> findLeaveApplicationsByEmployeeId(int id);
}
