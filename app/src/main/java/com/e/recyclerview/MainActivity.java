package com.e.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FoodListAdapter foodListAdapter;
    ArrayList<Food> foodList;
    private RecyclerView.LayoutManager linearLayoutManager,gridLayoutManager;
    Button buttonChange;
    boolean layoutType = false ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initRecyclerItemClick();
    }
    public void initRecyclerItemClick(){
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, final int pos) {
                        Intent intent = new Intent (MainActivity.this, FoodActivity.class);
                        intent.putExtra("foodName", foodList.get(pos).getTitle());
                        intent.putExtra("foodDesc", foodList.get(pos).getDesc());
                        intent.putExtra("foodPrice", foodList.get(pos).getPrice());
                        startActivity(intent);
                        Toast.makeText(MainActivity.this, "onItemClick", Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onLongItemClick(View view, int position){
                        Toast.makeText(MainActivity.this, "onLongItemClick", Toast.LENGTH_SHORT).show();
                    }
                } )
        );
    }
    public void initViews(){
        recyclerView = findViewById(R.id.recyclerView);
        buttonChange = findViewById(R.id.buttonChange);
        foodList = new ArrayList<>();

        foodList.add(new Food(R.drawable.pizza, "Pizza", "Fast-Food", 1300));
        foodList.add(new Food(R.drawable.burger, "Burger", "Fast-Food", 700));
        foodList.add(new Food(R.drawable.doner, "Doner", "Fast-Food", 650));

        foodListAdapter = new FoodListAdapter(this, foodList);

        linearLayoutManager = new LinearLayoutManager(this);
        gridLayoutManager = new GridLayoutManager(this,2);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(foodListAdapter);

        buttonChange.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                layoutType = !layoutType;
                if(layoutType) {
                    recyclerView.setLayoutManager(gridLayoutManager);
                    foodList.remove(0);
                }else {
                    recyclerView.setLayoutManager(linearLayoutManager);
                }
                foodListAdapter.notifyDataSetChanged();
            }
        });
    }
}

