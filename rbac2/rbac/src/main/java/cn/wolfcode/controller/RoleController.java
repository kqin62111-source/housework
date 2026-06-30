package cn.wolfcode.controller;

import cn.wolfcode.domain.Role;
import cn.wolfcode.domain.RoleQuery;
import cn.wolfcode.service.IRoleService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @RequestMapping("/list")
    public String list(Model model, RoleQuery query) {
        PageInfo<Role> pageInfo = roleService.selectRoleByQuery(query);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("query", query);
        return "role/list";
    }

    @RequestMapping("/saveOrUpdate")
    public String saveOrUpdate(Role role) {
        roleService.saveOrUpdate(role);
        return "redirect:/role/list";
    }

    @RequestMapping("/delete")
    public String delete(Long id) {
        roleService.deleteRole(id);
        return "redirect:/role/list";
    }
}