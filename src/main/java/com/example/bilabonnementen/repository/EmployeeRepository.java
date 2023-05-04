package com.example.bilabonnementen.repository;

import com.example.bilabonnementen.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeRepository {
    @Autowired
    JdbcTemplate template;
    public Employee findByUserAndPassword(String username, String user_password){
       String sql = "SELECT * FROM Employee WHERE username=? AND user_password=?";
        RowMapper rowMapper = new BeanPropertyRowMapper(Employee.class);
        List<Employee> users = template.query(sql, rowMapper, username, user_password);
        if (users.size() == 1) {
            return users.get(0);
        } else {
            return null;
        }

    }

}
