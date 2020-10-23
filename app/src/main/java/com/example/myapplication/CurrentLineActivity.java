package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class CurrentLineActivity extends AppCompatActivity {
TextView number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_line);
        number = (TextView) findViewById(R.id.textViewCurrentCouponNumber);
    }
    public void VerifyOTP(View view)
    {
        Toast.makeText(this,"OTP Verified",Toast.LENGTH_LONG).show();
    }

    public void NextNumber(View view)
    {
        int num = Integer.parseInt(number.getText().toString());
        num++;
        number.setText(""+num);

    }

    public void CurrentLine(View view)
    {
        Intent intent = new Intent(this, LineActivity.class);
        startActivity(intent);
    }

    public void StopLine(View view)
    {
        Toast.makeText(this,"Line Stopped",Toast.LENGTH_LONG).show();
    }
    public void Back(View view)
    {

        finish();
    }

}