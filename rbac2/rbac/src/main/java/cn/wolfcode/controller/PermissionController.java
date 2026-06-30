package cn.wolfcode.controller;

import cn.wolfcode.domain.Permission;
import cn.wolfcode.domain.PermissionQuery;
import cn.wolfcode.service.IPermissionService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private IPermissionService permissionService;

    @RequestMapping("/list")
    public String list(Model model, PermissionQuery query) {
        PageInfo<Permission> pageInfo = permissionService.selectPermissionByQuery(query);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("query", query);
        return "permission/list";
    }

    @RequestMapping("/saveOrUpdate")
    public String saveOrUpdate(Permission permission) {
        permissionService.saveOrUpdate(permission);
        return "redirect:/permission/list";
    }

    @RequestMapping("/delete")
    public String delete(Long id) {
        permissionService.deletePermission(id);
        return "redirect:/permission/list";
    }
}