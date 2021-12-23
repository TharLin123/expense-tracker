package sg.nus.edu.secondleave.model;


import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import sg.nus.edu.secondleave.util.LeaveEnum;
import sg.nus.edu.secondleave.util.TypeEnum;

@Entity
@NoArgsConstructor
public class LeaveApplication {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer leaveAppId;
	
	@Enumerated(EnumType.STRING)
	private TypeEnum type;
	
	//Xin just comment this notempty for date to let the save process run
	//if i could figure out how to validate notempty i will put it back
	//@NotEmpty
	//@Temporal(TemporalType.DATE)
	@Column(name = "fromdate")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate fromDate;
	
	//@NotEmpty
	//@Temporal(TemporalType.DATE)
	@Column(name = "todate")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate toDate;
	
	@Enumerated(EnumType.STRING)
	private LeaveEnum status;
	
	@NotEmpty
	private String reason;
		
	private String workDissemination;
	
	private String contactDetails;
	
	@ManyToOne
	private Employee employee;

	//Getters and Setters to avoid StackOverFlow caused By @Data
	public Integer getLeaveAppId() {
		return leaveAppId;
	}

	public void setLeaveAppId(Integer leaveAppId) {
		this.leaveAppId = leaveAppId;
	}

	public TypeEnum getType() {
		return type;
	}

	public void setType(TypeEnum type) {
		this.type = type;
	}

	public LocalDate getFromDate() {
		return fromDate;
	}

	public void setFromDate(LocalDate fromDate) {
		this.fromDate = fromDate;
	}

	public LocalDate getToDate() {
		return toDate;
	}

	public void setToDate(LocalDate toDate) {
		this.toDate = toDate;
	}

	public LeaveEnum getStatus() {
		return status;
	}

	public void setStatus(LeaveEnum status) {
		this.status = status;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getWorkDissemination() {
		return workDissemination;
	}

	public void setWorkDissemination(String workDissemination) {
		this.workDissemination = workDissemination;
	}

	public String getContactDetails() {
		return contactDetails;
	}

	public void setContactDetails(String contactDetails) {
		this.contactDetails = contactDetails;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}


	
}

