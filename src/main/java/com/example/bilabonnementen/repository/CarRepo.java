package com.example.bilabonnementen.repository;

import com.example.bilabonnementen.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository

public class CarRepo {
        @Autowired
        JdbcTemplate template;

        public List<Car> fetchAll(){
            String sql = "SELECT * FROM Car";
            RowMapper<Car> rowMapper = new BeanPropertyRowMapper<>(Car.class);
            return template.query(sql, rowMapper);
        }


        public List<Car> fetchAvailable() {
            String sql = "SELECT * FROM Car WHERE flow = 0";
            RowMapper<Car> rowMapper = new BeanPropertyRowMapper<>(Car.class);
            return template.query(sql, rowMapper);
        }

        public void addCar(Car c){
            String sql = "INSERT INTO car (vehicle_number,frame_number, " +
                    "brand, model, make, color, price, flow, odometer, fuel_type, motor, gear_type)" +
                    " VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";

            template.update(sql,c.getVehicle_number(), c.getFrame_number(), c.getBrand(), c.getModel(), c.getMake(),
            c.getColor(), c.getPrice(), c.getFlow(), c.getOdometer(), c.getFuel_type(), c.getMotor(), c.getGear_type());


        }



        public Car findAvailableCarByVehicleNum (int vehicle_number, int flow){

            String sql = "SELECT * FROM car WHERE vehicle_number = ? AND flow = 0";
            RowMapper<Car >rowMapper=new BeanPropertyRowMapper<>(Car.class);
            Car c = template.queryForObject(sql,rowMapper,vehicle_number, flow);

        return c;
        }
    public Boolean deleteCar(int vehicle_number){
        String sql = "DELETE FROM car WHERE vehicle_number = ?";
        return template.update(sql,vehicle_number) > 0;
    }

        // TODO add search button

        // TODO delete car (Admin feature)

        // TODO update feature?









    }
