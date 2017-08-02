package com.example.tacademy.eattogether.Ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.tacademy.eattogether.mainFragment.ChattingFragment;
import com.example.tacademy.eattogether.mainFragment.GoodRestFragment;
import com.example.tacademy.eattogether.mainFragment.HistoryFragment;
import com.example.tacademy.eattogether.mainFragment.HomeFragment;
import com.example.tacademy.eattogether.mainFragment.SettingsFragment;
import com.example.tacademy.eattogether.R;

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

        mTextMessage = (TextView) findViewById(R.id.message); //테스트용 텍스트 설정
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation); //네비게이션 아이템 끌어오기
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener); //네비게이션 아이템 리스너 설정
        getSupportActionBar().hide(); //액션바 숨기기
    }

    public void switchToHomeFragment(){
        
        fragmentManager.beginTransaction().replace(R.id.content, new HomeFragment()).commit();
    }

    public void switchToGoodRestFragment(){
        
        fragmentManager.beginTransaction().replace(R.id.content, new GoodRestFragment()).commit();
    }

    public void switchToHistoryFragment(){
        
        fragmentManager.beginTransaction().replace(R.id.content, new HistoryFragment()).commit();
    }

    public void switchToSettingsFragment(){
        
        fragmentManager.beginTransaction().replace(R.id.content, new SettingsFragment()).commit();
    }

    public void switchToChattingFragment(){
        
        fragmentManager.beginTransaction().replace(R.id.content, new ChattingFragment()).commit();
    }

}
