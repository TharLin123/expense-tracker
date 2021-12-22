package sg.nus.edu.secondleave.model;

import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import lombok.Data;
import lombok.NoArgsConstructor;
import sg.nus.edu.secondleave.util.LeaveEnum;

@Entity
@NoArgsConstructor
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer CommentId;
	
	@OneToOne(targetEntity = LeaveApplication.class)
	private LeaveApplication leave;

	private String message;

	@Transient
	private String decision;

	public Integer getCommentId() {
		return CommentId;
	}

	public void setCommentId(Integer commentId) {
		CommentId = commentId;
	}

	public LeaveApplication getLeave() {
		return leave;
	}

	public void setLeave(LeaveApplication leaveApplication) {
		this.leave = leaveApplication;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDecision() {
		return decision;
	}

	public void setDecision(String decision) {
		this.decision = decision;
	}
}