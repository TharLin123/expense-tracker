package sg.nus.edu.secondleave.controllers;

import java.util.ArrayList;
import java.util.Arrays;

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
import sg.nus.edu.secondleave.model.LeaveEntitlement;
import sg.nus.edu.secondleave.repo.EmployeeRepository;
import sg.nus.edu.secondleave.repo.LeaveEntitlementRepository;
import sg.nus.edu.secondleave.services.EmployeeService;
import sg.nus.edu.secondleave.services.LeaveEntitlementService;

@Controller
@RequestMapping("/admin/manageLeave")
public class AdminManageLeaveController {

	@Autowired
	LeaveEntitlementService leServ;
	@Autowired
	EmployeeService empServ;
	@Autowired
	EmployeeRepository empRepo;
	@Autowired
	LeaveEntitlementRepository leRepo;

	/**
	 * LEAVE ENTITLEMENT CRUD OPERATIONS
	 * 
	 * @return
	 */

	/* List Employees */
	@GetMapping("/list")
	public ModelAndView leaveEntitlementList() {
		ModelAndView mav = new ModelAndView("leaveEnt");
		List<Employee> empList = empServ.findAllEmployees();
		mav.addObject("empList", empList);
		return mav;
	}

	/* Create Leave Entitlement */
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView newLeaveEntitlementPage() {
		ModelAndView mav = new ModelAndView("leaveEntitlement", "leaveEntitlement", new LeaveEntitlement());
		ArrayList<String> eidList = new ArrayList<String>(Arrays.asList("employee", "manager", "admin"));
		mav.addObject("eidlist", eidList);
		return mav;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ModelAndView createNewLeaveEntitlement(@ModelAttribute @Valid LeaveEntitlement leaveEntitlement,
			BindingResult result) {
		if (result.hasErrors())
			return new ModelAndView("leaveEntitlement");
		ModelAndView mav = new ModelAndView();
		ArrayList<String> eidList = new ArrayList<String>(Arrays.asList("employee", "manager", "admin"));
		System.out.println(eidList);
		System.out.println(leaveEntitlement.toString());
		mav.setViewName("forward:/admin/manageLeave/list");
		return mav;
	}
	
	/* Edit Leave Entitlement */
	@RequestMapping(value = "/edit{id}", method = RequestMethod.GET)
	public ModelAndView editLeaveEntitlementPage(@PathVariable String id) {
		ModelAndView mav = new ModelAndView("leaveEntitlement", "leaveEntitlement", new LeaveEntitlement());
		ArrayList<String> eidList = new ArrayList<String>(Arrays.asList("employee", "manager", "admin"));
		mav.addObject("eidlist", eidList);
		return mav;
	}

	@RequestMapping(value = "/edit{id}", method = RequestMethod.POST)
	public ModelAndView editLeave(@ModelAttribute @Valid LeaveEntitlement leaveEntitlement, 
			BindingResult result, @PathVariable String id) {
		if (result.hasErrors())
			return new ModelAndView("leaveEntitlement");
		ModelAndView mav = new ModelAndView();
		ArrayList<String> eidList = new ArrayList<String>(Arrays.asList("employee", "manager", "admin"));
		System.out.println(eidList);
		System.out.println(leaveEntitlement.toString());
		mav.setViewName("forward:/admin/manageLeave/list");
		return mav;
	}
	

}

