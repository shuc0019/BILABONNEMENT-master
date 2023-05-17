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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String opretPersonale(HttpSession session) {
        String adminLogin = (String) session.getAttribute("username");
        Employee adminEmployee = employeeService.findAdminUser(adminLogin);
        if (adminEmployee == null) {
            return "redirect:/personale";
        } else {

            return "opretPersonale";
        }

    }

    @PostMapping("/opretPersonaler")
    public String opretPersonaler(Employee employee, Model model, HttpSession session) {

            employeeService.createEmployee(employee);
            return "redirect:/personale";

    }

    @GetMapping("/personale/{username}")
    public String fireEmployee(@PathVariable("username") String username,  HttpSession session){
        String adminLogin = (String) session.getAttribute("username");
        Employee adminEmployee =employeeService.findAdminUser(adminLogin);
        if (adminEmployee==null) {
            return"redirect:/personale";
        } else {
            employeeService.fireEmployee(username);
            return "redirect:/personale";
        }

    }

    @GetMapping("/opdaterPersonale/{username}")
    public String findByUsername(@PathVariable("username") String username, Model model, HttpSession session) {
        Employee employee = employeeService.findByUsername(username);
        String adminLogin = (String) session.getAttribute("username");
        Employee adminEmployee =employeeService.findAdminUser(adminLogin);
        if (adminEmployee==null) {
            return"redirect:/personale";
        } else {
            model.addAttribute("employee", employee);
            session.setAttribute("urlusername", employee.getUsername());
            return "opdaterPersonale";
        }
    }


    //inkluderes i rapporten
    @PostMapping("/opdateretPersonale")
    public String opdateretPersonal(Employee employee, int is_active, int is_admin, HttpSession session, RedirectAttributes redirectAttributes) {
        String usernames = (String) session.getAttribute("urlusername");

        if(is_active != 0 && is_active != 1 && is_admin != 0 && is_admin != 1  ){
            redirectAttributes.addFlashAttribute("fejl", "Admin value should be 0 or 1");
            redirectAttributes.addFlashAttribute("fejl2", "Active value should be 0 or 1");
            return "redirect:/opdaterPersonale/" + usernames;
        }
        else if (is_active != 0 && is_active != 1) {
            redirectAttributes.addFlashAttribute("fejl", "Active value should be 0 or 1");
            return "redirect:/opdaterPersonale/" + usernames;
        }
        else if (is_admin != 0 && is_admin != 1) {
            redirectAttributes.addFlashAttribute("fejl2", "Admin value should be 0 or 1");
            return "redirect:/opdaterPersonale/" + usernames;
        }else {
            employeeService.updateEmployee(employee);
            return "redirect:/personale";
        }

    }
}
