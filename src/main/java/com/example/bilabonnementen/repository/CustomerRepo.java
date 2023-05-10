package com.example.bilabonnementen.repository;

import com.example.bilabonnementen.model.Car;
import com.example.bilabonnementen.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerRepo {
    @Autowired
    JdbcTemplate template;

    public void createCustomer(Customer c){
        String sql = "INSERT INTO customer (customer_id,full_name, email, phone, address, cpr) " +
                        "VALUES(?,?,?,?,?,?)";

        template.update(sql, c.getCustomer_id(), c.getFull_name(), c.getEmail(), c.getPhone(), c.getAddress(), c.getCpr());


    }

    //TODO read metode

    //TODO UPDATE metode (ændringer i kundens oplysninger..)

    //TODO DELETE metode

}
