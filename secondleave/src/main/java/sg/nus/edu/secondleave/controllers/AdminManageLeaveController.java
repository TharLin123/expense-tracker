package sg.nus.edu.secondleave.controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import sg.nus.edu.secondleave.model.Employee;
import sg.nus.edu.secondleave.model.EmployeeInfo;
import sg.nus.edu.secondleave.model.LeaveEntitlement;
import sg.nus.edu.secondleave.repo.EmployeeRepository;
import sg.nus.edu.secondleave.repo.LeaveEntitlementRepository;
import sg.nus.edu.secondleave.services.EmployeeService;
import sg.nus.edu.secondleave.util.TypeEnum;

@Controller
@RequestMapping("/admin/manageleave")
public class AdminManageLeaveController {

	
	@Autowired
	EmployeeService empServ;
//	@Autowired
//	LeaveEntitlementService leServ;
	@Autowired
	EmployeeRepository empRepo;
	@Autowired
	LeaveEntitlementRepository leRepo;
//	@Autowired
//	EmployeeService eService;

	/**
	 * LEAVE ENTITLEMENT CRUD OPERATIONS
	 * 
	 * @return
	 */

	/* List Employees Leave Entitlement */

	 @GetMapping("/list")
	 public ModelAndView employeeList() {
	  ModelAndView mav = new ModelAndView("LeaveEnt-List");
	  List<Employee> empList = empServ.findAllEmployees();
	  List<LeaveEntitlement> leaveList = leRepo.findAll();
	  mav.addObject("empList", empList);
	  mav.addObject("leavelist", leaveList);

	  return mav;
	 }

	 @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	 public ModelAndView editEmployeePage(@PathVariable String id) {
	  ModelAndView mav = new ModelAndView("LeaveEnt-Edit");
	  Employee employee = empServ.findEmpById(Integer.parseInt(id));
	
		// Object[] arr = employee.getLeaveEntitlements().toArray();/
		// KIV - Still solving set to 0 0 0 issue.
		// fixed please see below - zhi jie
		Collection<LeaveEntitlement> LE = employee.getLeaveEntitlements();
		double annual=0.0, medical=0.0, compensation=0.0;
		for (LeaveEntitlement Leave : LE) {
			if (Leave.getType() == TypeEnum.ANNUAL) {
				annual = Leave.getEntitlement();
			}
			if (Leave.getType() == TypeEnum.MEDICAL) {
				medical = Leave.getEntitlement();
			}
			if (Leave.getType() == TypeEnum.COMPENSATION) {
				compensation = Leave.getEntitlement();
			}
		}

		EmployeeInfo eInfo = new EmployeeInfo(employee.getEmployeeId(), employee.getName(), employee.getUsername(), annual,
				medical, compensation);
	  
	  
	  mav.addObject("employee", eInfo);

	  List<Employee> employeelist = empServ.findAllEmployees();
	  ArrayList<String> reportlist = new ArrayList<>();
	  for (Employee theEmp: employeelist) {
	   reportlist.add(String.valueOf(theEmp.getEmployeeId()));
	  }
	  mav.addObject("reportlist",reportlist);
	  return mav;
	 }
	 
	 @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	 public String editEmployee(@ModelAttribute @Valid EmployeeInfo employee, BindingResult result,
	          @PathVariable String id) {
//	  if (result.hasErrors())
//	   return new ModelAndView("LeaveEnt-Edit");
	  
//	  ModelAndView mav = new ModelAndView("forward:/admin/create");
	  String message = "Employee was successfully updated.";
	  System.out.println(message);
	  System.out.println(employee.getAnnualLeaveN());
	  System.out.println(employee.getMedicalLeaveN());
	  System.out.println(employee.getCompLeaveN());
	  System.out.println(employee.getEmployeeId());
	  
	
	  
	  empRepo.updateAnnualValue(employee.getAnnualLeaveN(), employee.getEmployeeId());
	
	  empRepo.updateCompValue(employee.getCompLeaveN(), employee.getEmployeeId());
	  empRepo.updateMedicalValue(employee.getMedicalLeaveN(), employee.getEmployeeId());
	  System.out.println("hi");

	  return "redirect:/admin/manageleave/list";
	 }
	 


}

