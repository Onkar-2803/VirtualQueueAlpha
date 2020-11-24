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

public class CustomerSignupActivity extends AppCompatActivity {
    EditText mname,memail,mphone,mpassword;
    Button mbutton;
    ProgressBar progressBar;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_signup);
        mname=findViewById(R.id.editTextEditName1);
        mphone=findViewById(R.id.editTextEditPhone1);
        memail=findViewById(R.id.email1);
        mpassword=findViewById(R.id.editTextEditPassword1);
        mbutton=findViewById(R.id.buttonregister11);

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

                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(CustomerSignupActivity.this,"User Created",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),ShopListActivity.class));
                        }
                        else {
                            Toast.makeText(CustomerSignupActivity.this,"Error " +task.getException(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }

}