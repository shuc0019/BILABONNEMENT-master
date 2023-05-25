package com.example.bilabonnementen.controller;

import com.example.bilabonnementen.model.Damage_category;
import com.example.bilabonnementen.model.Damage_report;
import com.example.bilabonnementen.model.Leasing_contract;
import com.example.bilabonnementen.repository.DamageRepo;
import com.example.bilabonnementen.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class Damage_reportController {
    @Autowired
    Damage_reportService damage_reportService;
    @Autowired
    DamageService damageService;
    @Autowired
    Leasing_contractService leasing_contractService;
    @Autowired
    CarService carService;

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/skaderapport")
    public String ShowDamage_Report(Model model, HttpSession session){
        if (!employeeService.checkSession(session)){
            return "redirect:/";
        }
        List<Damage_report> damage_reports = damage_reportService.showReport();
        model.addAttribute("damage_report", damage_reports);
        return "skaderapport";

    }

    @GetMapping("skaderapportopret")
    public String createdamageReport(Model model, HttpSession session,  Integer contract_id) {
        if (!employeeService.checkSession(session)){
            return "redirect:/";
        }
        List<Leasing_contract> leasing_contracts = leasing_contractService.fetchAll();
        model.addAttribute("contract", leasing_contracts);


            return "skaderapportopret";

    }


    @PostMapping("/oprettelseskaderapport")
    public String opretskaderapport(Model model, HttpSession session, Integer contract_id){
        Leasing_contract leasing_contract = leasing_contractService.findId(contract_id);
        session.setAttribute("contract", leasing_contract.getContract_id());
        session.setAttribute("leasingcontract", leasing_contract);
        return "redirect:/opretskaderapport";
    }
    @GetMapping("opretskaderapport")
    public String visSkadeRapport(HttpSession session, Model model) {
        if (!employeeService.checkSession(session)){
            return "redirect:/";
        }
        Integer integer = (Integer) session.getAttribute("contract");
        List<Damage_category> damage_category =  damageService.fetchAllDamageCategories();
        model.addAttribute("category", damage_category);
        model.addAttribute("contractid", integer);
        return "opretskaderapport";
    }


    @PostMapping("/tilføjRapport")
    public String addDamageReport(Model model, Integer category_id, RedirectAttributes redirectAttributes, Integer finish, HttpSession session, Damage_report damage_report) {

        Double totalPrice = (Double) session.getAttribute("totalPrice");
        if (totalPrice == null) {
            totalPrice = 0.0;
        }
        if (category_id==null && finish==1){
            return "redirect:/kvitteringSkadeRapport";
        }

        Double value = damageService.getSpecificDamagePrice(category_id);
        totalPrice += value;

        session.setAttribute("totalPrice", totalPrice);


        if (finish == null) {
            redirectAttributes.addFlashAttribute("totalPrice", totalPrice);
            return "redirect:/opretskaderapport";

        }  else {
            // The finish condition is met, you can redirect or perform any other action here
            return "redirect:/kvitteringSkadeRapport";
        }

    }




    @GetMapping("/kvitteringSkadeRapport")
        public String kvittering(HttpSession session, Model model){
        if (!employeeService.checkSession(session)){
            return "redirect:/";
        }
        Double totalpris = (Double) session.getAttribute("totalPrice");
        Integer integer = (Integer) session.getAttribute("contract");
        model.addAttribute("contractid", integer);
        model.addAttribute("totalprisen", totalpris);
        return "kvitteringSkadeRapport";
        }

    @PostMapping("/Bekræftkvittering")
    public String kvitteringForDamageReport(Model model, RedirectAttributes redirectAttributes, Integer finish, HttpSession session, Damage_report damage_report) {
       Leasing_contract leasing_contract = (Leasing_contract) session.getAttribute("leasingcontract");
        damage_reportService.addDamage_report(damage_report);
        carService.updateAfterDamageReport(leasing_contract.getVehicle_number());
      return "redirect:/skaderapport";
    }




}