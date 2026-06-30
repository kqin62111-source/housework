package cn.wolfcode.controller;

import cn.wolfcode.domain.Clazz;
import cn.wolfcode.domain.Course;
import cn.wolfcode.domain.Employee;
import cn.wolfcode.domain.Schedule;
import cn.wolfcode.domain.ScheduleQuery;
import cn.wolfcode.service.IClazzService;
import cn.wolfcode.service.ICourseService;
import cn.wolfcode.service.IEmployeeService;
import cn.wolfcode.service.IScheduleService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    private IScheduleService scheduleService;

    @Autowired
    private IClazzService clazzService;

    @Autowired
    private ICourseService courseService;

    @Autowired
    private IEmployeeService employeeService;

    @RequestMapping("/list")
    public String list(Model model, ScheduleQuery query) {
        PageInfo<Schedule> pageInfo = scheduleService.selectScheduleByQuery(query);
        List<Clazz> clazzes = clazzService.selectAllClazz(new cn.wolfcode.domain.QueryObjet()).getList();
        List<Course> courses = courseService.selectAllCourse(new cn.wolfcode.domain.QueryObjet()).getList();
        List<Employee> employees = employeeService.selectAllEmployee(new cn.wolfcode.domain.QueryObjet()).getList();
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("clazzes", clazzes);
        model.addAttribute("courses", courses);
        model.addAttribute("employees", employees);
        model.addAttribute("query", query);
        return "schedule/list";
    }

    @RequestMapping("/saveOrUpdate")
    public String saveOrUpdate(Schedule schedule) {
        scheduleService.saveOrUpdate(schedule);
        return "redirect:/schedule/list";
    }

    @RequestMapping("/delete")
    public String delete(Long id) {
        scheduleService.deleteSchedule(id);
        return "redirect:/schedule/list";
    }
}