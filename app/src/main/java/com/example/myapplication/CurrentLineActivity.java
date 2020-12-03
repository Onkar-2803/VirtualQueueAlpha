package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class CurrentLineActivity extends AppCompatActivity {
    String shopname,category,name;
TextView number;
    FirebaseAuth fAuth;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_line);
        number = (TextView) findViewById(R.id.textViewCurrentCouponNumber);
        fAuth= FirebaseAuth.getInstance();
        user=fAuth.getCurrentUser();
        DatabaseReference reference1= FirebaseDatabase.getInstance().getReference().child("Vendor").child(user.getUid());
        reference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                shopname=dataSnapshot.child("shopname").getValue().toString();
                category=dataSnapshot.child("category").getValue().toString();
                // name=dataSnapshot.child("name").getValue().toString();

                Toast.makeText(getApplicationContext(), shopname+": -"+category, Toast.LENGTH_LONG).show();
                DatabaseReference reference3= FirebaseDatabase.getInstance().getReference().child("Queues").child(category).child(shopname);
                reference3.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                        for(DataSnapshot snapshot: dataSnapshot.getChildren()){

                            String temp1=snapshot.getValue().toString();
                            String temp2=snapshot.getKey().toString();
                            InformationQueue info1=new InformationQueue(temp2, temp1);

                            String txt=info1.getNumber()+": "+info1.getUid();

                            number.setText(txt);
                            break;

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }


    public void NextNumber(View view)
    {




        DatabaseReference reference1= FirebaseDatabase.getInstance().getReference().child("Vendor").child(user.getUid());
        reference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                shopname=dataSnapshot.child("shopname").getValue().toString();
                category=dataSnapshot.child("category").getValue().toString();
                // name=dataSnapshot.child("name").getValue().toString();

                Toast.makeText(getApplicationContext(), shopname+": -"+category, Toast.LENGTH_LONG).show();



                        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                        Query applesQuery = ref.child("Queues").child(category).child(shopname).orderByKey().limitToFirst(1);

                        applesQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                for (DataSnapshot appleSnapshot: dataSnapshot.getChildren()) {
                                    appleSnapshot.getRef().removeValue();
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });




            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



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