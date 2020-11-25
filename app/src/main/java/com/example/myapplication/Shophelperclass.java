package com.example.myapplication;

public class Shophelperclass {
    String shopname,address,postcalcode;

    public Shophelperclass() {

    }

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostcalcode() {
        return postcalcode;
    }

    public void setPostcalcode(String postcalcode) {
        this.postcalcode = postcalcode;
    }

    public Shophelperclass(String shopname, String address, String postcalcode) {
        this.shopname = shopname;
        this.address = address;
        this.postcalcode = postcalcode;
    }
}
