package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }
    public void ChangeInfo(View view)
    {
        Intent intent = new Intent(this, EditInfoActivity.class);
        startActivity(intent);
    }

    public void SaveAndExit(View view)
    {
        this.finish();
    }

}