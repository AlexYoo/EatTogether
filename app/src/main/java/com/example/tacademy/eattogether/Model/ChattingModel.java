package com.example.tacademy.eattogether.Model;

/**
 * Created by Tacademy on 2017-08-07.
 */

public class ChattingModel {
    String ownerName;
    String foodType;
    int peopleCnt;
    String comment;

    public ChattingModel() {
    }

    public ChattingModel(String ownerName, String foodType, int peopleCnt, String comment) {
        this.ownerName = ownerName;
        this.foodType = foodType;
        this.peopleCnt = peopleCnt;
        this.comment = comment;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
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
