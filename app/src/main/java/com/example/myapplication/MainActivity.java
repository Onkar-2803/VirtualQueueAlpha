package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void Login(View view)
    {
        Intent intent = new Intent(this, AfterLoginActivity.class);
        startActivity(intent);
    }
    public void Signup(View view)
    {
        Intent intent = new Intent(this, SignupActivity.class);
        startActivity(intent);
    }

}