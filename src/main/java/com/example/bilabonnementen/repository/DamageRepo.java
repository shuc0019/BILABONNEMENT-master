package com.example.bilabonnementen.repository;

import com.example.bilabonnementen.model.Damage_category;
import com.example.bilabonnementen.model.Specific_damage;
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
}
