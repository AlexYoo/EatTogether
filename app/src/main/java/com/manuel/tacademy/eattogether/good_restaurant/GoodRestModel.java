package com.manuel.tacademy.eattogether.good_restaurant;

/**
 * Created by Tacademy on 2017-08-07.
 */

public class GoodRestModel {
    String restaurantName;
    String foodName;
    String foodType;
    String foodPrice;
    String restaurantGrade;
    int foodImage;


    public GoodRestModel() {
    }

    public GoodRestModel(String restaurantName, String foodName, String foodType, String foodPrice, String restaurantGrade, int foodImage) {
        this.restaurantName = restaurantName;
        this.foodName = foodName;
        this.foodType = foodType;
        this.foodPrice = foodPrice;
        this.restaurantGrade = restaurantGrade;
        this.foodImage = foodImage;
    }

    public int getFoodImage() {
        return foodImage;
    }

    public void setFoodImage(int foodImage) {
        this.foodImage = foodImage;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    public String getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(String foodPrice) {
        this.foodPrice = foodPrice;
    }

    public String getRestaurantGrade() {
        return restaurantGrade;
    }

    public void setRestaurantGrade(String restaurantGrade) {
        this.restaurantGrade = restaurantGrade;
    }

}
