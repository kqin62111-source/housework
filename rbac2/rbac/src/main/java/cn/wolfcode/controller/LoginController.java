package cn.wolfcode.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @RequestMapping("/")
    public String index() {
        return "redirect:/static/login.html";
    }

    @RequestMapping("/login")
    public String login(String username, String password, HttpSession session) {
        if ("admin".equals(username) && "1".equals(password)) {
            session.setAttribute("user", username);
            return "redirect:/index";
        }
        return "redirect:/static/login.html";
    }

    @RequestMapping("/index")
    public String home() {
        return "index";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/static/login.html";
    }
}