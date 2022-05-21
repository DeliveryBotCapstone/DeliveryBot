package com.google.sample.cloudvision;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


//import androidx.appcompat.app.AppCompatActivity;

public class after_checking extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.after_delivery);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }

    public void button4(View view){
        Intent intent = new Intent(getApplicationContext(),checking_photo.class);
        startActivity(intent);
    }


}
