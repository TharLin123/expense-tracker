package sg.nus.edu.secondleave.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import sg.nus.edu.secondleave.model.Employee;

@Component
public class employeeValidator implements Validator {
	@Override
	public boolean supports(Class<?> arg) {
		return Employee.class.isAssignableFrom(arg);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Employee emp = (Employee) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "error.user.username.empty","Userame cannot be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "error.user.name.empty","Name cannot be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.user.password.empty","Password cannot be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "roles", "error.user.roles.empty","Please choose at least 1 role");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "managerId", "error.user.roles.empty","Please choose a manager");
		System.out.println(emp.toString());
	}

}
