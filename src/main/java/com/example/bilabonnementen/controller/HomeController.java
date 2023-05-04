package com.example.bilabonnementen.controller;

import ch.qos.logback.core.model.Model;
import com.example.bilabonnementen.model.Employee;
import com.example.bilabonnementen.repository.EmployeeRepository;
import com.example.bilabonnementen.service.EmployeeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.management.relation.Role;

@Controller
public class HomeController {

    EmployeeRepository employeeRepository;

    @GetMapping
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {

        return "login";
    }

    //validate login
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String user_password,
                        HttpSession session, RedirectAttributes redirectAttributes) {
        Employee employee = employeeRepository.findByUserAndPassword(username, user_password);

        if (employee == null) {
            redirectAttributes.addFlashAttribute("error", "Invalid username or password");
            return "redirect:/login";
        }
        session.setAttribute("employee", employee);
        if (employee.isIs_admin(true)) {
            session.setAttribute("isAdmin", true);
        }

        return "redirect:/home";
    }




}
