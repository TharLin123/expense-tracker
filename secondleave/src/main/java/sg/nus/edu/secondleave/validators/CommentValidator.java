package sg.nus.edu.secondleave.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import sg.nus.edu.secondleave.model.Comment;

@Component
public class CommentValidator implements Validator{

	@Override
	public boolean supports(Class<?> arg) {
		return Comment.class.isAssignableFrom(arg);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Comment com = (Comment) target;
		if(com.getDecision() == null) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "decision", "error.comment.decision.empty","Please choose approve or reject");
		}
		else if(com.getDecision().equals("approved")) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "decision", "error.comment.decision.empty","Please choose approve or reject");
		}
		else if(com.getDecision().equals("rejected")) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "decision", "error.comment.decision.empty","Please choose approve or reject");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "message", "error.comment.message.empty","You must comment reason to reject this leave");
		}
		
	}

}
