package com.passion.wijdan.schoolbustracking.models;

public class Driver {
    public Driver(String name, String phone , int no) {
        this.name = name;
        this.phone = phone;
        this.busNo=no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String name,phone;

    public int getBusNo() {
        return busNo;
    }

    int busNo;
}
