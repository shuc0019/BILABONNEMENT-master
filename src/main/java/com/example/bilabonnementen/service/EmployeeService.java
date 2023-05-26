package com.example.bilabonnementen.service;

import com.example.bilabonnementen.model.Employee;
import com.example.bilabonnementen.repository.EmployeeRepo;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepo employeeRepo;

    public List <Employee> fetchAllEmployees(){
        return employeeRepo.fetchAll();
    }

    public void createEmployee(Employee employee){
        employeeRepo.addEmployee(employee);
    }

    public void fireEmployee(String username){
        employeeRepo.fireEmployee(username);
    }

    public void updateEmployee(Employee employee){
        employeeRepo.updateEmployee(employee);

    }

    public Employee findAdminUser(String username){
        return employeeRepo.findAdmin(username);
    }

    public Employee findByUsername(String username){
        return employeeRepo.findByUsername(username);


    }

    public Employee findbyuserandpassword(String username, String user_password){
        return employeeRepo.findByUserAndPassword(username, user_password);

    }

    public Boolean checkSession(HttpSession httpSession){
        return httpSession.getAttribute("adminlogin") != null;
    }


}