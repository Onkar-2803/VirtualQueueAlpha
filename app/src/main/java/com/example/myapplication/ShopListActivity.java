package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ShopListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_list);
    }
    public void selectShop(View view)
    {
        Intent intent = new Intent(this, CustomerGetInLineActivity.class);
        startActivity(intent);
    }
    public void Back(View view)
    {
        this.finish();
    }
}