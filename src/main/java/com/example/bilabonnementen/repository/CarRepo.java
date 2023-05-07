package com.example.bilabonnementen.repository;

import com.example.bilabonnementen.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository

public class CarRepo {
        @Autowired
        JdbcTemplate template;

        public List<Car> fetchAll(){
            String sql = "SELECT * FROM Car";
            RowMapper<Car> rowMapper = new BeanPropertyRowMapper<>(Car.class);
            return template.query(sql, rowMapper);
        }


        public List<Car> fetchAvailable() {
            String sql = "SELECT * FROM Car WHERE flow = 1";
            RowMapper<Car> rowMapper = new BeanPropertyRowMapper<>(Car.class);
            return template.query(sql, rowMapper);
        }



    }
