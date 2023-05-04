package com.example.bilabonnementen.model;

public class Employee {
    private String username;
    private String user_password;
    private String full_name;
    private String email;
    private String phone;
    private boolean is_active;
    private boolean is_admin;

    public Employee() {
    }

    public Employee(String username, String user_password, String full_name, String email, String phone, boolean is_active, boolean is_admin) {
        this.username = username;
        this.user_password = user_password;
        this.full_name = full_name;
        this.email = email;
        this.phone = phone;
        this.is_active = is_active;
        this.is_admin = is_admin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

    public boolean isIs_admin(boolean b) {
        return is_admin;
    }

    public void setIs_admin(boolean is_admin) {
        this.is_admin = is_admin;
    }
}
