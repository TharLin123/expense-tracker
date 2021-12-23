package sg.nus.edu.secondleave.services;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.nus.edu.secondleave.model.LeaveApplication;
import sg.nus.edu.secondleave.model.LeaveEntitlement;
import sg.nus.edu.secondleave.repo.HolidayRepository;
import sg.nus.edu.secondleave.repo.LeaveEntitlementRepository;

@Service
public class HolidayServiceImpl implements HolidayService {

	@Autowired
	HolidayRepository holidayRepo;
	@Autowired
	LeaveEntitlementRepository leRepository;
	
	@Override
	@Transactional
	// this method is to compute how many workdays he will leave(without holiday or SAT SUN)
	public int findLeaveDaysWithoutHoliday(LocalDate start, LocalDate end) {
		Period p = Period.between(start, end);
		
		int res = p.getDays();
		
		LocalDate countDate = LocalDate.of(start.getYear(), start.getMonthValue(), start.getDayOfMonth());
		
		List<LocalDate> holidays = holidayRepo.findAllDate();
		
		// cuz we use the isAfter as condition, so we need to return res+1;
		while(!countDate.isAfter(end)) {
			
			if(holidays.contains(countDate)
					||countDate.getDayOfWeek().equals(DayOfWeek.SUNDAY)
					||countDate.getDayOfWeek().equals(DayOfWeek.SATURDAY))
			{
				res--; //this will run one more time.
			}
			
			countDate = countDate.plusDays(1);
		}
		return res+1;
	}

	@Override
	@Transactional
	// this method is to find all holiday date.
	public List<LocalDate> findAllDate() {
		
		return holidayRepo.findAllDate();
	}

	@Override
	@Transactional
	// this method is to check whether the from date and end date of one leave application is a work day
	public boolean isWorkingDay(LeaveApplication la) {
		LocalDate from = la.getFromDate();
		LocalDate to = la.getToDate();
		List<LocalDate> holidays = holidayRepo.findAllDate();
		if (holidays.contains(from) || from.getDayOfWeek().equals(DayOfWeek.SUNDAY)
				|| from.getDayOfWeek().equals(DayOfWeek.SATURDAY)) {
			return false; // if the FROM date is SAT SUN or Holiday return false
		} 
		else if (holidays.contains(to) || to.getDayOfWeek().equals(DayOfWeek.SUNDAY)
				|| to.getDayOfWeek().equals(DayOfWeek.SATURDAY)) 
		{
			return false;// if the TO date is SAT SUN or Holiday return false
		}
		else 
		{
			return true;//if both them are work day
		}
	}

	@Override
	public boolean isBalanceEnough(LeaveApplication la) {
		//this part may cause bug because of the EnumType
		LeaveEntitlement leaveEntitlement = leRepository.findByEmployeeAndType(la.getEmployee(), 
				la.getType());
		double balance = leaveEntitlement.getEntitlement();
		int leavedays = 0;
		// if it is an Annual leave, 
		// when its period is greater than 14 days the weekend & holiday will be included
		if(la.getType().getValue()=="ANNUAL" 
				&& Period.between(la.getFromDate(),la.getToDate()).getDays()>14)
		{
			leavedays = Period.between(la.getFromDate(),la.getToDate()).getDays();
		}
		else
		{
			leavedays = findLeaveDaysWithoutHoliday(la.getFromDate(), la.getToDate());
		}
		
		if (balance<=0) 
		{
			return false;
		}
		else if (leavedays > balance) 
		{
			return false;
		}
		else
		{
			return true;
		}
	}

}
