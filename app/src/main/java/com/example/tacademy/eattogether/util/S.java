package com.example.tacademy.eattogether.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.location.Address;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import com.example.tacademy.eattogether.Maps.GeoPoint;
import com.example.tacademy.eattogether.Maps.GeoTrans;
import com.squareup.otto.Bus;

import java.util.UUID;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by Tacademy on 2017-08-01.
 */

public class S {
    private static final S ourInstance = new S();

    public static S getInstance() {
        return ourInstance;
    }

    private S() {
    }

    final String TAG = "T";

//=========================================================================

    public void log(String msg){
        Log.i(TAG, msg);
    }

//=========================================================================

    //영속 저장
    final String SAVE_TAG = "ref";
    public void setString(Context context, String key, String value){
        SharedPreferences.Editor editor = context.getSharedPreferences
                (SAVE_TAG, 0).edit();
        editor.putString(key, value);
        editor.commit(); //밑으로 저장
    }
    public String getString(Context context, String key){
        return context.getSharedPreferences(SAVE_TAG, 0).getString(key,"");
    }

    //=========================================================================
    public void setInt(Context context, String key, int value){
        SharedPreferences.Editor editor = context.getSharedPreferences
                (SAVE_TAG, 0).edit();
        editor.putInt(key, value);
        editor.commit(); //밑으로 저장
    }
    public int getInt(Context context, String key){
        return context.getSharedPreferences(SAVE_TAG, 0).getInt(key,0);
    }

//=========================================================================

    public void setBoolean(Context context, String key, boolean value){
        SharedPreferences.Editor editor = context.getSharedPreferences
                (SAVE_TAG, 0).edit();
        editor.putBoolean(key, value);
        editor.commit(); //밑으로 저장
    }
    public boolean getBoolean(Context context, String key){
        return context.getSharedPreferences(SAVE_TAG, 0).getBoolean
                (key,false);
    }

    //==============================================================================================
    //동적 퍼미션 체크
    public boolean isDynamicPermissionCheck(){
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ? true : false ;
    }
    //==============================================================================================
    //전번 획득
    public String getPhoneNumber(Context context){
        try {
            //1.폰서비스 획득
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            //2.전번 획득
            String number = tm.getLine1Number();
            //3.예외 처리(usim이 없는 경우)
            if(number == null){
                Toast.makeText(context, "미개통 단말기는 사용할 수 없습니다.", Toast.LENGTH_LONG).show();
                return "";
            }else{
                //4.반납
                if(number.indexOf("+82") >=0 ){
                    return number.replace("+82","0");
                }else{
                    return number;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
    //==============================================================================================
    //uuid(사용자 고유 기기 정보) 획득 -> 동일 기종으로 가입을 했다면 앱을 삭제 후 재설치해도 별도 가입없이 처리
    public String getUUID(Context context){
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String tmDevice     = ""+tm.getDeviceId();
        String tmSerial     = ""+tm.getSimSerialNumber();
        String androidId    = ""+android.provider.Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ANDROID_ID);

        UUID uuid = new UUID((long)androidId.hashCode(), ((long)tmDevice.hashCode() << 32) | tmSerial.hashCode() );

        return uuid.toString();
    }
    // =============================================================================================
    // 팝업
    // =============================================================================================
    public void showPopup3(Context context, String title, String msg,
                           String cName, SweetAlertDialog.OnSweetClickListener cEvent,
                           String oName, SweetAlertDialog.OnSweetClickListener oEvent)
    {
        new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
                .setTitleText(title)
                .setContentText(msg)
                .setConfirmText(cName)
                .setConfirmClickListener(cEvent)
                .setCancelText(oName)
                .setCancelClickListener(oEvent)
                .show();
    }
    public SweetAlertDialog showLoading(Context context)
    {
        return showLoading(context, "LOADING");
    }
    public SweetAlertDialog showLoading(Context context, String msg)
    {
        return showLoading(context, msg, "#A5DC86");
    }
    public SweetAlertDialog showLoading(Context context, String msg, String color)
    {
        SweetAlertDialog pDialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor(color));
        pDialog.setTitleText(msg);
        pDialog.setCancelable(false); // 백키를 눌러도 닫히지 않는다.
        pDialog.show();
        return pDialog;
    }
    public void showSimplePopup(Context context, String title, String msg, int type)
    {
        new SweetAlertDialog(context, type)
                .setTitleText(title)
                .setContentText(msg)
                .show();
    }
    public SweetAlertDialog showPopup(Context context, String title, String msg, int type)
    {
        SweetAlertDialog alert = new SweetAlertDialog(context, type);
        alert.setTitleText(title).setContentText(msg).setCancelable(false);
        alert.show();
        return alert;
    }
    // =============================================================================================
    // 버스
    Bus gpsBus = new Bus();
    public Bus getGpsBus() {
        return gpsBus;
    }
    // =============================================================================================
    // Address => 시군구동 표시
    public String getTransferAddr(Address address)
    {
        if( address==null ) return "";
        return String.format("%s %s %s", address.getAdminArea(), address.getLocality(), address.getThoroughfare());
    }
    // =============================================================================================
    // 좌표 변환 -> KATEC -> GEO
    public GeoPoint transGeoToKatec(GeoPoint point)
    {
        return GeoTrans.convert(GeoTrans.KATEC, GeoTrans.GEO, point);
    }
    // =============================================================================================
    // String -> double
    public double getDouble(String src)
    {
        try {
            return Double.parseDouble(src);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
    public String changeBrand(String brand)
    {
        if( brand.equals("스타벅스") ){
            return "starbucks";
        }
        else if( brand.equals("커피빈") ){
            return "coffeebean";
        }
        return "";
    }
}
