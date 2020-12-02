package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class VendorLoginActivity extends AppCompatActivity {
    EditText memail, mpassword;
    Button mLogin;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_login);
        memail = findViewById(R.id.email_xml1);
        mpassword = findViewById(R.id.password_xml1);
        mLogin = findViewById(R.id.buttonLogin1);
        fAuth = FirebaseAuth.getInstance();

        if (fAuth.getCurrentUser() != null) {
 
            startActivity(new Intent(getApplicationContext(), AfterLoginActivity.class));
            finish();
        }

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = memail.getText().toString().trim();
                String password = mpassword.getText().toString().trim();
                if (TextUtils.isEmpty(email)) {
                    memail.setError("Email is Required");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    mpassword.setError("Password is Required");
                    return;
                }

                //authenticate user
                fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            String currentUser=FirebaseAuth.getInstance().getCurrentUser().getUid();
                            //Toast.makeText(VendorLoginActivity.this, ""+currentUser, Toast.LENGTH_LONG).show();
                            //DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Vendor").child()
                            Toast.makeText(VendorLoginActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                            Intent intent= new Intent(getApplicationContext(),AfterLoginActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(VendorLoginActivity.this, "Error " + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    public void Login(View view) {
        Intent intent = new Intent(this, AfterLoginActivity.class);
        startActivity(intent);
    }

    public void Signup(View view) {
        Intent intent = new Intent(this, VendorSignupActivity.class);
        startActivity(intent);
    }
}