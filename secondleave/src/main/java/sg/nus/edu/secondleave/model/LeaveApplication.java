package sg.nus.edu.secondleave.model;


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
import lombok.NoArgsConstructor;
import sg.nus.edu.secondleave.util.LeaveEnum;
import sg.nus.edu.secondleave.util.TypeEnum;

@Entity
@Data
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
	@Temporal(TemporalType.DATE)
	@Column(name = "fromdate")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date fromDate;
	
	//@NotEmpty
	@Temporal(TemporalType.DATE)
	@Column(name = "todate")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date toDate;
	
	@Enumerated(EnumType.STRING)
	private LeaveEnum status;
	
	@NotEmpty
	private String reason;
	
	// Made By Xin, just for store the leave type value posted by the "leaveform-apply" page
	private String leaveType;
	
	private String workDissemination;
	
	private String contactDetails;
	
	@ManyToOne
	private Employee employee;
	
}

