package com.example.my_food_application;

public class AddressRequest {
    private String userId;
    private String address;
    private String townArea;
    private String city;
    private String phoneNumber;

    // Constructor
    public AddressRequest(String userId, String address, String townArea, String city, String phoneNumber) {
        this.userId = userId;
        this.address = address;
        this.townArea = townArea;
        this.city = city;
        this.phoneNumber = phoneNumber;
    }

    // Getters and Setters
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTownArea() {
        return townArea;
    }

    public void setTownArea(String townArea) {
        this.townArea = townArea;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
