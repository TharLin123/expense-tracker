package sg.nus.edu.secondleave.model;

import lombok.Data;

@Data
public class EmployeeInfo {
	private Integer employeeId;
	private String name;
	private String username;
	private Integer annualLeaveN;
	private Integer medicalLeaveN;
	private Integer compLeaveN;
	public EmployeeInfo(Integer employeeId, String name, String username, Integer annualLeaveN, Integer medicalLeaveN,
			Integer compLeaveN) {
		super();
		this.employeeId = employeeId;
		this.name = name;
		this.username = username;
		this.annualLeaveN = annualLeaveN;
		this.medicalLeaveN = medicalLeaveN;
		this.compLeaveN = compLeaveN;
	}
	
	

}
