package com.example.bilabonnementen.controller;

import com.example.bilabonnementen.model.Employee;
import com.example.bilabonnementen.repository.EmployeeRepository;
import com.example.bilabonnementen.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
        model.addAttribute("employees", employees);
        return "personale";
    }

    @GetMapping("/opretPersonale")
    public String opretPersonale() {
        return "opretPersonale";
    }

    @PostMapping("/opretPersonaler")
    public String opretPersonaler(Employee employee, Model model) {
        employeeService.createEmployee(employee);
        return "login";
    }

    @GetMapping("/personale/{username}")
    public String fireEmployee(@PathVariable ("username") String username){
        employeeService.fireEmployee(username);
       return "redirect:/personale";
    }


}
