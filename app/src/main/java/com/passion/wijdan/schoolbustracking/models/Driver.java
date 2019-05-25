package com.passion.wijdan.schoolbustracking.models;

public class Driver {
    public Driver(String name, String phone , int no , String password) {
        this.name = name;
        this.phone = phone;
        this.busNo=no;
        this.password=password;
    }
    public Driver() {

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

    public String name;
    public String phone;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBusNo(int busNo) {
        this.busNo = busNo;
    }

    public String password;

    public int getBusNo() {
        return busNo;
    }

    int busNo;
}
