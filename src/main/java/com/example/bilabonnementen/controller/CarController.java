package com.example.bilabonnementen.controller;

import com.example.bilabonnementen.model.Car;
import com.example.bilabonnementen.repository.CarRepo;
import com.example.bilabonnementen.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
@Controller
public class CarController {

        @Autowired
        CarService carService;



    @GetMapping("/seallebiler")
    public String car(Model model) {
        List<Car> cars = carService.fetchAll();
        model.addAttribute("cars", cars);
        return "seallebiler";
    }

        @GetMapping("car")
        public String getAvailableCars(Model model) {
            List<Car> availableCars = carService.fetchAvailable();
            model.addAttribute("car", availableCars);
            return "car";
        }








    }
