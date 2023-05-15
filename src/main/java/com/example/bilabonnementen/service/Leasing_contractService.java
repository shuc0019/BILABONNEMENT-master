package com.example.bilabonnementen.service;


import com.example.bilabonnementen.model.Leasing_contract;
import com.example.bilabonnementen.repository.Leasing_contractRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Leasing_contractService {
    @Autowired
    Leasing_contractRepo leasing_contractRepo;

    public void addLeasingContract(Leasing_contract leasing_contract){
        leasing_contractRepo.createLeasingContract(leasing_contract);
    }
}