package com.example.tacademy.eattogether.Ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.tacademy.eattogether.R;
import com.example.tacademy.eattogether.mainFragment.ChattingFragment;
import com.example.tacademy.eattogether.mainFragment.GoodRestFragment;
import com.example.tacademy.eattogether.mainFragment.HistoryFragment;
import com.example.tacademy.eattogether.mainFragment.HomeFragment;
import com.example.tacademy.eattogether.mainFragment.SettingsFragment;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    FragmentManager fragmentManager = getSupportFragmentManager();

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_eatTogether: //메인 홈 화면
//                    mTextMessage.setText(R.string.title_eatTogether);
                    switchToHomeFragment();
                    return true;
                case R.id.navigation_goodRestaurant:
//                    mTextMessage.setText(R.string.title_goodRestaurant); //메인 맛집 화면
                    switchToGoodRestFragment();
                    return true;
                case R.id.navigation_history: //메인 히스토리 화면
//                    mTextMessage.setText(R.string.title_history);
                    switchToHistoryFragment();
                    return true;
                case R.id.navigation_chatting: //메인 채팅방 화면
//                    mTextMessage.setText(R.string.title_chatting);
                    switchToChattingFragment();
                    return true;
                case R.id.navigation_settings: //메인 설정 화면
//                    mTextMessage.setText(R.string.title_settings);
                    switchToSettingsFragment();
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation); //네비게이션 아이템 끌어오기
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener); //네비게이션 아이템 리스너 설정
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, tempActivity.class));
            }
        });

        FloatingActionButton fab2 = (FloatingActionButton) findViewById(R.id.fab2);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, tempActivity.class));
            }
        });
    }

    public void switchToHomeFragment(){ //홈 화면으로 이동
        
        fragmentManager.beginTransaction().replace(R.id.content, new HomeFragment()).commit();
    }

    public void switchToGoodRestFragment(){ //맛집 화면으로 이동
        
        fragmentManager.beginTransaction().replace(R.id.content, new GoodRestFragment()).commit();
    }

    public void switchToHistoryFragment(){ //히스토리 화면으로 이동
        
        fragmentManager.beginTransaction().replace(R.id.content, new HistoryFragment()).commit();
    }

    public void switchToSettingsFragment(){ //설정 화면으로 이동
        
        fragmentManager.beginTransaction().replace(R.id.content, new SettingsFragment()).commit();
    }

    public void switchToChattingFragment(){ //채팅 화면으로 이동
        
        fragmentManager.beginTransaction().replace(R.id.content, new ChattingFragment()).commit();
    }

}
