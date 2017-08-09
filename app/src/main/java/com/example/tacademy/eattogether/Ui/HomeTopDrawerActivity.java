package com.example.tacademy.eattogether.Ui;

/*
* HomeFragment에서 상단 Drawer를 누르면 나오는 액션
* */


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.tacademy.eattogether.R;



public class HomeTopDrawerActivity extends AppCompatActivity {

    Button goodrestDrawerSelectRegion, 
            goodrestDrawerSelectDate,
            goodrestDrawerSelectFood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_top_drawer);

        goodrestDrawerSelectRegion = (Button) findViewById(R.id.temp2SelectRegion);
        goodrestDrawerSelectDate = (Button) findViewById(R.id.temp2SelectDate);
        goodrestDrawerSelectFood = (Button) findViewById(R.id.temp2SelectFood);

        goodrestDrawerSelectRegion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeTopDrawerActivity.this, RegionActivity.class));
            }
        });

        goodrestDrawerSelectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeTopDrawerActivity.this, ScheduleActivity.class));
            }
        });

        goodrestDrawerSelectFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeTopDrawerActivity.this, FoodTypeActivity.class));
            }
        });

    }
}
