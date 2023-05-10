package com.example.bilabonnementen.controller;

import com.example.bilabonnementen.model.Car;
import com.example.bilabonnementen.model.Damage_category;
import com.example.bilabonnementen.service.DamageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class DamageController {

    @Autowired
    DamageService damageService;


    @GetMapping("/skaderapport")
    public String skaddeRapportListe(Model model){
        List<Damage_category> damage_category =  damageService.fetchAllDamageCategories();
        model.addAttribute("category", damage_category);

        return "skaderapport";
    }

}
