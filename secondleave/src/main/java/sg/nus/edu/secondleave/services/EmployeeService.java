package sg.nus.edu.secondleave.services;

import java.util.List;

import sg.nus.edu.secondleave.model.Employee;

public interface EmployeeService {
	public Employee authenticate(String username, String password);
	
	public void removeUser(Employee emp);
	public List<Employee> findAllEmployees();
	public Employee findEmpById(int employeeId);
	public Employee createEmp(Employee emp);
}
