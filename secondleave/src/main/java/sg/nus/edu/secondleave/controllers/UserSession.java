package sg.nus.edu.secondleave.controllers;

import lombok.Data;
import lombok.NoArgsConstructor;
import sg.nus.edu.secondleave.model.Employee;


@Data
@NoArgsConstructor
public class UserSession {
	
	private Employee employee;

	public UserSession(Employee employee) {
		super();
		this.employee = employee;
	}
	
	
}

