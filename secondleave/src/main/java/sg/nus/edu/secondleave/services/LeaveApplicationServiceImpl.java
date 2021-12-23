package sg.nus.edu.secondleave.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.nus.edu.secondleave.model.Employee;
import sg.nus.edu.secondleave.model.LeaveApplication;
import sg.nus.edu.secondleave.repo.EmployeeRepository;
import sg.nus.edu.secondleave.repo.LeaveApplicationRepository;
import sg.nus.edu.secondleave.util.TypeEnum;

@Service
public class LeaveApplicationServiceImpl implements LeaveApplicationService {

	@Autowired
	private LeaveApplicationRepository leaveAppRepo;
	
	@Autowired
	private EmployeeRepository employeeRepo;
	
	@Override
	@Transactional
	public List<LeaveApplication> findLeaveApplications() {
		return leaveAppRepo.findAll();
	}

	@Override
	@Transactional
	public Optional<LeaveApplication> getLeaveApplication(int id) {
		return leaveAppRepo.findById(id);
	}

	@Override
	@Transactional
	public Optional<Employee> getEmployee(int id) {
		return employeeRepo.findById(id);
	}

	@Override
	@Transactional
	public void saveLeaveApplication(LeaveApplication leaveApp) {
		leaveAppRepo.save(leaveApp);
	}
	
	@Override
	public List<String> findAllLeaveType() {
		// Use this to fetch all enum leave type as list<String>
		List<String> leaveTypes = Stream.of(TypeEnum.values())
									.map(TypeEnum::getValue)
									.collect(Collectors.toList());
		return leaveTypes;
	}

	@Override
	public List<LeaveApplication> findLeaveApplicationsByEmployeeId(int id) {
		return leaveAppRepo.findLAPByEmployeeId(id);
	}


	@Override
	@Transactional
	public void updateLeaveApplication(int Id, String leaveEnum) {
		leaveAppRepo.updateLeaveApplication(Id, leaveEnum);
	}

	@Override
	public List<LeaveApplication> findLeaveApplicationsForApproval() {
		return leaveAppRepo.findLapForApproval();
	}
}
