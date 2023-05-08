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

    //Find employee hvor username er ? og user_password er ?... Vi bruger den login
    public Employee findByUserAndPassword(String username, String user_password){
       String sql = "SELECT * FROM Employee WHERE username=? AND user_password=?";
        RowMapper rowMapper = new BeanPropertyRowMapper(Employee.class);
        List<Employee> employees = template.query(sql, rowMapper, username, user_password);
        if ((employees.size()) == 1) {
            return employees.get(0);
        } else {
            return null;
        }

    }
    //Hent alle employees (users) ... Til visning af alle users
    public List<Employee>fetchAll(){
        String sql = "SELECT * FROM Employee";
        RowMapper<Employee> rowMapper = new BeanPropertyRowMapper<>(Employee.class);
        return template.query(sql,rowMapper);
    }

}
