package com.example.my_food_application;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.GET;

import java.util.List;

public interface ApiService {

    @POST("/auth/register")
    Call<ApiResponse> registerUser(@Body User user);

    @POST("/auth/login")
    Call<ApiResponse> loginUser(@Body User user);

    // Fetch restaurant data with GET request
    @GET("restaurants") // Adjust endpoint if necessary
    Call<List<Restaurant>> getRestaurants();
}