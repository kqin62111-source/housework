package cn.wolfcode.controller;

import cn.wolfcode.domain.Grade;
import cn.wolfcode.domain.GradeQuery;
import cn.wolfcode.service.IGradeService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/grade")
public class GradeController {

    @Autowired
    private IGradeService gradeService;

    @RequestMapping("/list")
    public String list(Model model, GradeQuery query) {
        PageInfo<Grade> pageInfo = gradeService.selectGradeByQuery(query);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("query", query);
        return "grade/list";
    }

    @RequestMapping("/saveOrUpdate")
    public String saveOrUpdate(Grade grade) {
        gradeService.saveOrUpdate(grade);
        return "redirect:/grade/list";
    }

    @RequestMapping("/delete")
    public String delete(Long id) {
        gradeService.deleteGrade(id);
        return "redirect:/grade/list";
    }
}