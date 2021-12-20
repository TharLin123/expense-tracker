package sg.nus.edu.secondleave.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.nus.edu.secondleave.model.Employee;

@Controller
public class AdminUserController {

	@RequestMapping(value="/admin/history")
	public String adminpage(HttpSession session, Model model) {
		Employee emp = (Employee) session.getAttribute("admvalidated");
		if(emp != null) {
			System.out.println(emp.getName());
			return "staffhistory";	
		}
		
		return "forward:/home";
	}
}
