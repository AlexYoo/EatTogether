package com.example.tacademy.eattogether.settings;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatDrawableManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.tacademy.eattogether.R;
import com.example.tacademy.eattogether.sign_up.tutorial.SignUpActivity;
import com.example.tacademy.eattogether.util.S;
import com.miguelbcr.ui.rx_paparazzo2.RxPaparazzo;
import com.miguelbcr.ui.rx_paparazzo2.entities.FileData;
import com.squareup.picasso.Picasso;
import com.yalantis.ucrop.UCrop;

import java.io.File;

import cn.pedant.SweetAlert.SweetAlertDialog;
import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static android.app.Activity.RESULT_OK;

public class SettingsFragment extends Fragment {

    CircleImageView settingsProfileImage;
    ImageView jjim;
    Button logout;


    public SettingsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_settings, container, false);
        settingsProfileImage = view.findViewById(R.id.settingsProfileImage);
        jjim                 = view.findViewById(R.id.myFavoriteBtn);
        logout               = view.findViewById(R.id.logoutBtn);
        settingsProfileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                S.getInstance().showPopup3(getContext(),
                        "저장 방법 선택",
                        "사진을 가져올 방법을 선택하세요.",
                        "camera", new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                sweetAlertDialog.dismissWithAnimation();
                                onCamera(null);
                            }
                        }, "photo", new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                sweetAlertDialog.dismissWithAnimation();
                                onPhoto(null);
                            }
                        });

            }
        });

        jjim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                S.getInstance().showPopup3(getContext(), "찜", "이 버튼을 누르면 찜 목록으로 넘어갑니다.",
                        "다음화면", new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                sweetAlertDialog.dismissWithAnimation();
                                startActivity(new Intent(getContext(), MyFavoriteActivity.class));
                            }
                        }, "끄기", new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                sweetAlertDialog.dismissWithAnimation();
                            }
                        });
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                S.getInstance().showPopup3(getContext(), "로그아웃", "이 버튼을 누르면 로그아웃합니다.",
                        "다음화면", new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                sweetAlertDialog.dismissWithAnimation();
                                startActivity(new Intent(getContext(), SignUpActivity.class));
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


    public void onPhoto(View view){
        UCrop.Options options = new UCrop.Options();
        options.setToolbarColor(ContextCompat.getColor(getContext(), R.color.colorPrimaryDark));

        // crop(options) => 사진을 찍은후 편집 메뉴를 띠운다
        // usingCamera() => 카메라를 띠운다
        // usingGallery() => 포토 앨범을 띠운다
        RxPaparazzo.single(this)
                .crop(options)
                .usingGallery()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    // See response.resultCode() doc
                    if (response.resultCode() != RESULT_OK) {
                        return;
                    }
                    bind(response.data());
                });
    }


    public void onCamera(View view){
        UCrop.Options options = new UCrop.Options();
        options.setToolbarColor(ContextCompat.getColor(getContext(), R.color.colorPrimaryDark));

        // crop(options) => 사진을 찍은후 편집 메뉴를 띠운다
        // usingCamera() => 카메라를 띠운다
        // usingGallery() => 포토 앨범을 띠운다
        RxPaparazzo.single(this)
                .crop(options)
                .usingCamera()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    // See response.resultCode() doc
                    if (response.resultCode() != RESULT_OK) {
                        return;
                    }
                    bind(response.data());
                });
    }


    void bind(FileData fileData) {
        // 이미지를 서버로 전송 =>
        File file = fileData.getFile();
        if (file != null && file.exists()) {
            Picasso.with(settingsProfileImage.getContext())
                    .load(file)
                    .error(R.mipmap.ic_launcher_round)
                    .into(settingsProfileImage);
        } else {
            Drawable drawable = AppCompatDrawableManager.get().getDrawable(settingsProfileImage.getContext(), R.mipmap.ic_launcher_round);
            settingsProfileImage.setImageDrawable(drawable);
        }
    }



}
