package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
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

public class ShopListActivity extends AppCompatActivity {
    private DrawerLayout drawer;
    Toolbar toolbar;
    private ListView listView;
    private ArrayList<Information> words = new ArrayList<>();

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

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_list);
        /* Finds listview i.e binds it from xml to java code in order to use it using findviewbyid*/
        listView = findViewById(R.id.listView_xml);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        /* Catches Intent Passed from ShopCategories Activity to get which button is clicked i.e
         * either barber or grocery or pharma*/
        Intent intent = getIntent();
        String text1 = getIntent().getStringExtra("temp");


        // Pass reference of database to DataBasereference object
        DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference().child("Shops").child(text1);
        reference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    // Store the data in information class that we created so that we can pass single object to listview to display it
                    Information info = snapshot.getValue(Information.class);

                    words.add(info);
                    WordAdapter adapter = new WordAdapter(getApplicationContext(), words);
                    //String txt=info.getShopname()+ ": "+info.getAddress();
                    ListView listView = (ListView) findViewById(R.id.listView_xml);
                    listView.setAdapter(adapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                            Information info1 = (Information)adapterView.getAdapter().getItem(i);
                            Intent intent2 = new Intent(getApplicationContext(), CustomerGetInLineActivity.class);
                            intent2.putExtra("category",text1);
                            intent2.putExtra("shopname",info1.getShopname());
                            intent2.putExtra("address",info1.getAddress());
                            startActivity(intent2);
                        }
                    });

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void selectShop(View view) {
        Intent intent1 = new Intent(getApplicationContext(), CustomerGetInLineActivity.class);
        startActivity(intent1);
    }

    public void Back(View view) {
        this.finish();
    }


}

/* Create a ArrayList to store Values of Shops and pass it to arrayadapter so that it can adapt the data*/
// ArrayList<Information> words = new ArrayList<>();


/*Check whether the incoming intent is clicked on barber or pharma or grocery*/
/*

        if (text1.equals("barber")) {

            // Pass reference of database to DataBasereference object
            DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference().child("Shops").child("barber");
            reference1.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                       // Store the data in information class that we created so that we can pass single object to listview to display it
                        Information info = snapshot.getValue(Information.class);

                        words.add(info);
                        WordAdapter adapter = new WordAdapter(getApplicationContext(), words);
                        //String txt=info.getShopname()+ ": "+info.getAddress();
                        ListView listView = (ListView) findViewById(R.id.listView_xml);
                        listView.setAdapter(adapter);
                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                Intent intent2 = new Intent(getApplicationContext(), CustomerGetInLineActivity.class);
                                startActivity(intent2);
                            }
                        });

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }
        //Same like above
        if (text1.equals("pharma")) {
            DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference().child("Shops").child("pharma");
            reference1.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        // Store the data in information class that we created so that we can pass single object to listview to display it
                        Information info = snapshot.getValue(Information.class);
                        // getdata from info object and store it in txt file
                        words.add(info);
                        WordAdapter adapter = new WordAdapter(getApplicationContext(), words);
                        //String txt=info.getShopname()+ ": "+info.getAddress();
                        ListView listView = (ListView) findViewById(R.id.listView_xml);
                        listView.setAdapter(adapter);
                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                Intent intent2 = new Intent(getApplicationContext(), CustomerGetInLineActivity.class);
                                startActivity(intent2);
                            }
                        });

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }
        if (text1.equals("grocery")) {
            DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference().child("Shops").child("grocery");
            reference1.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        // Store the data in information class that we created so that we can pass single object to listview to display it
                        Information info = snapshot.getValue(Information.class);
                        // getdata from info object and store it in txt file
                        words.add(info);
                        WordAdapter adapter = new WordAdapter(getApplicationContext(), words);
                        //String txt=info.getShopname()+ ": "+info.getAddress();
                        ListView listView = (ListView) findViewById(R.id.listView_xml);
                        listView.setAdapter(adapter);
                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                Intent intent2 = new Intent(getApplicationContext(), CustomerGetInLineActivity.class);
                                startActivity(intent2);
                            }
                        });

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


        }
        */

