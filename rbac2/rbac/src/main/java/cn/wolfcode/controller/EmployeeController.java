package cn.wolfcode.controller;

import cn.wolfcode.domain.Department;
import cn.wolfcode.domain.Employee;
import cn.wolfcode.domain.EmployeeQuery;
import cn.wolfcode.service.IDepartmentService;
import cn.wolfcode.service.IEmployeeService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeService;

    @Autowired
    private IDepartmentService departmentService;

    @RequestMapping("/list")
    public String list(Model model, EmployeeQuery query) {
        PageInfo<Employee> pageInfo = employeeService.selectEmployeeByQuery(query);
        List<Department> departments = departmentService.selectAllDepartment(new cn.wolfcode.domain.QueryObjet()).getList();
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("departments", departments);
        model.addAttribute("query", query);
        return "employee/list";
    }

    @RequestMapping("/saveOrUpdate")
    public String saveOrUpdate(Employee employee) {
        employeeService.saveOrUpdate(employee);
        return "redirect:/employee/list";
    }

    @RequestMapping("/delete")
    public String delete(Long id) {
        employeeService.deleteEmployee(id);
        return "redirect:/employee/list";
    }
}