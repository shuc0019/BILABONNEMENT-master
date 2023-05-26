package com.example.bilabonnementen.controller;

import com.example.bilabonnementen.model.Employee;
import com.example.bilabonnementen.service.EmployeeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {


    @Autowired
    EmployeeService employeeService;
    //Start page
    @GetMapping("/")
    public String index() {
        return "index";
    }
    //login
    @GetMapping("/login")
    public String login() {

        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("adminlogin");
        return "redirect:/";
    }

    //Homepage
    @GetMapping("/home")
    public String home(HttpSession session, Model model) {
        String value = (String) session.getAttribute("username");
        model.addAttribute("username", value);
        if (!employeeService.checkSession(session)){
            return "redirect:/";
        }
        return "home";
    }
    //validate login
    @PostMapping("/login")
    public String loginAccount(String username, String user_password, Model model, HttpSession session) {
        Employee employee = employeeService.findbyuserandpassword(username,user_password);
        session.setAttribute("adminlogin", employee);
        System.out.println(employeeService.checkSession(session));

        if (employee != null && employee.getIs_active()==1){
            session.setAttribute("username", username);
            return "redirect:/home";
        } else {
            model.addAttribute("invalid", "bruger findes ikke");
            return "login";

        }
    }
}