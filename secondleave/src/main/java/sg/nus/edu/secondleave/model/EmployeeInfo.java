package sg.nus.edu.secondleave.model;

import lombok.Data;

@Data
public class EmployeeInfo {
	private Integer employeeId;
	private String name;
	private String username;
	private Double annualLeaveN;
	private Double medicalLeaveN;
	private Double compLeaveN;
	public EmployeeInfo(Integer employeeId, String name, String username, Double annualLeaveN, Double medicalLeaveN,
			Double compLeaveN) {
		super();
		this.employeeId = employeeId;
		this.name = name;
		this.username = username;
		this.annualLeaveN = annualLeaveN;
		this.medicalLeaveN = medicalLeaveN;
		this.compLeaveN = compLeaveN;
	}
	
	

}
