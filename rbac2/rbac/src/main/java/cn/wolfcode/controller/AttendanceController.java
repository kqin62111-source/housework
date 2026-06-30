package cn.wolfcode.controller;

import cn.wolfcode.domain.Attendance;
import cn.wolfcode.domain.AttendanceQuery;
import cn.wolfcode.domain.Student;
import cn.wolfcode.service.IAttendanceService;
import cn.wolfcode.service.IStudentService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/attendance")
public class AttendanceController {

    @Autowired
    private IAttendanceService attendanceService;

    @Autowired
    private IStudentService studentService;

    @RequestMapping("/list")
    public String list(Model model, AttendanceQuery query) {
        PageInfo<Attendance> pageInfo = attendanceService.selectAttendanceByQuery(query);
        List<Student> students = studentService.selectAllStudent(new cn.wolfcode.domain.QueryObjet()).getList();
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("students", students);
        model.addAttribute("query", query);
        return "attendance/list";
    }

    @RequestMapping("/saveOrUpdate")
    public String saveOrUpdate(Attendance attendance) {
        attendanceService.saveOrUpdate(attendance);
        return "redirect:/attendance/list";
    }

    @RequestMapping("/delete")
    public String delete(Long id) {
        attendanceService.deleteAttendance(id);
        return "redirect:/attendance/list";
    }
}