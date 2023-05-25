package com.example.bilabonnementen.service;

import com.example.bilabonnementen.model.Employee;
import com.example.bilabonnementen.repository.EmployeeRepository;
import jakarta.servlet.http.HttpSession;
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

    public void fireEmployee(String username){
        employeeRepository.fireEmployee(username);
    }

    public void updateEmployee(Employee employee){
        employeeRepository.updateEmployee(employee);

    }

    public Employee findAdminUser(String username){
        return employeeRepository.findAdmin(username);
    }

    public Employee findByUsername(String username){
        return employeeRepository.findByUsername(username);


    }

    public Employee findbyuserandpassword(String username, String user_password){
        return employeeRepository.findByUserAndPassword(username, user_password);

    }

    public Boolean checkSession(HttpSession httpSession){
        return httpSession.getAttribute("adminlogin") != null;
    }


}