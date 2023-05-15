package com.example.bilabonnementen.repository;

import com.example.bilabonnementen.model.Leasing_contract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class Leasing_contractRepo {
    @Autowired
    JdbcTemplate jdbcTemplate;


    public void createLeasingContract(Leasing_contract leasingContract) {
        String sql = "INSERT INTO leasing_contract (start_date, end_date, price, vehicle_number, username, customer_id) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, leasingContract.getStart_date(), leasingContract.getEnd_date(), leasingContract.getPrice(),
                leasingContract.getVehicle_number(), leasingContract.getUsername(), leasingContract.getCustomer_id());
    }
}