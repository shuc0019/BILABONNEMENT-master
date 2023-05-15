package com.example.bilabonnementen.service;

import com.example.bilabonnementen.model.Employee;
import com.example.bilabonnementen.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    public List <Employee> fetchAllEmployees(){
        return employeeRepository.fetchAll();
    }

    public void createEmployee(Employee employee){
        employeeRepository.addEmployee(employee);
    }


}