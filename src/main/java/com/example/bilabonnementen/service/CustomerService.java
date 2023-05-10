package com.example.bilabonnementen.service;

import com.example.bilabonnementen.model.Car;
import com.example.bilabonnementen.model.Customer;
import com.example.bilabonnementen.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    CustomerRepo customerRepo;

    public void createCustomer( Customer customer){
        customerRepo.createCustomer(customer);
    }

    public void updateCustomer (Customer customer){
        customerRepo.updateCustomer(customer);

    }

    //TODO lav controller klassen
}
