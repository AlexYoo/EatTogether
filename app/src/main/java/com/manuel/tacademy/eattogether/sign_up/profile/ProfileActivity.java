package com.manuel.tacademy.eattogether.sign_up.profile;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatDrawableManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.manuel.tacademy.eattogether.R;
import com.manuel.tacademy.eattogether.ui.MainActivity;
import com.manuel.tacademy.eattogether.util.S;
import com.miguelbcr.ui.rx_paparazzo2.RxPaparazzo;
import com.miguelbcr.ui.rx_paparazzo2.entities.FileData;
import com.squareup.picasso.Picasso;
import com.yalantis.ucrop.UCrop;

import java.io.File;

import cn.pedant.SweetAlert.SweetAlertDialog;
import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ProfileActivity extends AppCompatActivity {

    public final static int REQUEST_MY_ACTIVITY = 0;

    CircleImageView profileImage;
    EditText profileName, profileIntroduction;
    TextView profileSex;
    TextView profileBirthday;
    int myYear, myMonth, myDay;
    TextView YMD;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_MY_ACTIVITY && resultCode == Activity.RESULT_OK){
            myYear  = data.getIntExtra(DatePickerActivity.YEAR, 1980);
            myMonth = data.getIntExtra(DatePickerActivity.MONTH, 01)+1;
            myDay   = data.getIntExtra(DatePickerActivity.DAY, 01);
            YMD.setText(myYear + " - " + myMonth + " - " + myDay);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profileImage        = (CircleImageView) findViewById(R.id.profileImage);
        profileName         = (EditText) findViewById(R.id.profileName);
        profileIntroduction = (EditText) findViewById(R.id.profileIntroduction);
        profileBirthday     = (TextView) findViewById(R.id.profileBirthday);
        profileSex          = (TextView) findViewById(R.id.profileSex);
        YMD                 = (TextView) findViewById(R.id.YMD);

        initUI();
    }

    public void initUI() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.saveButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                S.getInstance().showPopup3(ProfileActivity.this, "저장", "이 버튼을 누르면 저장합니다.",
                        "다음화면", new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                sweetAlertDialog.dismissWithAnimation();
                                startActivity(new Intent(ProfileActivity.this, MainActivity.class));
                            }
                        }, "끄기", new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                sweetAlertDialog.dismissWithAnimation();
                            }
                        });
            }
        });

        //메인 액티비티로 갈때 서버에다가 프로필 정보 넘겨주기기

        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                S.getInstance().showPopup3(ProfileActivity.this,
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

        profileBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                S.getInstance().showPopup(ProfileActivity.this, "생년월일", "여기서 달력을 띄웁니다.", 2);
                Intent intent = new Intent(getBaseContext(), DatePickerActivity.class);
                startActivityForResult(intent, REQUEST_MY_ACTIVITY);
            }
        });
    }



    public void onPhoto(View view){
        UCrop.Options options = new UCrop.Options();
        options.setToolbarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));

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
        options.setToolbarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));

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
            Picasso.with(profileImage.getContext())
                    .load(file)
                    .error(R.mipmap.ic_launcher_round)
                    .into(profileImage);
        } else {
            Drawable drawable = AppCompatDrawableManager.get().getDrawable(profileImage.getContext(), R.mipmap.ic_launcher_round);
            profileImage.setImageDrawable(drawable);
        }
    }

}
