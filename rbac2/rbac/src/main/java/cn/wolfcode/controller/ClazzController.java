package cn.wolfcode.controller;

import cn.wolfcode.domain.Clazz;
import cn.wolfcode.domain.ClazzQuery;
import cn.wolfcode.domain.Grade;
import cn.wolfcode.domain.QueryObjet;
import cn.wolfcode.service.IClazzService;
import cn.wolfcode.service.IGradeService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/class")
public class ClazzController {

    @Autowired
    private IClazzService clazzService;

    @Autowired
    private IGradeService gradeService;

    @RequestMapping("/list")
    public String list(Model model, ClazzQuery query) {
        PageInfo<Clazz> pageInfo = clazzService.selectClazzByQuery(query);
        List<Grade> grades = gradeService.selectAllGrade(new QueryObjet()).getList();
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("grades", grades);
        model.addAttribute("query", query);
        return "class/list";
    }

    @RequestMapping("/saveOrUpdate")
    public String saveOrUpdate(Clazz clazz) {
        clazzService.saveOrUpdate(clazz);
        return "redirect:/class/list";
    }

    @RequestMapping("/delete")
    public String delete(Long id) {
        clazzService.deleteClazz(id);
        return "redirect:/class/list";
    }
}