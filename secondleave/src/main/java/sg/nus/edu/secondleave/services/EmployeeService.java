package sg.nus.edu.secondleave.services;

import sg.nus.edu.secondleave.model.Employee;

public interface EmployeeService {
	public Employee authenticate(String username, String password);
}
