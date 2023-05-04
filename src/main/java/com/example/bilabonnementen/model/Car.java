package com.example.bilabonnementen.model;

public class Car {
    private int vehicle_number;
    private String frame_number;
    private String brand;
    private String model;
    private int make;
    private String color;
    private double price;
    private int flow;

    public Car() {
    }

    public Car(int vehicle_number, String frame_number, String brand, String model, int make, String color, double price, int flow) {
        this.vehicle_number = vehicle_number;
        this.frame_number = frame_number;
        this.brand = brand;
        this.model = model;
        this.make = make;
        this.color = color;
        this.price = price;
        this.flow = flow;
    }

    public int getVehicle_number() {
        return vehicle_number;
    }

    public void setVehicle_number(int vehicle_number) {
        this.vehicle_number = vehicle_number;
    }

    public String getFrame_number() {
        return frame_number;
    }

    public void setFrame_number(String frame_number) {
        this.frame_number = frame_number;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getMake() {
        return make;
    }

    public void setMake(int make) {
        this.make = make;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getFlow() {
        return flow;
    }

    public void setFlow(int flow) {
        this.flow = flow;
    }
}
