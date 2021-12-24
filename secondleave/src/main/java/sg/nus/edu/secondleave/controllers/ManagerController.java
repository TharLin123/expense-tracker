package sg.nus.edu.secondleave.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import sg.nus.edu.secondleave.model.Employee;
import sg.nus.edu.secondleave.model.PageResult;
import sg.nus.edu.secondleave.services.EmployeeService;

@Controller
@RequestMapping("manager")
public class ManagerController {

    @Resource
    private EmployeeService employeeService;

    @RequestMapping("staffListView")
    public String staffList() {
        return "staffList";
    }

    @RequestMapping("staffList")
    @ResponseBody
    public PageResult staffList(Model model,
                                @RequestParam(required = false, defaultValue = "1") int page,
                                @RequestParam(required = false, defaultValue = "10") int size, HttpServletRequest request) {
        List<Employee> allEmployees = employeeService.findAllEmployees();
        Employee emp = (Employee) request.getSession().getAttribute("validated");
        List<Employee> staffList = new ArrayList<>();
        for (Employee e : allEmployees) {
        	e.setLeaveEntitlements(null);
            if (e.getManagerId() != null && e.getManagerId() == emp.getEmployeeId()) {
                staffList.add(e);
            }
        }
        List<Employee> newList;
        if (staffList.size() > 0) {
            if (staffList.size() > page * size) {
                newList = staffList.subList((page - 1) * size, page * size);
            } else {
                newList = staffList.subList((page - 1) * size, staffList.size());
            }
            int totalPage;
            if ((staffList.size() % 10) > 0) {
                totalPage = staffList.size() / 10 + 1;
            } else {
                totalPage = staffList.size() / 10;
            }
            PageResult result = new PageResult();
            result.setPage(page);
            result.setTotal(staffList.size());
            result.setTotalPage(totalPage);
            result.setData(newList);
            return result;
        }
        return null;
    }

}
