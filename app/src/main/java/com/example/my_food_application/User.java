package com.example.my_food_application;

public class User {
    private String email;
    private String password;
    private String token; // Token for authenticated API calls

    // Constructor for registration (email & password)
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // Constructor for login with token
    public User(String email, String password, String token) {
        this.email = email;
        this.password = password;
        this.token = token;
    }

    // Getters and Setters (needed for Retrofit/Gson serialization)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}