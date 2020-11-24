package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth fAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fAuth=FirebaseAuth.getInstance();

    }
    public void Vendor(View view)
    {
        Intent intent = new Intent(this, VendorLoginActivity.class);
        startActivity(intent);
    }
    public void Customer(View view)
    {
        Intent intent = new Intent(this, CustomerLoginActivity.class);
        startActivity(intent);
    }

}