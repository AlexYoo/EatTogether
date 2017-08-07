package com.example.tacademy.eattogether.Ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.tacademy.eattogether.R;
import com.example.tacademy.eattogether.S;

import cn.pedant.SweetAlert.SweetAlertDialog;
import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {

    CircleImageView profileImage;
    EditText profileName, profileIntroduction;
    TextView profileSex;
    Button profileBirthday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profileImage        = (CircleImageView) findViewById(R.id.profileImage);
        profileName         = (EditText) findViewById(R.id.profileName);
        profileIntroduction = (EditText) findViewById(R.id.profileIntroduction);
        profileBirthday     = (Button) findViewById(R.id.profileBirthday);
        profileSex          = (TextView) findViewById(R.id.profileSex);

        initUI();
    }

    public void initUI(){
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

       profileImage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                S.getInstance().showPopup(ProfileActivity.this, "프로필 사진", "여기서 프로필을 변경합니다.", 2);
            }
        });

        profileBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                S.getInstance().showPopup(ProfileActivity.this, "생년월일", "여기서 달력을 띄웁니다.", 2);
                startActivity(new Intent(getBaseContext(), DatePickerActivity.class));
            }
        });

    }

}
