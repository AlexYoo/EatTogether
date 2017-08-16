package com.manuel.tacademy.eattogether.home;


/*
* HomeFragment에서 새 글 작성을 누르면 나오는 액티비티
* */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.manuel.tacademy.eattogether.R;
import com.manuel.tacademy.eattogether.sign_up.profile.ScheduleActivity;

public class NewPostActivity extends AppCompatActivity {
    Button selectRegion, selectDate, selectFood, minusButton, plusButton; //지역, 날짜, 음식 선택
    TextView numOfPeople; //사람 수
    int peopleCnt;
    LinearLayout peopleLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);

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
                startActivity(new Intent(NewPostActivity.this, RegionActivity.class));
            }
        });

        selectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NewPostActivity.this, ScheduleActivity.class));
            }
        });

        selectFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NewPostActivity.this, FoodTypeActivity.class));
            }
        });

        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(peopleCnt < 3) return;
                numOfPeople.setText(String.valueOf(--peopleCnt));
            }

        });

        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(peopleCnt > 7) return;
                numOfPeople.setText(String.valueOf(++peopleCnt));
            }
        });


    }

}
