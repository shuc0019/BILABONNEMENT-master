package com.example.bilabonnementen.repository;

import com.example.bilabonnementen.model.Customer;
import com.example.bilabonnementen.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerRepo {
    @Autowired
    JdbcTemplate template;

    public void createCustomer(Customer c){
        String sql = "INSERT INTO customer (customer_id,full_name, email, phone, address, cpr)" +
                "VALUES(?,?,?,?,?,?)";

        template.update(sql,c.getCustomer_id(),  c.getFull_name(), c.getEmail(), c.getPhone(), c.getAddress(), c.getCpr());

    }

    //TODO read metode
    public List<Customer> fetchAll() {
        String sql = "SELECT * FROM customer";
        RowMapper<Customer> rowMapper = new BeanPropertyRowMapper<>(Customer.class);
        return template.query(sql, rowMapper);
    }
    //TODO DELETE metode



    //TODO UPDATE metode (ændringer i kundens oplysninger..)
    public void updateCustomer(Customer customer, int customer_id) {
        String sql = "UPDATE customer SET full_name = ?, email = ?, phone = ?, address = ?, cpr = ? WHERE customer_id = ?";
        template.update(sql, customer.getFull_name(), customer.getEmail(), customer.getPhone(), customer.getAddress(), customer.getCpr(), customer.getCustomer_id());
    }
    public Customer findCustomerByid(int customer_id){
        String sql = "Select * FROM customer WHERE customer_id = ?";
        RowMapper<Customer > rowMapper=new BeanPropertyRowMapper<>(Customer.class);
        return template.queryForObject(sql,rowMapper, customer_id);

    }
    public String findIdByEmail(String email){
        String sql = "Select customer_id FROM customer WHERE email = ?";
        return template.queryForObject(sql, String.class, email);

    }



}
