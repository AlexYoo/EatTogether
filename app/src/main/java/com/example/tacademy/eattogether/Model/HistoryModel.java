package com.example.tacademy.eattogether.Model;

/**
 * Created by Tacademy on 2017-08-07.
 */

public class HistoryModel {
    public String name;
    public String foodType;
    public int peopleCnt;
    public String comment;

    public HistoryModel() {
    }

    public HistoryModel(String name, String foodType, int peopleCnt, String comment) {
        this.name = name;
        this.foodType = foodType;
        this.peopleCnt = peopleCnt;
        this.comment = comment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    public int getPeopleCnt() {
        return peopleCnt;
    }

    public void setPeopleCnt(int peopleCnt) {
        this.peopleCnt = peopleCnt;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
