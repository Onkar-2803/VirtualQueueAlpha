package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AfterLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_login_activty);
    }
    public void CurrentLine(View view)
    {
        Intent intent = new Intent(this, CurrentLineActivity.class);
        startActivity(intent);
    }
    public void PreviousLine(View view)
    {
        Intent intent = new Intent(this, LineActivity.class);
        startActivity(intent);
    }
    public void Settings(View view)
    {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }
    public void Exit(View view)
    {
        finish();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}