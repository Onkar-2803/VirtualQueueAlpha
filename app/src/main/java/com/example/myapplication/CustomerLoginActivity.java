package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CustomerLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_login);
    }
    public void Login(View view)
    {
        Intent intent = new Intent(this, ShopCategories.class);
        startActivity(intent);
    }
    public void Signup(View view)
    {
        Intent intent = new Intent(this, CustomerSignupActivity.class);
        startActivity(intent);
    }
}