package com.example.bilabonnementen.repository;

import com.example.bilabonnementen.model.Car;
import com.example.bilabonnementen.model.Leasing_contract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class Leasing_contractRepo {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Leasing_contract> fetchAll(){
        String sql = "SELECT * FROM leasing_contract";
        RowMapper<Leasing_contract> rowMapper = new BeanPropertyRowMapper<>(Leasing_contract.class);
        return jdbcTemplate.query(sql, rowMapper);
    }

    public void createLeasingContract(Leasing_contract leasingContract) {
        String sql = "INSERT INTO leasing_contract (start_date, end_date, price, vehicle_number, username, customer_id) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, leasingContract.getStart_date(), leasingContract.getEnd_date(), leasingContract.getPrice(),
                leasingContract.getVehicle_number(), leasingContract.getUsername(), leasingContract.getCustomer_id());
    }

    public Leasing_contract findContractByid(int contract_id) {
        String sql = "Select * FROM leasing_contract WHERE contract_id = ?";
        RowMapper< Leasing_contract> rowMapper = new BeanPropertyRowMapper<>( Leasing_contract.class);
        List< Leasing_contract> users = jdbcTemplate.query(sql, rowMapper, contract_id);
        if (users.size() == 1) {
            return users.get(0);
        } else {
            return null;
        }
    }

   
}