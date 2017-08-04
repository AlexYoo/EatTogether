package com.example.tacademy.eattogether.Ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.tacademy.eattogether.R;
import com.example.tacademy.eattogether.viewPagerFragment.Tuto1Fragment;
import com.example.tacademy.eattogether.viewPagerFragment.Tuto2Fragment;
import com.example.tacademy.eattogether.viewPagerFragment.Tuto3Fragment;
import com.example.tacademy.eattogether.viewPagerFragment.Tuto4Fragment;

public class SignUpActivity extends AppCompatActivity {

    ViewPager tutorialViewPager;
    static final int NUM_OF_FRAGMENTS = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        tutorialViewPager = (ViewPager) findViewById(R.id.tutorialViewPager);

        tutorialViewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        tutorialViewPager.setCurrentItem(0);


    }
    View.OnClickListener movePageListener = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            int tag = (int)view.getTag();
            tutorialViewPager.setCurrentItem(tag);
        }
    };
    private class MyPagerAdapter extends FragmentStatePagerAdapter{
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return new Tuto1Fragment();
                case 1:
                    return new Tuto2Fragment();
                case 2:
                    return new Tuto3Fragment();
                case 3:
                    return new Tuto4Fragment();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return NUM_OF_FRAGMENTS;
        }
    }

}
