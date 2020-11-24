package com.example.myapplication;

public class Authenticate {
    boolean vendor,customer;
    public Authenticate(boolean vendor,boolean customer) {
        this.vendor=vendor;
        this.customer=customer;
    }

    public boolean isVendor() {
        return vendor;
    }

    public void setVendor(boolean vendor) {
        this.vendor = vendor;
    }

    public boolean isCustomer() {
        return customer;
    }

    public void setCustomer(boolean customer) {
        this.customer = customer;
    }
}
