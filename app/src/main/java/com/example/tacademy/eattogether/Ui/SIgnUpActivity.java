package com.example.tacademy.eattogether.Ui;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.tacademy.eattogether.R;
import com.example.tacademy.eattogether.signUpFragment.SNSLoginFragment;

public class SIgnUpActivity extends AppCompatActivity {

    SNSLoginFragment snsLoginFragment;
    ViewPager tutorialViewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        tutorialViewPager = (ViewPager) findViewById(R.id.tutorialViewPager);

    }




}