package cn.wolfcode.controller;

import cn.wolfcode.domain.QueryObjet;
import cn.wolfcode.domain.Student;
import cn.wolfcode.domain.StudentStatus;
import cn.wolfcode.domain.StudentStatusQuery;
import cn.wolfcode.service.IStudentService;
import cn.wolfcode.service.IStudentStatusService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/studentStatus")
public class StudentStatusController {

    @Autowired
    private IStudentStatusService studentStatusService;

    @Autowired
    private IStudentService studentService;

    @RequestMapping("/list")
    public String list(Model model, StudentStatusQuery query) {
        PageInfo<StudentStatus> pageInfo = studentStatusService.selectStudentStatusByQuery(query);
        List<Student> students = studentService.selectAllStudent(new QueryObjet()).getList();
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("students", students);
        model.addAttribute("query", query);
        return "studentStatus/list";
    }

    @RequestMapping("/saveOrUpdate")
    public String saveOrUpdate(StudentStatus status) {
        studentStatusService.saveOrUpdate(status);
        return "redirect:/studentStatus/list";
    }

    @RequestMapping("/delete")
    public String delete(Long id) {
        studentStatusService.deleteStudentStatus(id);
        return "redirect:/studentStatus/list";
    }
}