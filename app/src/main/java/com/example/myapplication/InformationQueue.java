package com.example.myapplication;

public class InformationQueue {
    String number;
    String uid;

    public InformationQueue() {

    }

    public InformationQueue(String number, String uid) {
        this.number = number;
        this.uid = uid;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
