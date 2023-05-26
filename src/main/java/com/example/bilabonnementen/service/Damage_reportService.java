package com.example.bilabonnementen.service;

import com.example.bilabonnementen.model.Damage_report;
import com.example.bilabonnementen.repository.Damage_reportRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Damage_reportService {
    @Autowired
    Damage_reportRepo damage_reportRepo;

     public List<Damage_report> showReport() {
       return damage_reportRepo.fetchAll();
    }
    public void addDamage_report(Damage_report damage_report){
         damage_reportRepo.CreateDamage_report(damage_report);
    }
    public boolean deleteReport(int report_id){
         return damage_reportRepo.deleteReport(report_id);
    }
}