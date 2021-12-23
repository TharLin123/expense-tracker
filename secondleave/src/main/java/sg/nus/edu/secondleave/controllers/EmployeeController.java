package sg.nus.edu.secondleave.controllers;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import sg.nus.edu.secondleave.model.Employee;
import sg.nus.edu.secondleave.model.HistoryResult;
import sg.nus.edu.secondleave.model.LeaveApplication;
import sg.nus.edu.secondleave.model.LeaveEntitlement;
import sg.nus.edu.secondleave.services.EmployeeServiceImpl;
import sg.nus.edu.secondleave.services.LeaveApplicationServiceImpl;
import sg.nus.edu.secondleave.services.LeaveEntitlementServiceImpl;
import sg.nus.edu.secondleave.util.LeaveEnum;
import sg.nus.edu.secondleave.validators.LeaveFormValidator;

@Controller
public class EmployeeController {

// To view and save the leave apply form, Xin needs to autowire this Service
	@Autowired
	LeaveApplicationServiceImpl laService;
	@Autowired
	LeaveEntitlementServiceImpl leService;
	
	@Autowired
	EmployeeServiceImpl emService;
//	To validate the form staff posted, Xin need to autowire this Validator(it is in the validators package )
	@Autowired
	private LeaveFormValidator laValidator;

	@InitBinder("leave")
	private void initLeaveBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
		binder.addValidators(laValidator);

	}

	@RequestMapping(value = "/employee/history")
	public String staffHisotry(HttpSession session, Model model,
			@RequestParam(required = false, defaultValue = "1") int page) {
		int size = 10;
		Employee emp = (Employee) session.getAttribute("validated");
		if (emp != null) {
			List<LeaveApplication> leaveApplications = laService.findLeaveApplications();
			List<LeaveApplication> list = new ArrayList<>();
			for (LeaveApplication leaveApplication : leaveApplications) {
				if (leaveApplication.getEmployee().getEmployeeId().equals(emp.getEmployeeId())) {
					list.add(leaveApplication);
				}
			}
			if (list.size() > 0) {

				List<LeaveApplication> newList;
				if (list.size() > page * size) {
					newList = list.subList((page - 1) * size, page * size);
				} else {
					newList = list.subList((page - 1) * size, list.size() - 1);
				}
				int totalPage;
				if ((list.size() % 10) > 0) {
					totalPage = list.size() / 10 + 1;
				} else {
					totalPage = list.size() / 10;
				}
				model.addAttribute("totalPage", totalPage);
				model.addAttribute("lhistory", newList);
				model.addAttribute("total", list.size());
				model.addAttribute("page", page);
				return "staffhistory";
			}
		}

		return "forward:/home";
	}

	@RequestMapping(value = "/staff/historyTable")
	@ResponseBody
	public HistoryResult staffHistoryTable(HttpSession session, Model model,
			@RequestParam(required = false, defaultValue = "1") int page) throws IOException {
		int size = 10;
		Employee emp = (Employee) session.getAttribute("validated");
		List<LeaveApplication> leaveApplications = laService.findLeaveApplications();
		List<LeaveApplication> list = new ArrayList<>();
		for (LeaveApplication leaveApplication : leaveApplications) {
			if (leaveApplication.getEmployee().getEmployeeId().equals(emp.getEmployeeId())) {
				leaveApplication.getEmployee().setLeaves(null);
				list.add(leaveApplication);
			}
		}
		//记录会少显示一条，大于10条和小于10条时都是，待修复
		List<LeaveApplication> newList;
		if (list.size() > 0) {
			if (list.size() > page * size) {
				newList = list.subList((page - 1) * size, page * size);
			} else {
				newList = list.subList((page - 1) * size, list.size() - 1);
			}
			int totalPage;
			if ((list.size() % 10) > 0) {
				totalPage = list.size() / 10 + 1;
			} else {
				totalPage = list.size() / 10;
			}
			model.addAttribute("totalPage", totalPage);
			model.addAttribute("lhistory", newList);
			model.addAttribute("total", list.size());
			model.addAttribute("page", page);
			HistoryResult result = new HistoryResult();
			result.setPage(page);
			result.setTotal(list.size());
			result.setTotalPage(totalPage);
			result.setData(newList);
			return result;
		}
		return null;
	}

	
	@RequestMapping(value = "/employee/leave/edit/{employeeId}",method = RequestMethod.GET)
	public String update(@PathVariable Integer employeeId,Model model) {
		Employee employee = emService.findEmpById(employeeId);
		model.addAttribute("employee",employee);
		return "updateRecord";
	}
	//修改记录（待完成）
	@RequestMapping(value = "/employee/leave/edit/{employeeId}",method = RequestMethod.POST)
	public String updateAndSave(@PathVariable Integer employeeId,@Validated User user, BindingResult bindingResult) {
	
		return null;
		
	
	}
	//删除记录（待完成）
//	
//	@RequestMapping(value="/employee/history")
//	public String staffCourseHistory(HttpSession session, Model model) {
//		Employee emp = (Employee) session.getAttribute("validated");
//		if(emp != null) {
//			if(emp.getLeaves().size()>=0) {
//				model.addAttribute("lhistory", emp.getLeaves());
//			}
//			return "staffhistory";	
//		}
//		
//		return "forward:/home";
//	}
//		

	
	// Made By Xin to show the leave apply form and save the form
	@RequestMapping("/employee/applyleave")
	public String viewLeaveForm(Model model, @ModelAttribute("User") Employee emp) {
		// 为了让跳转的表单正常显示（不出现whitelabel），我们需要跳转前就新建一个leaveapplication实例，让后将他发送给页面
		LeaveApplication la = new LeaveApplication();
		
		// 这里使用laService来获取所有的enum leavetype 以 List<String>的形式发送给页面，用来生成下拉菜单
		List<String> laType = laService.findAllLeaveType();
		
		// 这里的key字符串需要与页面的 th:action="newLeave"保持一致
		model.addAttribute("newLeave", la);
		model.addAttribute("leavetype", laType);
		
		return "leaveform-apply";
	}

	@RequestMapping(value = "/employee/saveleave", method = RequestMethod.POST)
	public ModelAndView saveLeaveForm(
			@ModelAttribute("newLeave") @Valid LeaveApplication leave,
			BindingResult bindingResult, 
			HttpSession session) 
	{				
		// 这里使用session找到登陆用户的employee对象
		Employee emp = (Employee) session.getAttribute("validated");
		leave.setEmployee(emp);
		ModelAndView mvSuccess = new ModelAndView();
		ModelAndView mvFail = new ModelAndView();
		if (bindingResult.hasErrors()) 
		{
			// back to view with empty form and all leave type
			//mvFail.addObject("newLeave", new LeaveApplication());
			mvFail.addObject("leavetype", laService.findAllLeaveType());
			mvFail.setViewName("leaveform-apply");
			return mvFail;
		}
		// if the binding success
		//LeaveEntitlement balance = leService.
		ModelAndView mv = new ModelAndView();
		String message = "New leaveapplication " + leave.getType() + " was successfully created.";
		System.out.println(message);



		// 设定表单初始状态为APPLIED
		leave.setStatus(LeaveEnum.APPLIED);
		mv.setViewName("forward:/employee/history");
		// 储存该表单并重定向回仪表盘
		laService.saveLeaveApplication(leave);
		return mv;
	}
}
