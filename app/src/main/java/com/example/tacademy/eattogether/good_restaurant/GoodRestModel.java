package com.example.tacademy.eattogether.good_restaurant;

/**
 * Created by Tacademy on 2017-08-07.
 */

public class GoodRestModel {
    String restaurantName;
    String foodName;
    String foodType;
    String foodPrice;
    double restaurantGrade;


    public GoodRestModel() {
    }

    public GoodRestModel(String restaurantName, String foodName, String foodType, String foodPrice, double restaurantGrade ) {
        this.restaurantName = restaurantName;
        this.foodName = foodName;
        this.foodType = foodType;
        this.foodPrice = foodPrice;
        this.restaurantGrade = restaurantGrade;
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

    public double getRestaurantGrade() {
        return restaurantGrade;
    }

    public void setRestaurantGrade(double restaurantGrade) {
        this.restaurantGrade = restaurantGrade;
    }

}
