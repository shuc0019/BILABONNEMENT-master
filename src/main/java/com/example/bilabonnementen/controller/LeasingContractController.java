package com.example.bilabonnementen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LeasingContractController {

    @GetMapping("/opretKontrakter")
    public String leasingKontrakt(){
        return "opretKontrakt";
    }

}
