package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

public class CustomerGetInLineActivity extends AppCompatActivity {
        TextView t1,t2;
        Button b1,b2,b3;
    FirebaseAuth fAuth;
   private String category;
    DatabaseReference reference;
        int countof_participants;
    int lastCoupon,Coupon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_get_in_line);
        t1=findViewById(R.id.textViewCurrentCouponNumber);
        t2=findViewById(R.id.currentparticipaipants);
        b1=findViewById(R.id.buttonGetInLine);
        b2=findViewById(R.id.mapsgoogle);
        Intent intent = getIntent();

        category =intent.getStringExtra("category");
       // b3=findViewById(R.id.)
        fAuth = FirebaseAuth.getInstance();
       //reference = FirebaseDatabase.getInstance().getReference().child("Queues").child(category);
       //setLastCoupon();
        Toast.makeText(CustomerGetInLineActivity.this,"Added",Toast.LENGTH_SHORT).show();

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
                final boolean[] exists = {false};

                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            if(snapshot.getValue().equals(fAuth.getUid()))
                            {
                                Toast.makeText(CustomerGetInLineActivity.this,"You Are Already Added",Toast.LENGTH_SHORT).show();
                             exists[0] =true;
                            }
                        }
                        if(!exists[0])
                        {
                            reference.child(String.valueOf(Coupon)).setValue(fAuth.getUid());
                            Toast.makeText(CustomerGetInLineActivity.this,"Coupon Number "+Coupon,Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
           //     Toast.makeText(CustomerGetInLineActivity.this,reference.toString(),Toast.LENGTH_SHORT).show();
           //     Toast.makeText(CustomerGetInLineActivity.this,reference.orderByKey().limitToLast(1).toString(),Toast.LENGTH_SHORT).show();
          //    reference.child(String.valueOf(reference.orderByKey().limitToLast(1))).setValue(fAuth.getUid());
              Toast.makeText(CustomerGetInLineActivity.this,"Added",Toast.LENGTH_SHORT).show();
               // String.valueOf(Integer.parseInt(String.valueOf(reference.orderByKey().limitToLast(1)))+1)
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

    private void setLastCoupon() {
        final boolean[] flag = {false};
        reference.orderByKey().limitToLast(1).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                if(!flag[0]) {
                    lastCoupon = Integer.parseInt(dataSnapshot.getKey());
                    Coupon=lastCoupon+1;
                    if(Coupon==0)
                    Coupon=1;
                    flag[0] =true;
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

            // ...
        });
    }

    private boolean addedToLine()
{
    reference = FirebaseDatabase.getInstance().getReference().child("Queues").child(category);
    final boolean[] flag = {false};
    reference.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                if(snapshot.getValue().equals(fAuth.getUid()))
                {
                    Toast.makeText(CustomerGetInLineActivity.this,snapshot.getValue().toString(),Toast.LENGTH_SHORT).show();
                    flag[0] = true;
                }
            }
            }


        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    });
    return flag[0];
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