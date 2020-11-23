package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class CustomerGetInLineActivity extends AppCompatActivity {
        TextView t1,t2;
        Button b1,b2,b3;

        int countof_participants;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_get_in_line);
        t1=findViewById(R.id.textViewCurrentCouponNumber);
        t2=findViewById(R.id.currentparticipaipants);
        b1=findViewById(R.id.buttonGetInLine);
        b2=findViewById(R.id.mapsgoogle);
       // b3=findViewById(R.id.)

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countof_participants++;
                Random rand=new Random();
                int rand_int=rand.nextInt(100);
                String str1=Integer.toString(rand_int);
                String str2=Integer.toString(countof_participants);
              t1.setText(str1);
              t2.setText(str2);
              Toast.makeText(CustomerGetInLineActivity.this,"Added",Toast.LENGTH_SHORT).show();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(CustomerGetInLineActivity.this,MapsActivity.class);
                startActivity(intent);
            }
        });


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