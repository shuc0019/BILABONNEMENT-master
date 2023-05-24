package com.example.bilabonnementen;

import com.example.bilabonnementen.model.Car;
import com.example.bilabonnementen.repository.CarRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class BilabonnementenApplicationTests {


	@Autowired
	CarRepo carRepo;
	@Test
	void testForAtSeAlleBiler() {
		List<Car> cars = carRepo.fetchAll();
		Assertions.assertNotNull(cars);
	}
	@Test
void testForOmBilenBliverTiltøjetTilDatabasen(){
		List<Car> carsBefore = carRepo.fetchAll();
		int beforeAdded = carsBefore.size();
		Car car = new Car();
		car.setBrand("bmw");
		car.setFlow(0);
		car.setColor("black");
		car.setMake(2003);
		car.setModel("a8");
		car.setMotor("l2");
		car.setOdometer(0);
		car.setFuel_type("benz");
		car.setGear_type("manual");
		car.setPrice(2222);
		car.setFrame_number("662222");
		carRepo.addCar(car);
		List<Car> carsAfter = carRepo.fetchAll();
		int afterAdded = carsAfter.size();

		Assertions.assertEquals(afterAdded, beforeAdded+1);
		Assertions.assertEquals(afterAdded, beforeAdded-1);
}
@Test
	void testForDelete() {
		List<Car> carsBefore = carRepo.fetchAll();
		int beforeDeleted = carsBefore.size();
		// Vi skal vælge en bil id at slette, vi vælger en arbitrær værdi fra tabellen.
		// Vi tager i det her eksempel car id 30
		carRepo.deleteCar(30);
		List<Car> carsAfter = carRepo.fetchAll();
		int afterDeleted = carsAfter.size();
		Assertions.assertEquals(afterDeleted, beforeDeleted-1);
		//Succesfully
	}
	}
