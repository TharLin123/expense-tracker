package sg.nus.edu.secondleave.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.nus.edu.secondleave.model.Employee;
import sg.nus.edu.secondleave.repo.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepo;
	
	@Override
	public Employee authenticate(String username, String password) {
		return employeeRepo.authenticate(username, password);
	}
}