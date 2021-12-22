//package sg.nus.edu.secondleave.controllers;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.List;
//
//import javax.servlet.http.HttpSession;
//import javax.validation.Valid;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.propertyeditors.CustomDateEditor;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.WebDataBinder;
//import org.springframework.web.bind.annotation.InitBinder;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.servlet.ModelAndView;
//
//import sg.nus.edu.secondleave.model.Employee;
//import sg.nus.edu.secondleave.model.LeaveApplication;
//import sg.nus.edu.secondleave.services.LeaveApplicationServiceImpl;
//import sg.nus.edu.secondleave.util.LeaveEnum;
//
//@Controller
//public class EmployeeController {
//
//	
//	//To view and save the leave apply form, Xin needs to autowire this Service
//	@Autowired
//	LeaveApplicationServiceImpl laService;
////	To validate the form staff posted, Xin need to autowire this Validator(it is in the validators package )
//	@Autowired
//	private LeaveFormValidator laValidator;
//	
//	@InitBinder("leave")
//	private void initLeaveBinder(WebDataBinder binder) {
//		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//		dateFormat.setLenient(false);
//		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
//		binder.addValidators(laValidator);
//
//	}
//	
//	@RequestMapping(value="/employee/history")
//	public String staffCourseHistory(HttpSession session, Model model) {
//		Employee emp = (Employee) session.getAttribute("validated");
//		if(emp != null) {
//			if(emp.getLeaves().size()>=0) {
//				model.addAttribute("lhistory", emp.getLeaves());
//			}
//			return "employeedashboard";	
//		}
//		
//		return "forward:/home";
//	}
//		
//		//Made By Xin to show the leave apply form and save the form
//		@RequestMapping("/employee/applyleave")
//		public String viewLeaveForm(Model model) {
//			// 为了让跳转的表单正常显示（不出现whitelabel），我们需要跳转前就新建一个leaveapplication实例，让后将他发送给页面
//			LeaveApplication la = new LeaveApplication();
//			// 这里使用laService来获取所有的enum leavetype 以 List<String>的形式发送给页面，用来生成下拉菜单
//			List<String> laType = laService.findAllLeaveType();
//			// 这里的key字符串需要与页面的 th:action="newLeave"保持一致
//			model.addAttribute("newLeave", la);
//			model.addAttribute("leavetype",laType);
//			return "leaveform-apply";
//		}
//		
//		@RequestMapping(value="/employee/saveleave",method = RequestMethod.POST)
//		public ModelAndView saveLeaveForm(@ModelAttribute("newLeave") @Valid LeaveApplication leave
//				,BindingResult bindingResult,HttpSession session) {
//			
//			UserSession userSession = (UserSession) session.getAttribute("empsession");
//			if(bindingResult.hasErrors())
//			{
//				return new ModelAndView("leaveform-apply");
//			}
//			ModelAndView mv = new ModelAndView();
//			String message = "New leaveapplication " + leave.getLeaveAppId() + " was successfully created.";
//			System.out.println(message);
//			//这里使用session找到登陆用户的employee对象	
//			Employee emp = (Employee)session.getAttribute("validated");
//			//先在console里看下Session里有没有登录过的对象
//			System.out.println(emp);
//			
//			leave.setEmployee(emp);
//			//设定表单初始状态为APPLIED
//			leave.setStatus(LeaveEnum.APPLIED);
//			mv.setViewName("forward:/employee/history");
//			//储存该表单并重定向回仪表盘
//			laService.saveLeaveApplication(leave);
//			return mv;
//		}
//}
