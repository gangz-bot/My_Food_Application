package com.example.my_food_application;

public class Restaurant {
    private String name;
    private float rating;
    private String imageUrl;
    private String location;

    public Restaurant(String name, float rating, String imageUrl, String location) {
        this.name = name;
        this.rating = rating;
        this.imageUrl = imageUrl;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public float getRating() {
        return rating;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getLocation() {
        return location;
    }
}
