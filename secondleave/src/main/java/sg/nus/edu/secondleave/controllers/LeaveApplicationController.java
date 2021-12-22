package sg.nus.edu.secondleave.controllers;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.nus.edu.secondleave.model.Comment;
import sg.nus.edu.secondleave.model.LeaveApplication;
import sg.nus.edu.secondleave.services.LeaveApplicationService;
import sg.nus.edu.secondleave.util.LeaveEnum;
import sg.nus.edu.secondleave.util.TypeEnum;

@Controller
@RequestMapping("/leaves")
public class LeaveApplicationController {
	
	@Autowired
	LeaveApplicationService leaveAppService;

	@GetMapping("/view")
	public String viewLeaveApp(Model model) {
		List<LeaveApplication> leaveApps = leaveAppService.findLeaveApplications();
		model.addAttribute("leaves",leaveApps);
		return "LeaveApplicationView";
	}
	
	@GetMapping("/detail/{id}")
	public String detailLeaveApp(Model model,@PathVariable int id) {
		Comment comment = new Comment();
		Optional<LeaveApplication> leaveApp = leaveAppService.getLeaveApplication(id);
		List<LeaveApplication> leaveApps = leaveAppService.findLeaveApplications();
		model.addAttribute("leaves",leaveApps);
		model.addAttribute("leave",leaveApp.get());
		model.addAttribute("comment", comment);
		return "LeaveApplicationDetail";
	}
	
	@PostMapping("/approve/{id}")
	public void approveLeaveApp(@ModelAttribute("comment") @Valid Comment comment,@PathVariable int id) {
		Optional<LeaveApplication> leaveApp = leaveAppService.getLeaveApplication(id);
		leaveApp.get().setStatus(LeaveEnum.APPROVED);
		comment.setLeave(leaveApp);
		System.out.println(comment.getMessage());
		System.out.println(comment.getLeave().get().getStatus());
	}
	
	@PostMapping("/reject/{id}")
	public void rejectLeaveApp(@ModelAttribute("comment") @Valid Comment comment,@PathVariable int id) {
		Optional<LeaveApplication> leaveApp = leaveAppService.getLeaveApplication(id);
		leaveApp.get().setStatus(LeaveEnum.REJECTED);
		comment.setLeave(leaveApp);
		System.out.println(comment.getMessage());
		System.out.println(comment.getLeave().get().getStatus());
	}
	
//	Mock Data
	@GetMapping("/save")
	public void saveLeaveApp() {
		LeaveApplication lap = new LeaveApplication();
		lap.setEmployee(leaveAppService.getEmployee(1).get());
		lap.setFromDate(new Date());
		lap.setToDate(new Date());
		lap.setStatus(LeaveEnum.APPLIED);
		lap.setType(TypeEnum.ANNUAL);
		leaveAppService.saveLeaveApplication(lap);
	}
}
