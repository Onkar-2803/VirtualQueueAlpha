package com.example.myapplication;

public class Information {
    private  String address, shopname;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    public Information() {

    }

    public Information(String email, String name) {
        this.address = email;
        this.shopname = name;
    }
}
