package cn.wolfcode.controller;

import cn.wolfcode.domain.Course;
import cn.wolfcode.domain.Score;
import cn.wolfcode.domain.ScoreQuery;
import cn.wolfcode.domain.Student;
import cn.wolfcode.service.ICourseService;
import cn.wolfcode.service.IScoreService;
import cn.wolfcode.service.IStudentService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/score")
public class ScoreController {

    @Autowired
    private IScoreService scoreService;

    @Autowired
    private IStudentService studentService;

    @Autowired
    private ICourseService courseService;

    @RequestMapping("/list")
    public String list(Model model, ScoreQuery query) {
        PageInfo<Score> pageInfo = scoreService.selectScoreByQuery(query);
        List<Student> students = studentService.selectAllStudent(new cn.wolfcode.domain.QueryObjet()).getList();
        List<Course> courses = courseService.selectAllCourse(new cn.wolfcode.domain.QueryObjet()).getList();
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("students", students);
        model.addAttribute("courses", courses);
        model.addAttribute("query", query);
        return "score/list";
    }

    @RequestMapping("/saveOrUpdate")
    public String saveOrUpdate(Score score) {
        scoreService.saveOrUpdate(score);
        return "redirect:/score/list";
    }

    @RequestMapping("/delete")
    public String delete(Long id) {
        scoreService.deleteScore(id);
        return "redirect:/score/list";
    }
}