package com.example.tacademy.eattogether.history;

/**
 * Created by Tacademy on 2017-08-07.
 */

public class HistoryModel {
    public String name;
    public String foodType;
    public String peopleCnt;
    public String comment;

    public HistoryModel() {
    }

    public HistoryModel(String name, String foodType, String peopleCnt, String comment) {
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

    public String getPeopleCnt() {
        return peopleCnt;
    }

    public void setPeopleCnt(String peopleCnt) {
        this.peopleCnt = peopleCnt;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
