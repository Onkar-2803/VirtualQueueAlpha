package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ShopCategories extends AppCompatActivity {
    public static final  String EXTRA_TEXT="com.example.myapplication.EXTRA_TEXT";
    public  static  final String EXTRA_NUMBER="com.example.myapplication.EXTRA_NUMBER";
    Button b1,b2,b3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_categories);
        b1=findViewById(R.id.grocery);
        b2=findViewById(R.id.Barber);
        b3=findViewById(R.id.Pharma);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ShopCategories.this,ShopListActivity.class);
                intent.putExtra("temp","grocery");
                startActivity(intent);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ShopCategories.this,ShopListActivity.class);
                intent.putExtra("temp","barber");

                startActivity(intent);

            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ShopCategories.this,ShopListActivity.class);
                intent.putExtra("temp","pharma");
                startActivity(intent);
            }
        });
    }
}