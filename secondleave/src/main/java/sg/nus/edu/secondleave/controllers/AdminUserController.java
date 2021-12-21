package sg.nus.edu.secondleave.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sg.nus.edu.secondleave.model.Employee;
import sg.nus.edu.secondleave.model.Role;
import sg.nus.edu.secondleave.repo.EmployeeRepository;
import sg.nus.edu.secondleave.repo.RoleRepository;

@Controller
public class AdminUserController {
	
	
	@Autowired
	EmployeeRepository empRepo;
	@Autowired
	RoleRepository roleRepo;
	

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
	public String saveUser(@ModelAttribute @Valid Employee user, BindingResult bindingResult, Model model,@RequestParam("chooserole") String rolename 
			) {
		if (bindingResult.hasErrors()) {
			List<Role> e = roleRepo.findAll();
			return "adduser";
		}
		System.out.println(user.getName());
		System.out.println(rolename);
		Role choice = roleRepo.findByName(rolename);
		Set<Role> rolechoice = new HashSet<>();
		rolechoice.add(choice);
		user.setRoles(rolechoice);		
		empRepo.save(user);
		
		return "redirect:/adminlogin";
	}

}
