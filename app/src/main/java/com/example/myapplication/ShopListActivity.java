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
        /* Finds listview i.e binds it from xml to java code in order to use it using findviewbyid*/
        listView=findViewById(R.id.listView_xml);

        /* Catches Intent Passed from ShopCategories Activity to get which button is clicked i.e
        * either barber or grocery or pharma*/
        Intent intent=getIntent();
        String text1=getIntent().getStringExtra("temp");


        /* Create a ArrayList to store Values of Shops and pass it to arrayadapter so that it can adapat the data*/
        ArrayList<String> list=new ArrayList<>();
        ArrayAdapter adapter=new ArrayAdapter<String>(this,R.layout.list_item,list);

        /* Pass adpater to listview so that it can display it*/
        listView.setAdapter(adapter);

        /*Check whether the incoming intent is clicked on barber or pharma or grocery*/
        if(text1.equals("barber")){
            /* Pass reference of database to DataBasereference object */
           DatabaseReference reference1= FirebaseDatabase.getInstance().getReference().child("Shops").child("barber");
            reference1.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                        /* Store the data in information class that we created so that we can pass single object to listview to display it*/
                        Information info= snapshot.getValue(Information.class);
                        /* getdata from info object and store it in txt file*/
                        String txt=info.getShopname()+ ": "+info.getAddress();
                        /* Pass the data to list to display it on recylerview*/
                        list.add(txt);
                    }
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        /* Same like above*/
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
    /* onclick  when user clicks on logout button*/
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