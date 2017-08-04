package com.example.tacademy.eattogether.Ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tacademy.eattogether.R;

public class tempActivity extends AppCompatActivity {
    Button selectRegion, selectDate, selectFood, minusButton, plusButton; //지역, 날짜, 음식 선택
    TextView numOfPeople; //사람 수
    int peopleCnt;
    LinearLayout peopleLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp);

        peopleCnt    = 2;
        selectRegion = (Button) findViewById(R.id.selectRegion);
        selectDate   = (Button) findViewById(R.id.selectDate);
        selectFood   = (Button) findViewById(R.id.selectFood);
        minusButton  = (Button) findViewById(R.id.minusButton);
        plusButton   = (Button) findViewById(R.id.plusButton);
        numOfPeople  = (TextView) findViewById(R.id.NumOfPeople);

        selectRegion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(tempActivity.this, RegionActivity.class));
            }
        });

        selectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(tempActivity.this, ScheduleActivity.class));
            }
        });

        selectFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(tempActivity.this, FoodTypeActivity.class));
            }
        });

        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    numOfPeople.setText(String.valueOf(--peopleCnt));
            }

        });

        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    numOfPeople.setText(String.valueOf(++peopleCnt));
            }
        });


    }

}
