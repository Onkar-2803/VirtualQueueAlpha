package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class CustomerGetInLineActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_get_in_line);
    }

    public void GetInLine(View view)
    {
        Toast.makeText(this,"Added to Queue",Toast.LENGTH_LONG).show();
    }

    public void CurrentLine(View view)
    {
        Intent intent = new Intent(this, LineActivity.class);
        startActivity(intent);
    }

    public void ExitLine(View view)
    {

        finish();
    }
    public void Back(View view)
    {

        finish();
    }
}