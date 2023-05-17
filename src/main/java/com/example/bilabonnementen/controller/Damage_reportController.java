package com.example.bilabonnementen.controller;

import com.example.bilabonnementen.model.Damage_report;
import com.example.bilabonnementen.service.Damage_reportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class Damage_reportController {
    @Autowired
    Damage_reportService damage_reportService;

    @GetMapping("/skaderapport")
    public String ShowDamage_Report(Model model){
        List<Damage_report> damage_reports = damage_reportService.showReport();
        model.addAttribute("damage_report", damage_reports);
        return "skaderapport";

    }
    @GetMapping("opretskaderapport")
    public String CreateDamage_report(){
        return "opretskaderapport";
    }
}
