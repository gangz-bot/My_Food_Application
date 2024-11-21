package com.example.my_food_application;

import java.util.List;

public class OrderRequest {
    private List<OrderItem> foodItems;
    private double totalPrice;

    public OrderRequest(List<OrderItem> foodItems, double totalPrice) {
        this.foodItems = foodItems;
        this.totalPrice = totalPrice;
    }

    public List<OrderItem> getFoodItems() {
        return foodItems;
    }

    public void setFoodItems(List<OrderItem> foodItems) {
        this.foodItems = foodItems;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
