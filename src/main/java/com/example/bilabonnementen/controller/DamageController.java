package com.example.bilabonnementen.controller;

import com.example.bilabonnementen.model.Damage_category;
import com.example.bilabonnementen.service.DamageService;
import com.example.bilabonnementen.service.EmployeeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class DamageController {

    @Autowired
    DamageService damageService;

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/skader")
    public String skaddeRapportListe(Model model, HttpSession session){
        if (!employeeService.checkSession(session)){
            return "redirect:/";
        }
        List<Damage_category> damage_category =  damageService.fetchAllDamageCategories();
        model.addAttribute("category", damage_category);

        return "skader";
    }

}
