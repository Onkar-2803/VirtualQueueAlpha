package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class ShopListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_list);
    }
    public  void logout(View view){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(),CustomerLoginActivity.class));
        finish();
    }
    public void selectShop(View view)
    {
        Intent intent = new Intent(this, ShopCategories.class);
        startActivity(intent);
    }
    public void Back(View view)
    {
        this.finish();
    }
}