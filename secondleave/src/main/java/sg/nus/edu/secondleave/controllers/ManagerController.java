package sg.nus.edu.secondleave.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sg.nus.edu.secondleave.model.Employee;
import sg.nus.edu.secondleave.model.LeaveApplication;
import sg.nus.edu.secondleave.model.PageResult;
import sg.nus.edu.secondleave.services.EmployeeService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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
                                @RequestParam(required = false, defaultValue = "10") int size) {
        List<Employee> allEmployees = employeeService.findAllEmployees();
        List<Employee> staffList = new ArrayList<>();
        for (Employee e : allEmployees) {
            if (e.getManagerId() != null) {
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
