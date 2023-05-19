package com.example.bilabonnementen.controller;

import com.example.bilabonnementen.model.Damage_report;
import com.example.bilabonnementen.model.Leasing_contract;
import com.example.bilabonnementen.repository.DamageRepo;
import com.example.bilabonnementen.service.DamageService;
import com.example.bilabonnementen.service.Damage_reportService;
import com.example.bilabonnementen.service.Leasing_contractService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class Damage_reportController {
    @Autowired
    Damage_reportService damage_reportService;
    @Autowired
    DamageService damageService;
    @Autowired
    Leasing_contractService leasing_contractService;

    @GetMapping("/skaderapport")
    public String ShowDamage_Report(Model model){
        List<Damage_report> damage_reports = damage_reportService.showReport();
        model.addAttribute("damage_report", damage_reports);
        return "skaderapport";

    }

    @GetMapping("skaderapportopret")
    public String createdamageReport(Model model){
        List <Leasing_contract>leasing_contracts=leasing_contractService.fetchAll();
        model.addAttribute("contract", leasing_contracts);
        return "skaderapportopret";
    }


    @PostMapping("/report")
    public String addDamageReport(Model model, int category_id, RedirectAttributes redirectAttributes, Integer finish, HttpSession session) {



        Double totalPrice = (Double) session.getAttribute("totalPrice");
        if (totalPrice == null) {
            totalPrice = 0.0;
        }

        Double value = damageService.getSpecificDamagePrice(category_id);
        totalPrice += value;

        session.setAttribute("totalPrice", totalPrice);

        if (finish == null) {
            redirectAttributes.addFlashAttribute("totalPrice", totalPrice);
            return "redirect:/opretskaderapport";
        } else {
            // The finish condition is met, you can redirect or perform any other action here
            return "redirect:/opretskaderapport";
        }
    }


}