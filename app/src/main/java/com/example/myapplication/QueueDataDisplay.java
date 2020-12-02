package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class QueueDataDisplay extends AppCompatActivity {


    private ListView listView;
    private ArrayList<InformationQueue> words = new ArrayList<>();
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queue_data_display);
         String category;
         String shopname;
        Intent intent= getIntent();
        listView=findViewById(R.id.list_view_queue);
        category=getIntent().getStringExtra("category_1");
        shopname=getIntent().getStringExtra("shopname_1");
        firebaseAuth=FirebaseAuth.getInstance();

        final ArrayList<String> list=new ArrayList<>();

       final ArrayAdapter adapter=new ArrayAdapter<String >(this,R.layout.list_item_queue,list);
       listView.setAdapter(adapter);
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Queues").child(category).child(shopname);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //words.clear();
                list.clear();

                for(DataSnapshot snapshot: dataSnapshot.getChildren()){

                        String temp1=snapshot.getValue().toString();
                        DatabaseReference namereference= FirebaseDatabase.getInstance().getReference().child("Customers").child(temp1);

                  //DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("Queues").c;

                        String temp2=snapshot.getKey().toString();
                       InformationQueue info1=new InformationQueue(temp2, temp1);

                      String txt=info1.getNumber()+": "+info1.getUid();
                       list.add(txt);
                       //words.add(info1);
                       //WordAdapterQueue adapter=new WordAdapterQueue(getApplicationContext(),words);

                      // ListView listView = (ListView) findViewById(R.id.list_view_queue);
                       //listView.setAdapter(adapter);
                    }
                    adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}