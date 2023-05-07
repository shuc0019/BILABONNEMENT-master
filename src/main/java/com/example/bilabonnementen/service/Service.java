package com.example.bilabonnementen.service;

import com.example.bilabonnementen.model.Car;
import com.example.bilabonnementen.repository.CarRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
@org.springframework.stereotype.Service

public class Service {

    @Autowired
    private CarRepo carRepo;

    public List<Car> fetchAll() {
        return carRepo.fetchAll();
    }
}







