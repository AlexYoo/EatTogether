package com.manuel.tacademy.eattogether.settings;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.manuel.tacademy.eattogether.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteChatFragment extends Fragment {


    public FavoriteChatFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorite_chat, container, false);
        return view;
    }

}
