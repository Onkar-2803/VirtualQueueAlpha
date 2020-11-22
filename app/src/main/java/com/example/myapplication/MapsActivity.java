package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MapsActivity extends AppCompatActivity {
    EditText etsource,etdestination;
    Button bttrack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        etsource=findViewById(R.id.et_source);
        etdestination=findViewById(R.id.et_destination);
        bttrack=findViewById(R.id.bt_track);

        bttrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ssource=etsource.getText().toString();
                String sDestination=etdestination.getText().toString().trim();
                if(ssource.equals("")&&sDestination.equals("")){
                    Toast.makeText(getApplicationContext(), "Enter Both Location", Toast.LENGTH_SHORT).show();
                }
                else{
                    DisplayTrack(ssource,sDestination);
                }
            }
        });
    }
    private void DisplayTrack(String sSource,String sDestination){
        try{
            Uri uri=Uri.parse("https://www.google.co.in/maps/dir/"+ sSource +"/"+sDestination);
            Intent intent=new Intent(Intent.ACTION_VIEW,uri);
            intent.setPackage("com.google.android.apps.maps");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        catch (ActivityNotFoundException e){
            Uri uri=Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.maps");
            Intent intent=new Intent(Intent.ACTION_VIEW,uri);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

        }
    }
}