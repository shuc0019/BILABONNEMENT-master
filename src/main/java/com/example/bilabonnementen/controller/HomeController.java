package com.example.bilabonnementen.controller;

import com.example.bilabonnementen.model.Employee;
import com.example.bilabonnementen.repository.EmployeeRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {


    @Autowired
    EmployeeRepository employeeRepository;

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

    //Homepage
    @GetMapping("/home")
    public String home() {
        return "home";
    }
    //validate login
    @PostMapping("/login")
    public String loginAccount(String username, String user_password, Model model, HttpSession session) {
        Employee employee = employeeRepository.findByUserAndPassword(username, user_password);

        if (employee != null && employee.getIs_active()==1){
            session.setAttribute("username", username);
            return "redirect:/home";
        } else {
            model.addAttribute("invalid", "bruger findes ikke");
            return "login";

        }
    }

    //Dashboards button gateways

    //Dataregistrering



    //skade og udbedring

    @GetMapping("/opretskaderapport")
    public String createDamagereport() {
        return "opretskaderapport";
    }



    // rapportering og overvågnings
    @GetMapping("/seallebil")
    public String allCars() {
        return "seallebiler";
    }

    @GetMapping("/ledigbil")
    public String availaibleCars() {
        return "ledigbiler";
    }

    @GetMapping("/opretlejekontrakt")
    public String lejekontrakt() {
        return "opretLejekontrakt";
    }



}