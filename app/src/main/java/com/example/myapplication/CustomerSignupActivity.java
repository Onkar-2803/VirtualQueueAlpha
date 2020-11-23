package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CustomerSignupActivity extends AppCompatActivity {
    EditText e1,e2,e3,e4;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_signup);
        e1=findViewById(R.id.editTextEditName);
        e2=findViewById(R.id.editTextEditPhone);
        e3=findViewById(R.id.email);
        e4=findViewById(R.id.editTextEditPassword);
        b1=findViewById(R.id.buttonregister);

        String name=e1.getText().toString();
        String phoneNo=e2.getText().toString().trim();
        String email=e3.getText().toString();
        String password=e4.getText().toString();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent intent=new Intent(getApplicationContext(),CustomerVerifyPhoneNumber.class);
                    intent.putExtra("phoneNo",phoneNo);
                    startActivity(intent);
            }
        });
    }
    public void Signup(View view)
    {
        Intent intent = new Intent(this, ShopListActivity.class);
        startActivity(intent);
        this.finish();
    }
}