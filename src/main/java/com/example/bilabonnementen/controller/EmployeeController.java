package com.example.bilabonnementen.controller;

import com.example.bilabonnementen.model.Employee;
import com.example.bilabonnementen.repository.EmployeeRepository;
import com.example.bilabonnementen.service.EmployeeService;
import jakarta.servlet.http.HttpSession;
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
    public String fireEmployee(@PathVariable("username") String username){
        employeeService.fireEmployee(username);
        return "redirect:/personale";
    }

    @GetMapping("/opdaterPersonale/{username}")
    public String findByUsername(@PathVariable("username") String username, Model model, HttpSession session){
       Employee employee = employeeService.findByUsername(username);
        model.addAttribute("employee", employee);
        session.setAttribute("username", username);
        return "opdaterPersonale";
    }

    @PostMapping("/opdateretPersonale")
    public String opdateretPersonal(@PathVariable("username") String username, Employee employee, int is_active, int is_admin, Model model, HttpSession session ){
       username = (String)  session.getAttribute(username);
        if (is_active!=1 || is_active!=0){
            model.addAttribute("fejl", "denne er en fejl");
            return "redirect:/opdaterPersonale/" +username;
        } else {
            employeeService.updateEmployee(employee);
            return "redirect:/personale";
        }

    }


}
