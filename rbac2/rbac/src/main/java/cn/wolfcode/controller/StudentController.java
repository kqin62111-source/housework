package cn.wolfcode.controller;

import cn.wolfcode.domain.Student;
import cn.wolfcode.domain.StudentQuery;
import cn.wolfcode.service.IStudentService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private IStudentService studentService;

    @RequestMapping("/list")
    public String list(Model model, StudentQuery query) {
        PageInfo<Student> pageInfo = studentService.selectStudentByQuery(query);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("query", query);
        return "student/list";
    }

    @RequestMapping("/saveOrUpdate")
    public String saveOrUpdate(Student student) {
        studentService.saveOrUpdate(student);
        return "redirect:/student/list";
    }

    @RequestMapping("/delete")
    public String delete(Long id) {
        studentService.deleteStudent(id);
        return "redirect:/student/list";
    }
}