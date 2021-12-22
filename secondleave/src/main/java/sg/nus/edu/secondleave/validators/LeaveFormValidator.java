package sg.nus.edu.secondleave.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


import sg.nus.edu.secondleave.model.LeaveApplication;

@Component
public class LeaveFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return LeaveApplication.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		LeaveApplication leave = (LeaveApplication) target;
		if ((leave.getFromDate()!=null && leave.getToDate()!=null)&&(leave.getFromDate().compareTo(leave.getToDate()) > 0)) {
			errors.reject("toDate", "Leave end date should be greater than leave start date.");
			errors.rejectValue("toDate", "error.dates", "to date must be > from date");	
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "reason", "error.reason", "Reason is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fromDate", "error.fromDate", "From Date is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "toDate", "error.toDate", "To Date is required.");
	}

}
