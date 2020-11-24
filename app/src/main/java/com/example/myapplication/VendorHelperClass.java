package com.example.myapplication;

public class VendorHelperClass {
    String email,shopname,address,postalcode,phone,password;

    public VendorHelperClass() {
    }

    public VendorHelperClass(String email, String shopname, String address, String postalcode, String phone, String password) {
        this.email = email;
        this.shopname = shopname;
        this.address = address;
        this.postalcode = postalcode;
        this.phone = phone;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

