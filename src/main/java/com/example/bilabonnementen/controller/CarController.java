package com.example.bilabonnementen.controller;

import com.example.bilabonnementen.model.Car;
import com.example.bilabonnementen.repository.CarRepo;
import com.example.bilabonnementen.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
@Controller
public class CarController {

        @Autowired
        CarService carService;


//Se alle biler
    @GetMapping("/seallebiler")
    public String car(Model model) {
        List<Car> cars = carService.fetchAll();
        model.addAttribute("cars", cars);
        return "seallebiler";
    }

    //Se alle biler der er ledig

    @GetMapping("/ledigbiler")
    public String getAvailableCars(Model model) {
        List <Car> availableCars = carService.fetchAvailable();
        model.addAttribute("available", availableCars);
        return "ledigbiler";
    }
    @GetMapping("/tilføjBiler")
    public String addCar() {
        return "tilføjBiler";
    }
    @PostMapping("/createNew")
    public String addCartoList( Car car) {
        carService.addCar(car);
        return "redirect:/seallebiler";
    }
    @GetMapping("/deleteOne/{vehicle_number}")
    public String deleteOne(@PathVariable("vehicle_number") int vehicle_number){
        boolean deleted = carService.deleteCar(vehicle_number);
        if (deleted){
            return "redirect:/seallebiler";
        }else {
            return "redirect:/seallebiler";
        }
    }

    @GetMapping("/opdaterBil")
    public String updateCar() {
        return "opdaterBil";
    }







    }
