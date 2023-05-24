package com.example.bilabonnementen.controller;

import com.example.bilabonnementen.model.*;
import com.example.bilabonnementen.service.CarService;
import com.example.bilabonnementen.service.CustomerService;
import com.example.bilabonnementen.service.EmployeeService;
import com.example.bilabonnementen.service.Leasing_contractService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Controller
public class LeasingContractController {

    int number;
    @Autowired
    Leasing_contractService leasing_contractService;
    @Autowired
    CarService carService;
    @Autowired
    CustomerService customerService;

    @GetMapping("/opretKontrakt")
    public String leasingKontrakt(Model model) {
        List<Car> availableCars = carService.fetchAvailable();
        model.addAttribute("available", availableCars);

        return "opretKontrakt";
    }

    @PostMapping("/chooseCar")
    public String seBiler(Model model, int vehicle_number, HttpSession session, RedirectAttributes redirectAttributes) {
        Car car = carService.findId(vehicle_number);
        if (car == null) {
            redirectAttributes.addFlashAttribute("error", "Bilen med det angivne vognnummer kunne ikke findes");
            return "redirect:/opretKontrakt";
        } else {
            model.addAttribute("opdater", car);
            model.addAttribute("model", "");
            session.setAttribute("numb", car.getVehicle_number());
            if (car.getFlow() == 1) {
                redirectAttributes.addFlashAttribute("flowerror", "Bilen er allerede lejet ud");
                return "redirect:/opretKontrakt";
            } else {
                return "bilValgt";
            }
        }
    }
    @GetMapping("/selejekontrakt")
    public String Leasing_contracts(Model model){
        List<Leasing_contract> LC = leasing_contractService.fetchAll();
        model.addAttribute("LC",LC );
        System.out.println(LC.size());
        double totalPrice = leasing_contractService.calculateTotalPriceOfLeasingContracts();
        model.addAttribute("totalPriceRent", totalPrice);
        return "seLejekontrakt";
    }



    @GetMapping("/lej")
    public String leasing(Model model, HttpSession session) {
        String username = (String) session.getAttribute("username");
        Integer numb = (Integer) session.getAttribute("numb");
        List<Customer> customers = customerService.fetchAll();
        if (numb != null) {
            Car car = carService.findId(numb);
            model.addAttribute("opdater", car);
            model.addAttribute("username", username);
            model.addAttribute("customers", customers);
            return "lej";
        } else {
            // handle case where numb is null
            return "error";
        }
    }

    @PostMapping("/createLeasingContract")
    public String createLease(LocalDate start_date, LocalDate end_date, Model model, HttpSession session, int customer_id, String username) {
        int numb = (int) session.getAttribute("numb");
        Car car = carService.findId(numb);
        model.addAttribute("opdater", car);
        model.addAttribute("username", username);
        Period period = Period.between(start_date, end_date);
        int months = period.getMonths();
        int days = period.getDays();
        // check if lease period is at least 3 months
        if (months < 3 && (months == 2 && days < 5 || months < 2)) {
            model.addAttribute("error", "Lej perioden skal være mindst 3 måneder.");
            return "lej";

        } else {
            // Calculate leasing price
            double monthlyPrice = car.getPrice();
            double totalPrice = monthlyPrice * months;
            if (days > 0) {
                double dailyPrice = (double) monthlyPrice / 30;
                double extraDaysPrice = dailyPrice * days;
                totalPrice += Math.round(extraDaysPrice);
            }
            System.out.println(totalPrice);
            model.addAttribute("opdater", car);
            model.addAttribute("username", username);
            model.addAttribute("totalPrice", totalPrice);
            model.addAttribute("startDate", start_date);
            model.addAttribute("endDate", end_date);
            model.addAttribute("price", monthlyPrice);
            model.addAttribute("customerId", customer_id);
            model.addAttribute("totalPriceRent", totalPrice);
            session.setAttribute("totalPriceRent", totalPrice);
            session.setAttribute("startDate", start_date);
            session.setAttribute("endDate", end_date);
            session.setAttribute("customer", customer_id);
            session.setAttribute("username", username);
            Customer customer = customerService.findId(customer_id);
            session.setAttribute("customer_id", customer);
            session.setAttribute("customername", customer.getFull_name());

            return "redirect:/leaseconfirm";
        }
    }

    @GetMapping("/leaseconfirm")
    public String leasingConfirmation(Model model, HttpSession session) {
        String username = (String) session.getAttribute("username");
        String customername = (String) session.getAttribute("customername");
        LocalDate startDate = (LocalDate) session.getAttribute("startDate");
        LocalDate endDate = (LocalDate) session.getAttribute("endDate");
        double totalprice = (double) session.getAttribute("totalPriceRent");
        int customer = (int) session.getAttribute("customer");

        Integer numb = (Integer) session.getAttribute("numb");
        Car car = carService.findId(numb);
        model.addAttribute("opdater", car);
        model.addAttribute("username", username);
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);
        model.addAttribute("customer", customer);
        model.addAttribute("totalPriceRent", totalprice);
        model.addAttribute("customername", customername);
        return "leaseconfirm";
    }

    @PostMapping("/createLeasingContractConfirmed")
    public String leasingAdd(Model model, HttpSession session, Leasing_contract leasing_contract) {
        String username = (String) session.getAttribute("username");
        LocalDate startDate = (LocalDate) session.getAttribute("startDate");
        String customername = (String) session.getAttribute("customername");
        LocalDate endDate = (LocalDate) session.getAttribute("endDate");
        double totalprice = (double) session.getAttribute("totalPriceRent");
        int customer = (int) session.getAttribute("customer");
        Integer numb = (Integer) session.getAttribute("numb");
        Car car = carService.findId(numb);
        model.addAttribute("opdater", car);
        model.addAttribute("username", username);
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);
        model.addAttribute("customer", customer);
        model.addAttribute("totalPriceRent", totalprice);
        model.addAttribute("customername", customername);
        leasing_contractService.addLeasingContract(leasing_contract);
        carService.updateAfterContract(numb);
        return "redirect:/home";
    }


}
