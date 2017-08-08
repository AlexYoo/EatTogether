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



public class temp2Activity extends AppCompatActivity {

    Button temp2SelectRegion, temp2SelectDate, temp2SelectFood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp2);

        temp2SelectRegion = (Button) findViewById(R.id.temp2SelectRegion);
        temp2SelectDate = (Button) findViewById(R.id.temp2SelectDate);
        temp2SelectFood = (Button) findViewById(R.id.temp2SelectFood);

        temp2SelectRegion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(temp2Activity.this, RegionActivity.class));
            }
        });

        temp2SelectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(temp2Activity.this, ScheduleActivity.class));
            }
        });

        temp2SelectFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(temp2Activity.this, FoodTypeActivity.class));
            }
        });

    }
}
