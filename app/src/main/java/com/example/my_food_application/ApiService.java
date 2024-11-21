package com.example.my_food_application;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.PUT;


import java.util.List;

public interface ApiService {

    @POST("/auth/register")
    Call<ApiResponse> registerUser(@Body User user);

    @POST("/auth/login")
    Call<ApiResponse> loginUser(@Body User user);

    // Fetch restaurant data with GET request
    @GET("/restaurants")
    Call<List<Restaurant>> getRestaurants();

    @GET("/restaurants/{id}/menu")
    Call<List<MenuItem>> getMenu(@Path("id") String restaurantId);

    @POST("/order/saveOrder")
    Call<OrderResponse> saveOrder(@Body OrderRequest orderRequest);

    @PUT("/order/saveAddress/{orderId}")
    Call<Void> saveAddress(@Path("orderId") String orderId, @Body AddressRequest addressRequest);


    @POST("/send-message")
    Call<Void> sendMessage(@Body MessageRequest messageRequest);

}