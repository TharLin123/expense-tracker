package sg.nus.edu.secondleave.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.nus.edu.secondleave.model.Employee;
import sg.nus.edu.secondleave.model.Role;
import sg.nus.edu.secondleave.repo.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepo;
	
	@Override
	public Employee authenticate(String username, String password) {
		return employeeRepo.authenticate(username, password);
	}
	
	
	@Override
	@Transactional
	public void removeUser(Employee emp) {
		employeeRepo.deleteEmp(emp.getEmployeeId());
	}

	@Override
	@Transactional
	public List<Employee> findAllEmployees() {
		List<Employee> empList = employeeRepo.findAll();
		return empList;
	}

	@Override
	@Transactional
	public Employee findEmpById(int employeeId) {
		Employee empFind = employeeRepo.findByemployeeId(employeeId);
		return empFind;
	}

	@Override
	@Transactional
	public Employee createEmp(Employee emp) {
		return employeeRepo.save(emp);
	}


	@Override
	@Transactional
	public Employee editEmp(Employee emp) {
		return employeeRepo.save(emp);
	}


	@Override
	@Transactional
	public List<Employee> findAllManager() {
		
		List<Employee> empList = employeeRepo.findAll();
		List<Employee> managerList = new ArrayList<>();
		for (Employee emp : empList) {
			Set<Role> tempEmpset = emp.getRoles();
			Iterator<Role> iter = tempEmpset.iterator();
			while (iter.hasNext()) {
				Role role =(Role)iter.next();
				if(role.getRoleId() == 2)
				{
					managerList.add(emp);
				}
			}
		}
		return managerList;
	}
	
}