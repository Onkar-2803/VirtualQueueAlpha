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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LineActivity extends AppCompatActivity {
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logout:
                logout();
                Toast.makeText(getApplicationContext(), "Logged Out", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void logout() {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }
        String shopname,category,name;
        FirebaseAuth fAuth;
        FirebaseUser user;
        ListView listView;
    private ArrayList<InformationQueue> words = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line);
        fAuth=FirebaseAuth.getInstance();
        listView=findViewById(R.id.listvendor_queue);
        String currentUser;
        user=fAuth.getCurrentUser();

        final ArrayList<String> list=new ArrayList<>();

        final ArrayAdapter adapter=new ArrayAdapter<String >(this,R.layout.list_item_queue,list);
        listView.setAdapter(adapter);


            DatabaseReference reference1= FirebaseDatabase.getInstance().getReference().child("Vendor").child(user.getUid());
        reference1.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                    shopname=dataSnapshot.child("shopname").getValue().toString();
                    category=dataSnapshot.child("category").getValue().toString();
                    // name=dataSnapshot.child("name").getValue().toString();

                   // Toast.makeText(getApplicationContext(), shopname+": -"+category, Toast.LENGTH_LONG).show();
                    DatabaseReference reference3= FirebaseDatabase.getInstance().getReference().child("Queues").child(category).child(shopname);
                    reference3.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            list.clear();

                            for(DataSnapshot snapshot: dataSnapshot.getChildren()){

                                String temp1=snapshot.getValue().toString();
                                String temp2=snapshot.getKey().toString();
                                InformationQueue info1=new InformationQueue(temp2, temp1);
                                DatabaseReference namereference= FirebaseDatabase.getInstance().getReference().child("Customers");
                                Query query = namereference.getRef().child(info1.getUid()).child("name");
                            //    Toast.makeText(getApplicationContext(),query.toString().toString(),Toast.LENGTH_SHORT).show();
                                query.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                     //   Toast.makeText(getApplicationContext(),dataSnapshot.getValue().toString(),Toast.LENGTH_LONG).show();
                                        String txt=info1.getNumber()+": "+dataSnapshot.getValue();
                                        list.add(txt);
                                        adapter.notifyDataSetChanged();
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });
                                String txt=info1.getNumber()+": "+info1.getUid();

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

       /* String shopname= FirebaseDatabase.getInstance().getReference().child("Vendor").child(currentUser).getKey();
       DatabaseReference reference2= FirebaseDatabase.getInstance().getReference().child("Vendor").child(currentUser);
        reference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    category = snapshot.getValue().toString();
                }
                System.out.println(category+" category");

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/








    }
    public void Back(View view)
    {
        this.finish();
    }

}