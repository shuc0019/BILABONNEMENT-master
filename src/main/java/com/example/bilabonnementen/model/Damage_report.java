package com.example.bilabonnementen.model;

public class Damage_report {
    private int report_id;
    private double total_price;
    private int contract_id;

    public Damage_report() {
    }

    public Damage_report(int report_id, double total_price, int contract_id) {
        this.report_id = report_id;
        this.total_price = total_price;
        this.contract_id = contract_id;
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

    public int getContract_id() {
        return contract_id;
    }

    public void setContract_id(int contract_id) {
        this.contract_id = contract_id;
    }
}