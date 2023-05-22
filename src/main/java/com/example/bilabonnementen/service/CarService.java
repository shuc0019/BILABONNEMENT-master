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

    public void updateCar(Car car, int id){
        carRepo.updateCar(car, id);
    }
    public void updateAfterContract(int id){
        carRepo.updateAfterContract(id);
    }
    public Car findId(int id){
        return carRepo.findCarByid(id);
    }
    public double calculateTotalPriceOfRentedCars() {
        List<Car> rentedCars = carRepo.fetchRentedCars(); // Retrieve rented cars
        double totalPrice = 0.0;

        for (Car car : rentedCars) {
            if (car.getFlow() == 1) { // Check if car is rented (flow = 1)
                totalPrice += car.getPrice(); // Add the car's price to the total
            }
        }

        return totalPrice;
    }

    public List <Car> getRentedCars(){
        return carRepo.fetchRentedCars();
    }

    public void updateAfterDamageReport(int id){
        carRepo.updateAfterDamageReport(id);
    }

}
