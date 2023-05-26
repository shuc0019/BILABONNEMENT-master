package com.example.bilabonnementen.controller;

import com.example.bilabonnementen.model.Car;
import com.example.bilabonnementen.model.Employee;
import com.example.bilabonnementen.service.CarService;
import com.example.bilabonnementen.service.EmployeeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
@Controller
public class CarController {

    @Autowired
    CarService carService;
    @Autowired
    EmployeeService employeeService;


    //Se alle biler
    @GetMapping("/seallebiler")
    public String car(Model model, HttpSession session) {
        if (!employeeService.checkSession(session)){
            return "redirect:/";
        }
        List<Car> cars = carService.fetchAll();
        model.addAttribute("cars", cars);
        return "seallebiler";
    }

    //Se alle biler der er ledig

    @GetMapping("/ledigbiler")
    public String getAvailableCars(Model model, HttpSession session) {

        if (!employeeService.checkSession(session)){
            return "redirect:/";
        }
        List <Car> availableCars = carService.fetchAvailable();
        model.addAttribute("available", availableCars);
        return "ledigbiler";
    }
    @GetMapping("/tilføjBiler")
    public String addCar(HttpSession session) {
        if (!employeeService.checkSession(session)){
            return "redirect:/";
        }
        return "tilføjBiler";
    }
    @PostMapping("/createNew")
    public String addCartoList( Car car, HttpSession session) {
        carService.addCar(car);
        return "redirect:/seallebiler";
    }
    @GetMapping("/deleteOne/{vehicle_number}")
    public String deleteOne(@PathVariable("vehicle_number") int vehicle_number, HttpSession session){
        boolean deleted = carService.deleteCar(vehicle_number);
        if (deleted){
            return "redirect:/seallebiler";
        }else {
            return "redirect:/seallebiler";
        }
    }

    @GetMapping("/opdaterBilen/{vehicle_number}")
    public String updateCar(@PathVariable("vehicle_number") int vehicle_number, Model model, HttpSession session) {
        if (!employeeService.checkSession(session)){
            return "redirect:/";
        }
        Car car = carService.findId(vehicle_number);
        model.addAttribute("opdater", car);
        return "opdaterBil";
    }

    @PostMapping("/carupdate")
    public String updateCarToList(Car car, int vehicle_number) {
        carService.updateCar(car, vehicle_number);
        return "redirect:/seallebiler";
    }
    @GetMapping("/sammenlagtpris")
    public String getTotalPrice(Model model, HttpSession session) {
        if (!employeeService.checkSession(session)){
            return "redirect:/";
        }
        Employee adminLogin = (Employee) session.getAttribute("adminlogin");

        model.addAttribute("admin", adminLogin);

        double totalPrice = carService.calculateTotalPriceOfRentedCars();
        model.addAttribute("totalPrice", totalPrice);

        List<Car> rentedCars = carService.getRentedCars(); // Retrieve rented cars
        model.addAttribute("rentedCars", rentedCars);

        return "sammenlagtpris";
    }


}
