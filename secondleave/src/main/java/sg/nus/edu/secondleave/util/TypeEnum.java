package sg.nus.edu.secondleave.util;

public enum TypeEnum {
	ANNUAL("ANNUAL"), MEDICAL("MEDICAL"), COMPENSATION("COMPENSATION");
	
	//Made by Xin, for fetch all leave type as a list in LeaveApplicationServiceImpl class
	private String leaveType;
	TypeEnum(String leaveType)
	{
		this.leaveType=leaveType;
	}
	public String getValue()
	{
		return leaveType;
	}
}


