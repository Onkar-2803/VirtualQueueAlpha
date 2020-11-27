package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ShopListActivity extends AppCompatActivity {


    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_list);
        listView=findViewById(R.id.listView_xml);


        Intent intent=getIntent();
        String text1=getIntent().getStringExtra("temp");

        ArrayList<String> list=new ArrayList<>();
        ArrayAdapter adapter=new ArrayAdapter<String>(this,R.layout.list_item,list);

        listView.setAdapter(adapter);
        /*DatabaseReference reference= FirebaseDatabase.getInstance().getReference().child("Shops").child("pharma");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                    Information info= snapshot.getValue(Information.class);
                    String txt=info.getShopname()+ ": "+info.getAddress();
                    list.add(txt);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/

        if(text1.equals("barber")){
           DatabaseReference reference1= FirebaseDatabase.getInstance().getReference().child("Shops").child("barber");
            reference1.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                        Information info= snapshot.getValue(Information.class);
                        String txt=info.getShopname()+ ": "+info.getAddress();
                        list.add(txt);
                    }
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        if(text1.equals("pharma")){
            DatabaseReference reference2= FirebaseDatabase.getInstance().getReference().child("Shops").child("pharma");
            reference2.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                        Information info= snapshot.getValue(Information.class);
                        String txt=info.getShopname()+ ": "+info.getAddress();
                        list.add(txt);
                    }
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        if(text1.equals("grocery")){
            DatabaseReference reference3= FirebaseDatabase.getInstance().getReference().child("Shops").child("grocery");
            reference3.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                        Information info= snapshot.getValue(Information.class);
                        String txt=info.getShopname()+ ": "+info.getAddress();
                        list.add(txt);
                    }
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }


    }
    public  void logout(View view){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(),CustomerLoginActivity.class));
        finish();
    }
    public void selectShop(View view)
    {
        Intent intent = new Intent(getApplicationContext(), CustomerGetInLineActivity.class);
        startActivity(intent);
    }
    public void Back(View view)
    {
        this.finish();
    }
}