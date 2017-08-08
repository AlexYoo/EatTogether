package com.example.tacademy.eattogether.Maps;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * 커피 전문점 정보를 요청하는 API 통신 메소드 정의
 */
public interface CoffeeFactoryIm
{
    // 커피전문점 모든 정보 요청
    // http://13.124.108.134:3000/all
    @GET("all")
    //Call<List<User>> login(@Query("userEmail") String userEmail, @Query("userPwd") String userPwd);
    Call<ResCoffeeStoresModel> coffeeAll();

    // 커피전문점 특정 업체 정보 요청
    // http://13.124.108.134:3000/coffee?t=COFFEEBEAN
    @GET("coffee")
    Call<ResCoffeeStoresModel> coffeeBrand(@Query("t") String t);

    @POST("coffee")
    Call<ResCoffeeStoresModel> coffeeDist(@Body DistModel distModel);

}









