package com.example.tacademy.eattogether.mainFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tacademy.eattogether.R;
import com.example.tacademy.eattogether.S;

import cn.pedant.SweetAlert.SweetAlertDialog;
import de.hdodenhof.circleimageview.CircleImageView;

public class SettingsFragment extends Fragment {

    CircleImageView settingsProfileImage;

    public SettingsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_settings, container, false);
        settingsProfileImage = view.findViewById(R.id.settingsProfileImage);

        settingsProfileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                S.getInstance().showPopup3(getContext(), "프로필 이미지", "이 버튼을 누르면 갤러리로 넘어갑니다.",
                        "갤러리 열기", new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                sweetAlertDialog.dismissWithAnimation();
                                //이미지 설정 갤러리로 넘어가기
                            }
                        }, "끄기", new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                sweetAlertDialog.dismissWithAnimation();
                            }
                        });
            }
        });

        return view;



    }



}
