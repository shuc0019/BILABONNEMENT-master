package com.example.bilabonnementen.controller;

import com.example.bilabonnementen.model.Employee;
import com.example.bilabonnementen.repository.EmployeeRepository;
import com.example.bilabonnementen.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;
    @GetMapping("/personale")
    public String getAllEmployees(Model model){
        List <Employee> employees = employeeService.fetchAllEmployees();
        System.out.println(employees.size());
        model.addAttribute("employees", employees);
        return "personale";
    }
    @GetMapping("/test")
    public String testAllEmployees(Model model){
        List <Employee> employees = employeeService.fetchAllEmployees();
        System.out.println(employees.size());
        model.addAttribute("employees", employees);
        return "test";
    }

}
