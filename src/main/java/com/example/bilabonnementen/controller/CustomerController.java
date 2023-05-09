package com.example.bilabonnementen.controller;

import com.example.bilabonnementen.model.Customer;
import com.example.bilabonnementen.repository.CustomerRepo;
import com.example.bilabonnementen.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomerController {
    @Autowired
    CustomerService customerService;


    @GetMapping("/opretlegekontrakt")
    public String createCustomer (Customer c){

        return "index";
    }

    //TODO lav færdig imorgen
}
