package sg.nus.edu.secondleave.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.nus.edu.secondleave.model.Employee;
import sg.nus.edu.secondleave.model.LeaveEntitlement;
import sg.nus.edu.secondleave.repo.EmployeeRepository;
import sg.nus.edu.secondleave.repo.LeaveEntitlementRepository;

@Service
public class LeaveEntitlementServiceImpl implements LeaveEntitlementService {
	@Autowired
	private EmployeeRepository employeeRepo;
	@Autowired
	private LeaveEntitlementRepository leaveEntitlementRepo;
	
	@Override
	@Transactional
	public Employee findEmpById(int employeeId) {
		Employee empFind = employeeRepo.findByemployeeId(employeeId);
		return empFind;
	}

	@Override
	@Transactional
	public List<LeaveEntitlement> findLeaveEntitlement() {
		return leaveEntitlementRepo.findAll();
	}

	@Override
	@Transactional
	public LeaveEntitlement createLeaveEntitlement(LeaveEntitlement LeaveEnt) {
		return leaveEntitlementRepo.saveAndFlush(LeaveEnt);
	}

	@Override
	@Transactional
	public LeaveEntitlement editLeaveEntitlement(LeaveEntitlement LeaveEnt) {
		return leaveEntitlementRepo.saveAndFlush(LeaveEnt);
	}
	
	
}
