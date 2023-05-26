package com.example.bilabonnementen.repository;

import com.example.bilabonnementen.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class DamageRepo {
    @Autowired
    JdbcTemplate template;

    public List<Damage_category> fetchAll() {
        String sql = "SELECT * FROM damage_category";
        RowMapper<Damage_category> rowMapper = new BeanPropertyRowMapper<>(Damage_category.class);
        return template.query(sql, rowMapper);
    }

    public void AddDamage(Damage_category d) {
        String sql = "INSERT INTO damage_category (category_id, damage_name, price) VALUES (?,?,?)";
        template.update(sql, d.getCategory_id(),d.getDamage_name(),d.getPrice());
    }
    public void updateDamage( Damage_category damage_category, int category_id ){
        String sql = "UPDATE damage_category SET damage_name= ?, price= ? where category_id=?";
        template.update(sql, damage_category.getDamage_name(), damage_category.getPrice(),damage_category.getCategory_id());
    }

    public Boolean deleteDamage(int category_id) {
        String sql = "DELETE FROM damage_category WHERE category_id = ?";
        return template.update(sql, category_id) > 0;
    }

    public Damage_category findDamageByid(int category_id) {
        String sql = "Select * FROM damage_category WHERE category_id = ?";
        RowMapper<Damage_category> rowMapper = new BeanPropertyRowMapper<>(Damage_category.class);
        List<Damage_category> users = template.query(sql, rowMapper, category_id);
        if (users.size() == 1) {
            return users.get(0);
        } else {
            return null;
        }

    }

    // tilføj specifikke skader metoden bliver ikke brugt endnu!
    public void addSpecificDamage(Specific_damage specific_damage) {
        String sql = "INSERT INTO specific_damage (specific_damage_id, report_id, category_id) VALUES (?,?,?)";
        template.update(sql, specific_damage.getSpecific_damage_id(), specific_damage.getReport_id(), specific_damage.getCategory_id());
    }

    public Double findSpecificDamagePrice(int category_id) {
        String sql = "SELECT price FROM damage_category WHERE category_id = ?";

        return template.queryForObject(sql, Double.class, category_id);
    }

    public void updateSpecificDamage(Specific_damage specific_damage){
        String sql = "UPDATE specific_damage SET specific_damage_id = ?, report_id= ?, category_id= ? where specific_damage_id=?";
        template.update(sql, specific_damage.getSpecific_damage_id(), specific_damage.getReport_id(), specific_damage.getCategory_id());

    }

}




