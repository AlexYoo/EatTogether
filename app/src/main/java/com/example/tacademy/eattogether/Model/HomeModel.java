package com.example.tacademy.eattogether.Model;

/**
 * Created by Tacademy on 2017-08-04.
 */

public class HomeModel {
    public String viewName;
    public String viewFoodType;
    public int viewPeopleCnt;
    public String viewNotice;
    public int image;



    public HomeModel() {
    }

    public HomeModel(String viewName, String viewFoodType, int viewPeopleCnt, String viewNotice, int image) {
        this.viewName = viewName;
        this.viewFoodType = viewFoodType;
        this.viewPeopleCnt = viewPeopleCnt;
        this.viewNotice = viewNotice;
        this.image = image;
    }

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public String getViewFoodType() {
        return viewFoodType;
    }

    public void setViewFoodType(String viewFoodType) {
        this.viewFoodType = viewFoodType;
    }

    public int getViewPeopleCnt() {
        return viewPeopleCnt;
    }

    public void setViewPeopleCnt(int viewPeopleCnt) {
        this.viewPeopleCnt = viewPeopleCnt;
    }

    public String getViewNotice() {
        return viewNotice;
    }

    public void setViewNotice(String viewNotice) {
        this.viewNotice = viewNotice;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
