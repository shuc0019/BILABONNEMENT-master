package com.example.bilabonnementen.service;


import com.example.bilabonnementen.model.Car;
import com.example.bilabonnementen.model.Leasing_contract;
import com.example.bilabonnementen.repository.Leasing_contractRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Leasing_contractService {
    @Autowired
    Leasing_contractRepo leasing_contractRepo;


    public List <Leasing_contract> fetchAll() {
        return leasing_contractRepo.fetchAll();
    }


    public void addLeasingContract(Leasing_contract leasing_contract){
        leasing_contractRepo.createLeasingContract(leasing_contract);
    }
    public double calculateTotalPriceOfLeasingContracts() {
        List<Leasing_contract> leasingContracts = leasing_contractRepo.fetchAll();
        double totalPrice = 0.0;

        for (Leasing_contract contract : leasingContracts) {
            totalPrice += contract.getPrice();
        }

        return totalPrice;
    }
}