package sg.nus.edu.secondleave.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.nus.edu.secondleave.model.Employee;

@Controller
public class EmployeeController {

	
	@RequestMapping(value="/employee/history")
	public String staffCourseHistory(HttpSession session, Model model) {
		Employee emp = (Employee) session.getAttribute("validated");
		if(emp != null) {
			if(emp.getLeaves().size()>=0) {
				model.addAttribute("lhistory", emp.getLeaves());
			}
			return "employeedashboard";	
		}
		
		return "forward:/home";
	}
}
