package com.example.bilabonnementen.service;

import com.example.bilabonnementen.model.Car;
import com.example.bilabonnementen.repository.CarRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {
    @Autowired
    private CarRepo carRepo;

    public List<Car> fetchAll() {
        return carRepo.fetchAll();
    }

    public List<Car>fetchAvailable(){
        return carRepo.fetchAvailable();
    }
    public void addCar( Car car){
        carRepo.addCar(car);
    }
    public boolean deleteCar(int id){
        return carRepo.deleteCar(id);
    }


}
