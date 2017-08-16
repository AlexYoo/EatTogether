package com.manuel.tacademy.eattogether.chatting;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.SlidingDrawer;

import com.manuel.tacademy.eattogether.R;

public class ChattingJoinActivity extends AppCompatActivity {


    Button chattingPhotoBtn, chattingGoodrestBtn, chattingCompleteBtn, chattingPlusBtn, chattingSendBtn;
    RelativeLayout chattingBottomSlidingDrawer;
    SlidingDrawer slidingDrawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatting_join);
        Animation slide_down = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);
        Animation slide_up = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);




    }


}
