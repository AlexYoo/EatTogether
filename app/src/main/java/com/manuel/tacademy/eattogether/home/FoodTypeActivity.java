package com.manuel.tacademy.eattogether.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.manuel.tacademy.eattogether.R;
import com.manuel.tacademy.eattogether.util.S;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class FoodTypeActivity extends AppCompatActivity {

    public static final String ALL = "all";
    public static final String KOREAN = "korean";
    public static final String WEST = "west";
    public static final String CHINESE = "chinese";
    public static final String JAPANESE = "japanese";
    public static final String ALCOHOL = "alcohol";
    public static final String CAFE = "cafe";
    public static final String MEAT = "meat";
    public static final String VEGETABLE = "vegetable";
    public static final String NOODLE = "noodle";
    public static final String FISH = "fish";
    public static final String BREAD = "bread";

    Button foodAll, foodKorean, foodWest, foodChinese,
            foodJapanese, foodAlcohol, foodCafe, foodMeat,
            foodVegetable, foodNoodle, foodFish, foodBread,
            foodtypeSaveBtn;
    Intent foodTypeData;
    int selectCnt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_type);

        foodTypeData = new Intent();

        foodAll         = (Button)findViewById(R.id.foodAll);
        foodKorean      = (Button)findViewById(R.id.foodKorean);
        foodWest        = (Button)findViewById(R.id.foodWest);
        foodChinese     = (Button)findViewById(R.id.foodChinese);
        foodJapanese    = (Button)findViewById(R.id.foodJapanese);
        foodAlcohol     = (Button)findViewById(R.id.foodAlcohol);
        foodCafe        = (Button)findViewById(R.id.foodCafe);
        foodMeat        = (Button)findViewById(R.id.foodMeat);
        foodVegetable   = (Button)findViewById(R.id.foodVegetable);
        foodNoodle      = (Button)findViewById(R.id.foodNoodle);
        foodFish        = (Button)findViewById(R.id.foodFish);
        foodBread       = (Button)findViewById(R.id.foodBread);
        foodtypeSaveBtn = (Button)findViewById(R.id.foodtypeSaveBtn);

        foodAll.setOnClickListener(new View.OnClickListener() {
            boolean isClicked = false;
            @Override
            public void onClick(View view) {
                if(!isClicked) {
                    foodAll.setBackgroundResource(R.drawable.rounded_clicked);
                    isClicked = true;
                }else{
                    foodAll.setBackgroundResource(R.drawable.rounded);
                    isClicked = false;
                }
            }
        });

        foodKorean.setOnClickListener(new View.OnClickListener() {
            boolean isClicked = false;
            @Override
            public void onClick(View view) {
                if(!isClicked) {
                    foodKorean.setBackgroundResource(R.drawable.rounded_clicked);
                    isClicked = true;
                }else{
                    foodKorean.setBackgroundResource(R.drawable.rounded);
                    isClicked = false;
                }
            }
        });

        foodWest.setOnClickListener(new View.OnClickListener() {
            boolean isClicked = false;
            @Override
            public void onClick(View view) {
                if(!isClicked) {
                    foodWest.setBackgroundResource(R.drawable.rounded_clicked);
                    isClicked = true;
                }else{
                    foodWest.setBackgroundResource(R.drawable.rounded);
                    isClicked = false;
                }
            }
        });

        foodChinese.setOnClickListener(new View.OnClickListener() {
            boolean isClicked = false;
            @Override
            public void onClick(View view) {
                if(!isClicked) {
                    foodChinese.setBackgroundResource(R.drawable.rounded_clicked);
                    isClicked = true;
                }else{
                    foodChinese.setBackgroundResource(R.drawable.rounded);
                    isClicked = false;
                }
            }
        });

        foodJapanese.setOnClickListener(new View.OnClickListener() {
            boolean isClicked = false;
            @Override
            public void onClick(View view) {
                if(!isClicked) {
                    foodJapanese.setBackgroundResource(R.drawable.rounded_clicked);
                    isClicked = true;
                }else{
                    foodJapanese.setBackgroundResource(R.drawable.rounded);
                    isClicked = false;
                }
            }
        });

        foodAlcohol.setOnClickListener(new View.OnClickListener() {
            boolean isClicked = false;
            @Override
            public void onClick(View view) {
                if(!isClicked) {
                    foodAlcohol.setBackgroundResource(R.drawable.rounded_clicked);
                    isClicked = true;
                }else{
                    foodAlcohol.setBackgroundResource(R.drawable.rounded);
                    isClicked = false;
                }
            }
        });

        foodCafe.setOnClickListener(new View.OnClickListener() {
            boolean isClicked = false;
            @Override
            public void onClick(View view) {
                if(!isClicked) {
                    foodCafe.setBackgroundResource(R.drawable.rounded_clicked);
                    isClicked = true;
                }else{
                    foodCafe.setBackgroundResource(R.drawable.rounded);
                    isClicked = false;
                }
            }
        });

        foodMeat.setOnClickListener(new View.OnClickListener() {
            boolean isClicked = false;
            @Override
            public void onClick(View view) {
                if(!isClicked) {
                    foodMeat.setBackgroundResource(R.drawable.rounded_clicked);
                    isClicked = true;
                }else{
                    foodMeat.setBackgroundResource(R.drawable.rounded);
                    isClicked = false;
                }
            }
        });

        foodVegetable.setOnClickListener(new View.OnClickListener() {
            boolean isClicked = false;
            @Override
            public void onClick(View view) {
                if(!isClicked) {
                    foodVegetable.setBackgroundResource(R.drawable.rounded_clicked);
                    isClicked = true;
                }else{
                    foodVegetable.setBackgroundResource(R.drawable.rounded);
                    isClicked = false;
                }
            }
        });

        foodNoodle.setOnClickListener(new View.OnClickListener() {
            boolean isClicked = false;
            @Override
            public void onClick(View view) {
                if(!isClicked) {
                    foodNoodle.setBackgroundResource(R.drawable.rounded_clicked);
                    isClicked = true;
                }else{
                    foodNoodle.setBackgroundResource(R.drawable.rounded);
                    isClicked = false;
                }
            }
        });

        foodFish.setOnClickListener(new View.OnClickListener() {
            boolean isClicked = false;
            @Override
            public void onClick(View view) {
                if(!isClicked) {
                    foodFish.setBackgroundResource(R.drawable.rounded_clicked);
                    isClicked = true;
                }else{
                    foodFish.setBackgroundResource(R.drawable.rounded);
                    isClicked = false;
                }
            }
        });

        foodBread.setOnClickListener(new View.OnClickListener() {
            boolean isClicked = false;
            @Override
            public void onClick(View view) {
                if(!isClicked) {
                    foodBread.setBackgroundResource(R.drawable.rounded_clicked);
                    isClicked = true;
                }else{
                    foodBread.setBackgroundResource(R.drawable.rounded);
                    isClicked = false;
                }
            }
        });

        foodtypeSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                S.getInstance().showPopup3(FoodTypeActivity.this, "저장", "음식 종류를 저장하시겠습니까?",
                        "예", new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                sweetAlertDialog.dismissWithAnimation();
                                //putextra
                                finish();
                            }
                        }, "아니오", new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                sweetAlertDialog.dismissWithAnimation();
                            }
                        });
            }
        });
    }
}
