package com.e.recyclerview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView

public class FoodActivity extends AppCompatActivity {
    TextView foodName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tort);
        foodName = findViewById(R.id.foodName);

        Intent intent = getIntent();
        String tName = intent.getStringExtra("foodName");
        String foodDesc = intent.getStringExtra("foodDesc");
        String foodPrice = intent.getStringExtra("foodPrice");

        foodName.setText(tName+" "+foodDesc+" "+ foodPrice);


    }
}
