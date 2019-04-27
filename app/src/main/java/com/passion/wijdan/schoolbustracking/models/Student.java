package com.passion.wijdan.schoolbustracking.models;

public class Student {
    public Student(String sName, String sClass, String pName, String pEmail, int busNo) {
        this.sName = sName;
        this.sClass = sClass;
        this.pName = pName;
        this.pEmail = pEmail;
        this.busNo = busNo;
    }
    String sName;

    public String getsName() {
        return sName;
    }

    public String getsClass() {
        return sClass;
    }

    public String getpName() {
        return pName;
    }

    public String getpEmail() {
        return pEmail;
    }

    public int getBusNo() {
        return busNo;
    }

    String sClass;
    String pName;
    String pEmail;
int busNo;
}
