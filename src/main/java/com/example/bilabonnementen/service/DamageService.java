package com.example.bilabonnementen.service;

import com.example.bilabonnementen.model.Damage_category;
import com.example.bilabonnementen.model.Specific_damage;
import com.example.bilabonnementen.repository.DamageRepo;
import jdk.jfr.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DamageService {
    @Autowired
    DamageRepo damageRepo;

    public List<Damage_category> fetchAllDamageCategories(){
        return damageRepo.fetchAll();
    }

    public Double getSpecificDamagePrice(int category_id) {
        return damageRepo.findSpecificDamagePrice(category_id);
    }
}
