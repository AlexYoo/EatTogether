package com.example.tacademy.eattogether.viewPagerFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.tacademy.eattogether.R;

/**
 * Created by Tacademy on 2017-08-04.
 */

public class Tuto2Fragment extends Fragment {
    public Tuto2Fragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.tuto2, container, false);
        return layout;
    }
}