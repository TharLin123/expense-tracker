package sg.nus.edu.secondleave.services;

import java.time.LocalDate;
import java.util.List;

import sg.nus.edu.secondleave.model.LeaveApplication;

public interface HolidayService {
	int findLeaveDaysWithoutHoliday(LocalDate start, LocalDate end);

	List<LocalDate> findAllDate();

	boolean isWorkingDay(LeaveApplication la);
	
	boolean isBalanceEnough(LeaveApplication la);
}
