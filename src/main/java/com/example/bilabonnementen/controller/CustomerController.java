package com.example.bilabonnementen.controller;

import com.example.bilabonnementen.model.Car;
import com.example.bilabonnementen.model.Customer;
import com.example.bilabonnementen.service.CustomerService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CustomerController {
    @Autowired
    CustomerService customerService;


    @GetMapping("/opretlejekontrakt")
    public String lejekontrakt ( Model model, HttpSession session){
        List<Customer> customers = customerService.fetchAll();
        session.setAttribute("customersInLej", customers);
        model.addAttribute("customers", customers);
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
        model.addAttribute("customerid",value);
        return "opretNyKundeConfirmed";

    }


    @GetMapping("/opdaterkunde/{customer_id}")
    public String updateCustomer(@PathVariable("customer_id") int customer_id, Model model){
        Customer customer=customerService.findId(customer_id);
        model.addAttribute("opdater", customer);
        return "opdaterKunde";
    }

    @PostMapping("/opdaterkunden")
    public String updateTheCustomer(Customer c,int customer_id){
        customerService.updateCustomer(c, customer_id);
        return "redirect:/opretlejekontrakt";
    }


    //TODO lav færdig imorgen
}
