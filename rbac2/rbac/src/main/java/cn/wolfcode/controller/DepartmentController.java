package cn.wolfcode.controller;

import cn.wolfcode.domain.Department;
import cn.wolfcode.domain.DepartmentQuery;
import cn.wolfcode.service.IDepartmentService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/department")
public class DepartmentController{
@Autowired
   private IDepartmentService departmentService;

@RequestMapping("/list")
     public String addDepartment(Model model, DepartmentQuery query) {
        PageInfo<Department> pageInfo=departmentService.selectDepartmentByQuery(query);
           model.addAttribute("pageInfo",pageInfo);
           model.addAttribute("query", query);
        return "department/list";
    }
@RequestMapping("/saveOrUpdate")
  public String saveOrUpdate(Department department) {
      departmentService.saveOrUpdate(department);
        return "redirect:/department/list";
}
@RequestMapping("/delete")
    public String delete(Long id)
    { departmentService.deleteDepartment( id);
        return "redirect:/department/list";

    }

}
