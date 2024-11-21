package com.example.my_food_application;

public class AddressRequest {
    private String orderId;
    private String fullAddress;
    private String address;
    private String townArea;
    private String city;
    private String phoneNumber;

    public AddressRequest(String orderId, String fullAddress, String address, String townArea, String city, String phoneNumber) {
        this.orderId = orderId;
        this.fullAddress = fullAddress;
        this.address = address;
        this.townArea = townArea;
        this.city = city;
        this.phoneNumber = phoneNumber;
    }

    // Getters and setters (if needed)
}
