package com.example.bilabonnementen.model;

public class Damage_report {
    private int report_id;
    private double total_price;
    private String username;
    private int customer_id;

    public Damage_report() {
    }

    public Damage_report(int report_id, double total_price, String username, int customer_id) {
        this.report_id = report_id;
        this.total_price = total_price;
        this.username = username;
        this.customer_id = customer_id;
    }

    public int getReport_id() {
        return report_id;
    }

    public void setReport_id(int report_id) {
        this.report_id = report_id;
    }

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }
}
