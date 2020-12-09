package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth fAuth;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fAuth = FirebaseAuth.getInstance();

        /*Check  Whether Current User logged in through Customer or vendor*/
        if (fAuth.getCurrentUser() != null) {
            reference = FirebaseDatabase.getInstance().getReference().child("Customers");
            reference.child(fAuth.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if(dataSnapshot.exists())
                    {
                        finish();
                        startActivity(new Intent(getApplicationContext(), ShopCategories.class));
                    }
                    else
                    {
                        finish();
                        startActivity(new Intent(getApplicationContext(), AfterLoginActivity.class));
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }
        else
        {
            finish();
            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
        }

    }

    /* Onclick for new vendors*/


}

// Query applesQuery = reference.orderByKey().equalTo(fAuth.getUid());
            /*
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        if (snapshot.getKey().equals(fAuth.getUid())) {
                            finish();
                            startActivity(new Intent(getApplicationContext(), ShopCategories.class));
                            flag = false;
                        }
                    }
                    if (flag) {
                        finish();
                        startActivity(new Intent(getApplicationContext(), AfterLoginActivity.class));
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
            */