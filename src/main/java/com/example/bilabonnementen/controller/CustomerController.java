package com.example.bilabonnementen.controller;

import com.example.bilabonnementen.model.Customer;
import com.example.bilabonnementen.service.CustomerService;
import jakarta.servlet.http.HttpSession;
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

        return "opretLejekontrakt";
    }
    @PostMapping("/opretenkunde")
    public String createCustomer (Customer c, Model model, HttpSession session){
        customerService.createCustomer(c);
        model.addAttribute("kunde", "Kunde tilføjet");
        session.setAttribute("kundeoprettet", c);
        System.out.println(c.getCustomer_id());
        return "redirect:/opretNyKundeConfirmed";
    }

    @GetMapping("/opretNyKunde")
    public String CreateNewCustomer(){
        return "opretNyKunde";
    }

    @GetMapping("/opretNyKundeConfirmed")
    public String newCustomerCreated(HttpSession session, Model model){
        Customer c = (Customer) session.getAttribute("kundeoprettet");
        String value = customerService.findCustomerid(c.getEmail());
        model.addAttribute("customer", c);
        model.addAttribute("customerid", value);
        return "opretNyKundeConfirmed";

    }

    //TODO lav færdig imorgen
}
