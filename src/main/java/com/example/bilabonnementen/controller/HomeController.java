package com.example.bilabonnementen.controller;

import com.example.bilabonnementen.model.Car;
import com.example.bilabonnementen.model.Employee;
import com.example.bilabonnementen.repository.CarRepo;
import com.example.bilabonnementen.repository.EmployeeRepository;
import com.example.bilabonnementen.service.CarService;
import com.example.bilabonnementen.service.Service;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    CarService carService;
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
        if (employee != null){
            session.setAttribute("username", username);
            return "redirect:/home";
        } else {
            model.addAttribute("invalid", "Din login er forkert");
            return "login";

        }
    }

    @GetMapping("/car")
    public String car(Model model) {
        List<Car> cars = carService.fetchAll();
        model.addAttribute("car", cars);
        return "car";
    }

  /*  @GetMapping("/car")
    public String car() {
        return "car";
    }


   */

    @GetMapping("/report")
    public String damageReport() {
        return "report";
    }
    @GetMapping ("/contract")
    public String leasingContract(){
        return "contract";
    }

    @GetMapping ("/employee")
    public String employee(){
        return "employee";
    }









}
