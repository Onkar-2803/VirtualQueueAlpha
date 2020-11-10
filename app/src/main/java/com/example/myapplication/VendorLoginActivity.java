package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class VendorLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_login);
    }
    public void Login(View view)
    {
        Intent intent = new Intent(this, AfterLoginActivity.class);
        startActivity(intent);
    }
    public void Signup(View view)
    {
        Intent intent = new Intent(this, VendorSignupActivity.class);
        startActivity(intent);
    }
}