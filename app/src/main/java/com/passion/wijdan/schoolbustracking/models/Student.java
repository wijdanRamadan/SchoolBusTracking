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

    public Student() {

    }

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

    public void setsName(String sName) {
        this.sName = sName;
    }

    public void setsClass(String sClass) {
        this.sClass = sClass;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public void setpEmail(String pEmail) {
        this.pEmail = pEmail;
    }

    public void setBusNo(int busNo) {
        this.busNo = busNo;
    }

    String sClass;
    String pName;
    String pEmail;
int busNo;
}
