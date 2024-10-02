package com.example.my_food_application;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {

    // Register user API call
    @POST("/auth/register")
    Call<ApiResponse> registerUser(@Body User user);

    // Login user API call
    @POST("/auth/login")
    Call<ApiResponse> loginUser(@Body User user);
}
