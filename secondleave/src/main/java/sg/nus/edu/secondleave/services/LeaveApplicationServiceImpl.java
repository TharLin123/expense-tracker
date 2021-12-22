package sg.nus.edu.secondleave.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.nus.edu.secondleave.model.Employee;
import sg.nus.edu.secondleave.model.LeaveApplication;
import sg.nus.edu.secondleave.repo.EmployeeRepository;
import sg.nus.edu.secondleave.repo.LeaveApplicationRepository;

@Service
public class LeaveApplicationServiceImpl implements LeaveApplicationService {

	@Autowired
	LeaveApplicationRepository leaveAppRepo;
	
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
}
