package com.example.bilabonnementen.controller;

import com.example.bilabonnementen.model.Employee;
import com.example.bilabonnementen.repository.EmployeeRepository;
import com.example.bilabonnementen.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;
    EmployeeRepository employeeRepository;

    @GetMapping("/personale")
    public String getAllEmployees(Model model) {
        List<Employee> employees = employeeService.fetchAllEmployees();
        System.out.println(employees.size());
        model.addAttribute("employees", employees);
        return "personale";
    }

    @GetMapping("/test")
    public String testAllEmployees(Model model) {
        List<Employee> employees = employeeService.fetchAllEmployees();
        System.out.println(employees.size());
        model.addAttribute("employees", employees);
        return "test";
    }

    @GetMapping("/opretPersonale")
    public String opretPersonale() {
        return "opretPersonale";
    }
/*
    @PostMapping("/opretPersonale")
    public String opretPersonale(@ModelAttribute Employee employee, Model model, String confirm_password) {
        if (employeeRepository.doesTheUserExsit(employee.getUsername())) {
            model.addAttribute("forkert", "Brugernavn allerede i brug");
            return "opretPersonale";
        } else {
            if (!employee.getUser_password().equals(confirm_password)) {
                model.addAttribute("forkert", "Koden matcher ikke");
                return "opretPersonale";
            } else {
                employeeRepository.addEmployee(employee);
                model.addAttribute("succes", "Account created!!!!");
                return "personale";
            }


        }
    }

 */
    @PostMapping("/opretPersonaler")
    public String opretPersonaler(Employee employee, Model model) {
          employeeService.createEmployee(employee);
          return "login";
    }
}
