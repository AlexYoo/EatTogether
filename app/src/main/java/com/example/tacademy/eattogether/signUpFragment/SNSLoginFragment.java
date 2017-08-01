package com.example.tacademy.eattogether.signUpFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.tacademy.eattogether.R;
import com.example.tacademy.eattogether.S;
import com.example.tacademy.eattogether.Ui.ProfileActivity;

import cn.pedant.SweetAlert.SweetAlertDialog;


public class SNSLoginFragment extends Fragment {

    Button facebookLoginButton;
    Button kakaoLoginButton;
    Button naverLoginButton;

    public SNSLoginFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_snslogin, container, false);
        facebookLoginButton = view.findViewById(R.id.facebookLoginButton);
        kakaoLoginButton    = view.findViewById(R.id.kakaoLoginButton);
        naverLoginButton    = view.findViewById(R.id.naverLoginButton);

        facebookLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                S.getInstance().showPopup3(getContext(), "페북 로그인", "페북으로 로그인 합니다.",
                        "다음화면", new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                sweetAlertDialog.dismissWithAnimation();
                                startActivity(new Intent(getContext(), ProfileActivity.class));
                            }
                        }, "끄기", new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                sweetAlertDialog.dismissWithAnimation();
                            }
                        });
            }
        });

        kakaoLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                S.getInstance().showPopup3(getContext(), "카카오 로그인", "카카오로 로그인 합니다.",
                        "다음화면", new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                sweetAlertDialog.dismissWithAnimation();
                                startActivity(new Intent(getContext(), ProfileActivity.class));
                            }
                        }, "끄기", new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                sweetAlertDialog.dismissWithAnimation();
                            }
                        });
            }
        });

        naverLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                S.getInstance().showPopup3(getContext(), "네이버 로그인", "네이버로 로그인 합니다.",
                        "다음화면", new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                sweetAlertDialog.dismissWithAnimation();
                                startActivity(new Intent(getContext(), ProfileActivity.class));
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
