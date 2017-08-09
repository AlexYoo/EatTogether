package com.example.tacademy.eattogether.Ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.tacademy.eattogether.R;

public class GoodRestTopDrawerActivity extends AppCompatActivity {

    Button goodrestDrawerSelectRegion,
            goodrestDrawerSelectFood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.good_rest_top_drawer);

        goodrestDrawerSelectRegion = (Button) findViewById(R.id.selectRegion);
        goodrestDrawerSelectFood = (Button) findViewById(R.id.selectFood);

        goodrestDrawerSelectRegion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GoodRestTopDrawerActivity.this, RegionActivity.class));
            }
        });

        goodrestDrawerSelectFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GoodRestTopDrawerActivity.this, FoodTypeActivity.class));
            }
        });

    }
}
