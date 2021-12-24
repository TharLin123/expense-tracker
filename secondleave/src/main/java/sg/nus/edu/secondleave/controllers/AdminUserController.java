package sg.nus.edu.secondleave.controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import sg.nus.edu.secondleave.model.Employee;
import sg.nus.edu.secondleave.model.LeaveEntitlement;
import sg.nus.edu.secondleave.model.Role;
import sg.nus.edu.secondleave.repo.EmployeeRepository;
import sg.nus.edu.secondleave.repo.RoleRepository;
import sg.nus.edu.secondleave.services.EmployeeService;
import sg.nus.edu.secondleave.services.LeaveEntitlementService;
import sg.nus.edu.secondleave.services.RoleService;
import sg.nus.edu.secondleave.validators.employeeValidator;

@Controller
public class AdminUserController {

	@Autowired
	EmployeeRepository empRepo;
	@Autowired
	RoleRepository roleRepo;
	@Autowired
	EmployeeService empServ;
	@Autowired
	RoleService roleServ;
	@Autowired
	LeaveEntitlementService leaveServ;
	
	@Autowired
	private employeeValidator empValidator;

	@InitBinder("user")
	private void initUserBinder(WebDataBinder binder) {
		binder.addValidators(empValidator);
	}

	@RequestMapping(value = "/admin/history")
	public String adminpage(HttpSession session, Model model) {
		Employee emp = (Employee) session.getAttribute("admvalidated");
		if (emp != null) {
			System.out.println(emp.getName());
			return "staffList";
		}
		return "forward:/home";
	}

	@GetMapping("/admin/create")
	public String createUser(Model model) {
		model.addAttribute("user", new Employee());
		List<Employee> managerList = empServ.findAllManager();
		List<Role> roleList = roleServ.findAll();
		// for testing purposes can remove
		/*
		 * for(Employee E : managerList) { System.out.println(E.getName()); } for (Role
		 * E : roleList) { System.out.println(E.toString()); }
		 */
		model.addAttribute("managerlist", managerList);
		model.addAttribute("rolelist", roleList);
		return "adduser";
	}

	/* Create User POST */
	@PostMapping("/admin/create")
	public String saveUser(@ModelAttribute("user") @Valid Employee user, BindingResult bindingResult, Model model) {
		HashSet<Role> newRoleSet = new HashSet<Role>();
		Collection<LeaveEntitlement> entitlementCollection = new ArrayList<>();
		if (bindingResult.hasErrors()) {
			List<Employee> managerList = empServ.findAllManager();
			List<Role> roleList = roleServ.findAll();
			model.addAttribute("managerlist", managerList);
			model.addAttribute("rolelist", roleList);
			return "adduser";
		}
		for (Iterator<Role> iterator = user.getRoles().iterator(); iterator.hasNext();) {
			Role type = (Role) iterator.next();
			Role newRole = roleRepo.findRole(type.getRoleId());
			newRoleSet.add(newRole);
		}
		boolean isProfessional = empServ.checkProfessional(user);
		entitlementCollection = leaveServ.setEntitlement(isProfessional, user);
		user.setRoles(newRoleSet);
		user.setLeaveEntitlements(entitlementCollection);
		empRepo.save(user);

		return "redirect:/admin/list";
	}

	/* List Users */
	@GetMapping("/admin/list")
	public ModelAndView employeeList() {
		ModelAndView mav = new ModelAndView("emplist");
		List<Employee> empList = empServ.findAllEmployees();
		mav.addObject("empList", empList);
		return mav;
	}

	/* Delete Users */
	@RequestMapping("/admin/delete/{id}")
	public ModelAndView deleteEmployee(@PathVariable String id) {
		ModelAndView mav = new ModelAndView("forward:/admin/list");

		Employee employee = empServ.findEmpById(Integer.parseInt(id));
		leaveServ.removeuserEnt(employee);
		empServ.removeUser(employee);
		System.out.println("Employee delete");
		return mav;
	}

	/* Edit User */
	@RequestMapping(value = "/admin/edit/{id}", method = RequestMethod.GET)
	public String editUser(@PathVariable String id, Model model) {
		Employee emp = empServ.findEmpById(Integer.parseInt(id));
		List<Employee> managerList = empServ.findAllManager();
		List<Role> e = roleRepo.findAll();
		for (Role E : e) {
			System.out.println(E.toString());
		}
		model.addAttribute("managerlist", managerList);
		model.addAttribute("user", emp);
		model.addAttribute("roles", e);
		return "edituser";
	}

	@RequestMapping(value = "/admin/edituser/{id}", method = RequestMethod.POST)
	public String editUser(@ModelAttribute @Valid Employee user, BindingResult bindingResult, Model model,
			@PathVariable int id) {

		if (bindingResult.hasErrors()) {
			return "redirect:/admin/list";
		}
		Collection<LeaveEntitlement> entitlementCollection = new ArrayList<>();
		boolean isProfessional = empServ.checkProfessional(user);
		entitlementCollection = leaveServ.setEntitlement(isProfessional, user);
		user.setLeaveEntitlements(entitlementCollection);
		System.out.println("edit success");
		empServ.editEmp(user);
		return "redirect:/admin/manageleave/edit/"+id;

	}

}
