package com.example.bilabonnementen.controller;

import com.example.bilabonnementen.model.Customer;
import com.example.bilabonnementen.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CustomerController {
    @Autowired
    CustomerService customerService;


    @GetMapping("/opretlegekontrakt")
    public String lejekontrakt (Customer c){

        return "opretlejekontrakt";
    }
    @PostMapping("/opretenkunde")
    public String createCustomer (Customer c, Model model){
        customerService.createCustomer(c);
        model.addAttribute("kunde", "Kunde tilføjet");
        return "opretlejekontrakt";
    }

    //TODO lav færdig imorgen
}
