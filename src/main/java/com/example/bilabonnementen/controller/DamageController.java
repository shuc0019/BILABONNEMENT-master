package com.example.bilabonnementen.controller;

import com.example.bilabonnementen.service.DamageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DamageController {

    @Autowired
    DamageService damageService;


    @GetMapping("/skaderapport")
    public String skaddeRapportListe(){
        return "skaderapport";
    }

}
