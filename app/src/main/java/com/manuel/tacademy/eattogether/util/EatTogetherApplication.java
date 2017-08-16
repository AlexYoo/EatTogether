package com.manuel.tacademy.eattogether.util;

import android.app.Application;

import com.miguelbcr.ui.rx_paparazzo2.RxPaparazzo;

/**
 * Created by Tacademy on 2017-08-09.
 */

public class EatTogetherApplication extends Application {
    @Override public void onCreate() {
        super.onCreate();
        // 파파라조 라이브러리 등록
        RxPaparazzo.register(this);
    }
}