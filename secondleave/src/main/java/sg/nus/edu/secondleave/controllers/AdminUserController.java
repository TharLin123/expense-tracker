package sg.nus.edu.secondleave.controllers;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import sg.nus.edu.secondleave.model.Employee;
import sg.nus.edu.secondleave.model.Role;
import sg.nus.edu.secondleave.repo.EmployeeRepository;
import sg.nus.edu.secondleave.repo.RoleRepository;
import sg.nus.edu.secondleave.services.EmployeeService;

@Controller
public class AdminUserController {
	
	
	@Autowired
	EmployeeRepository empRepo;
	@Autowired
	RoleRepository roleRepo;
	@Autowired
	EmployeeService empServ;
	

	@RequestMapping(value = "/admin/history")
	public String adminpage(HttpSession session, Model model) {
		Employee emp = (Employee) session.getAttribute("admvalidated");
		if (emp != null) {
			System.out.println(emp.getName());
			return "staffhistory";
		}

		return "forward:/home";
	}

	@GetMapping("/admin/create")
	public String createUser(Model model) {
		model.addAttribute("user", new Employee());
		List<Role> e = roleRepo.findAll();
		for (Role E : e) {
			System.out.println(E.toString());
		}

		model.addAttribute("rolelist", e);
		return "adduser";
	}

	@PostMapping("/admin/create")
	public String saveUser(@ModelAttribute("user") @Valid Employee user, BindingResult bindingResult, Model model
			) {
		HashSet<Role> newRoleSet = new HashSet<Role>();
		if (bindingResult.hasErrors()) {
			
			return "adduser";
		}
		
		for (Iterator<Role> iterator = user.getRoles().iterator(); iterator.hasNext();) {
			Role type = (Role) iterator.next();
			Role newRole = roleRepo.findRole(type.getRoleId());
			newRoleSet.add(newRole);
			//System.out.println("Role: "+type.toString());
		}
		
		user.setRoles(newRoleSet);		
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
		empServ.removeUser(employee);
		System.out.println("Employee delete");
		return mav;

	}
	
	
	
}
