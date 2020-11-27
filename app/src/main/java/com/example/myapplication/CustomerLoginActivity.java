package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CustomerLoginActivity extends AppCompatActivity {
    EditText memail,mpassword;
    Button mbutton;
    ProgressBar progressBar;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_login);
        memail=findViewById(R.id.customer_email);
        mpassword=findViewById(R.id.customer_password);
        mbutton=findViewById(R.id.customer_login);
        fAuth=FirebaseAuth.getInstance();

        mbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                String email=memail.getText().toString().trim();
                String password=mpassword.getText().toString().trim();
                if(TextUtils.isEmpty(email)){
                    memail.setError("Email is Required");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    mpassword.setError("Password is Required");
                    return;
                }
                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(CustomerLoginActivity.this,"Login Successfully",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),ShopCategories.class));
                        }
                        else {
                            Toast.makeText(CustomerLoginActivity.this,"Error " +task.getException(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
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