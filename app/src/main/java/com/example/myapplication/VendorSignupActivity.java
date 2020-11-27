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

public class VendorSignupActivity extends AppCompatActivity {
    EditText email, shopname, address, postalcode, phone, password, category;
    Button register;
    ProgressBar progressBar;
    FirebaseAuth fAuth;


    FirebaseDatabase rootnode;
    DatabaseReference VendorReference, ShopReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_signup);

        category = findViewById(R.id.category_xml);
        email = findViewById(R.id.email_xml);
        shopname = findViewById(R.id.shop_xml);
        address = findViewById(R.id.address_xml);
        postalcode = findViewById(R.id.Code_xml);
        phone = findViewById(R.id.phone_xml);
        password = findViewById(R.id.password_xml);
        progressBar = findViewById(R.id.progress_circular_bar);

        register = findViewById(R.id.button_xml);
        fAuth = FirebaseAuth.getInstance();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                rootnode = FirebaseDatabase.getInstance();
                VendorReference = rootnode.getReference("Vendor");

                ShopReference = rootnode.getReference("Shops");


                String e_email = email.getText().toString().trim();
                String p_password = password.getText().toString().trim();
                if (TextUtils.isEmpty(e_email)) {
                    email.setError("Email is Required");
                    return;
                }
                if (TextUtils.isEmpty(p_password)) {
                    password.setError("Password is Required");
                    return;
                }
                String eemail = email.getText().toString().trim();
                String sshopname = shopname.getText().toString().trim();
                String aadress = address.getText().toString().trim();
                String ppostalcode = postalcode.getText().toString().trim();
                String pphone = phone.getText().toString().trim();
                String ppasword = password.getText().toString().trim();
                String ccategory = category.getText().toString().toLowerCase().trim();

                VendorHelperClass vendorHelperClass = new VendorHelperClass(eemail, sshopname, aadress, ppostalcode, pphone, ppasword, ccategory);
                Shophelperclass shophelperclass = new Shophelperclass(sshopname, aadress, ppostalcode);


                fAuth.createUserWithEmailAndPassword(e_email, p_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            VendorReference.child(fAuth.getUid()).setValue(vendorHelperClass);

                            ShopReference.child(ccategory).child(sshopname).setValue(shophelperclass);

                            Toast.makeText(VendorSignupActivity.this, "User Created", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), AfterLoginActivity.class));
                        } else {
                            Toast.makeText(VendorSignupActivity.this, "Error " + task.getException(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        });

    }

    public void Signup(View view) {
        Intent intent = new Intent(this, AfterLoginActivity.class);
        startActivity(intent);
        this.finish();
    }
}