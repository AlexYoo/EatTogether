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
                case R.id.navigation_eatTogether:
//                    mTextMessage.setText(R.string.title_eatTogether);
                    switchToHomeFragment();
                    return true;
                case R.id.navigation_goodRestaurant:
//                    mTextMessage.setText(R.string.title_goodRestaurant);
                    switchToGoodRestFragment();
                    return true;
                case R.id.navigation_history:
//                    mTextMessage.setText(R.string.title_history);
                    switchToHistoryFragment();
                    return true;
                case R.id.navigation_chatting:
//                    mTextMessage.setText(R.string.title_chatting);
                    switchToChattingFragment();
                    return true;
                case R.id.navigation_settings:
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

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
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
