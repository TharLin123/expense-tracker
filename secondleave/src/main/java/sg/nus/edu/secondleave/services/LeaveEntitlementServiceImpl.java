package sg.nus.edu.secondleave.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.nus.edu.secondleave.model.Employee;
import sg.nus.edu.secondleave.model.LeaveEntitlement;
import sg.nus.edu.secondleave.repo.EmployeeRepository;
import sg.nus.edu.secondleave.repo.LeaveEntitlementRepository;
import sg.nus.edu.secondleave.util.TypeEnum;

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
	
	@Override
	@Transactional
	public Collection<LeaveEntitlement> setEntitlement(boolean isEmpProfessional,Employee emp)
	{
		LeaveEntitlement newLeaveEntitlement1 = new LeaveEntitlement();
		LeaveEntitlement newLeaveEntitlement2 = new LeaveEntitlement();
		LeaveEntitlement newLeaveEntitlement3 = new LeaveEntitlement();
		Collection<LeaveEntitlement> entitlementCollection = new ArrayList<>();
		
		if (isEmpProfessional == true) {
			newLeaveEntitlement1.setType(TypeEnum.ANNUAL);
			newLeaveEntitlement1.setEntitlement(18.0);
			newLeaveEntitlement1.setEmployee(emp);
			newLeaveEntitlement2.setType(TypeEnum.MEDICAL);
			newLeaveEntitlement2.setEntitlement(60.0);
			newLeaveEntitlement2.setEmployee(emp);
			newLeaveEntitlement3.setType(TypeEnum.COMPENSATION);
			newLeaveEntitlement3.setEntitlement(0.0);
			newLeaveEntitlement3.setEmployee(emp);
			entitlementCollection.add(newLeaveEntitlement1);
			entitlementCollection.add(newLeaveEntitlement2);
			entitlementCollection.add(newLeaveEntitlement3);
		}
		else
		{
			newLeaveEntitlement1.setType(TypeEnum.ANNUAL);
			newLeaveEntitlement1.setEntitlement(14.0);
			newLeaveEntitlement1.setEmployee(emp);
			newLeaveEntitlement2.setType(TypeEnum.MEDICAL);
			newLeaveEntitlement2.setEntitlement(60.0);
			newLeaveEntitlement2.setEmployee(emp);
			newLeaveEntitlement3.setType(TypeEnum.COMPENSATION);
			newLeaveEntitlement3.setEntitlement(0.0);
			newLeaveEntitlement3.setEmployee(emp);
			entitlementCollection.add(newLeaveEntitlement1);
			entitlementCollection.add(newLeaveEntitlement2);
			entitlementCollection.add(newLeaveEntitlement3);
		}
		return entitlementCollection;
	}
	
	
}
