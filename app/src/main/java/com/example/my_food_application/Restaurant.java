package com.example.my_food_application;

public class Restaurant {
    private String name;
    private double rating;
    private String image;
    private String location;

    public Restaurant(String name, double rating, String image, String location) {
        this.name = name;
        this.rating = rating;
        this.image = image;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public double getRating() {
        return rating;
    }

    public String getImage() {
        return image;
    }

    public String getLocation() {
        return location;
    }
}
