package com.passion.wijdan.schoolbustracking.models;

public class Bus {
    private String driverName;
    private int busNo;
    private String contactNo;

    public Bus(String driverName, int busNo, String contactNo, String lat, String lng) {
        this.driverName = driverName;
        this.busNo = busNo;
        this.contactNo = contactNo;
        this.lat = lat;
        this.lng = lng;
    }
    public Bus() {
     
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public void setBusNo(int busNo) {
        this.busNo = busNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    private String lat;
    private String lng;

    public String getDriverName() {
        return driverName;
    }

    public int getBusNo() {
        return busNo;
    }

    public String getContactNo() {
        return contactNo;
    }
}