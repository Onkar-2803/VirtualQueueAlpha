package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }
    public void Vendor(View view) {
        Intent intent = new Intent(this, VendorLoginActivity.class);
        startActivity(intent);
    }

    /*OnClick for new Customers*/

    public void Customer(View view) {
        Intent intent = new Intent(this, CustomerLoginActivity.class);
        startActivity(intent);
    }
}