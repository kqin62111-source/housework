package cn.wolfcode.controller;

import cn.wolfcode.domain.Course;
import cn.wolfcode.domain.CourseQuery;
import cn.wolfcode.service.ICourseService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private ICourseService courseService;

    @RequestMapping("/list")
    public String list(Model model, CourseQuery query) {
        PageInfo<Course> pageInfo = courseService.selectCourseByQuery(query);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("query", query);
        return "course/list";
    }

    @RequestMapping("/saveOrUpdate")
    public String saveOrUpdate(Course course) {
        courseService.saveOrUpdate(course);
        return "redirect:/course/list";
    }

    @RequestMapping("/delete")
    public String delete(Long id) {
        courseService.deleteCourse(id);
        return "redirect:/course/list";
    }
}