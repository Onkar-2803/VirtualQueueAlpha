/*
MIT License

        Copyright (c) 2020 SDL-Project-2020 (Sarish Nigade, Onkar Litake, Parth Patil)

        Permission is hereby granted, free of charge, to any person obtaining a copy
        of this software and associated documentation files (the "Software"), to deal
        in the Software without restriction, including without limitation the rights
        to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
        copies of the Software, and to permit persons to whom the Software is
        furnished to do so, subject to the following conditions:

        The above copyright notice and this permission notice shall be included in all
        copies or substantial portions of the Software.

        THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
        IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
        FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
        AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
        LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
        OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
        SOFTWARE.
*/
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

public class CustomerSignupActivity extends AppCompatActivity {
    EditText mname, memail, mphone, mpassword, maddress;
    Button mbutton;
    ProgressBar progressBar;
    FirebaseAuth fAuth;

    FirebaseDatabase rootnode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /* gets id of xml files*/
        setContentView(R.layout.activity_customer_signup);
        mname = findViewById(R.id.editTextEditName1);
        mphone = findViewById(R.id.editTextEditPhone1);
        memail = findViewById(R.id.email1);
        mpassword = findViewById(R.id.editTextEditPassword1);
        mbutton = findViewById(R.id.buttonregister11);
        maddress = findViewById(R.id.address_xml11);

        fAuth = FirebaseAuth.getInstance();

        mbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                rootnode = FirebaseDatabase.getInstance();
                reference = rootnode.getReference("Customers");

                /* Store the values of edittext into variables that are entered by user*/
                String email = memail.getText().toString().trim();
                String password = mpassword.getText().toString().trim();
                String Name = mname.getText().toString().trim();
                String Phone = mphone.getText().toString().trim();
                String address = maddress.getText().toString().trim();


                if (TextUtils.isEmpty(email)) {
                    memail.setError("Email is Required");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    mpassword.setError("Password is Required");
                    return;
                }
                /* Pass values to a class */
                UserHelperClass userHelperClass = new UserHelperClass(Name, Phone, email, password, address);

                /* Method to create new sign in */
                fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            reference.child(fAuth.getUid()).setValue(userHelperClass);
                            Toast.makeText(CustomerSignupActivity.this, "User Created", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), ShopCategories.class));
                        } else {
                            Toast.makeText(CustomerSignupActivity.this, "Error " + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            }
        });

    }

}