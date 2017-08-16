package com.manuel.tacademy.eattogether.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.manuel.tacademy.eattogether.R;
import com.manuel.tacademy.eattogether.chatting.ChattingFragment;
import com.manuel.tacademy.eattogether.good_restaurant.GoodRestFragment;
import com.manuel.tacademy.eattogether.history.HistoryFragment;
import com.manuel.tacademy.eattogether.home.HomeFragment;
import com.manuel.tacademy.eattogether.settings.SettingsFragment;
import com.manuel.tacademy.eattogether.util.BackPressCloseHandler;

public class MainActivity extends AppCompatActivity {

    FragmentManager fragmentManager = getSupportFragmentManager();

    BackPressCloseHandler backPressCloseHandler;
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
    public void onBackPressed() {
        backPressCloseHandler.onBackPressed();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        switchToHomeFragment();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation); //네비게이션 아이템 끌어오기
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener); //네비게이션 아이템 리스너 설정

        backPressCloseHandler = new BackPressCloseHandler(this);
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
