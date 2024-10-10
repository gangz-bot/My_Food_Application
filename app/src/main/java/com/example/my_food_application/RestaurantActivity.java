package com.example.my_food_application;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestaurantActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RestaurantAdapter restaurantAdapter;
    private List<Restaurant> restaurantList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

        recyclerView = findViewById(R.id.recyclerViewRestaurants);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        restaurantList = new ArrayList<>();

        // Add sample data with placeholder for location
        restaurantList.add(new Restaurant("The Great Indian Restaurant", 4.5f, "", "Fetching location..."));
        restaurantList.add(new Restaurant("Sushi Corner", 4.2f, "", "Fetching location..."));
        restaurantList.add(new Restaurant("Pizza Paradise", 4.8f, "", "Fetching location..."));
        restaurantList.add(new Restaurant("Burger Bonanza", 4.3f, "", "Fetching location..."));
        restaurantList.add(new Restaurant("Pasta House", 4.6f, "", "Fetching location..."));

        // Initialize the adapter and set it to the RecyclerView
        restaurantAdapter = new RestaurantAdapter(restaurantList);
        recyclerView.setAdapter(restaurantAdapter);

        // Button to sort the restaurant list by rating
        Button buttonSortByRating = findViewById(R.id.buttonSortByRating);
        buttonSortByRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Sort the list by rating in descending order
                Collections.sort(restaurantList, (r1, r2) -> Float.compare(r2.getRating(), r1.getRating()));
                restaurantAdapter.notifyDataSetChanged(); // Notify adapter about data change
            }
        });

        // Uncomment to fetch data from API if needed
        // fetchRestaurantData();
    }

    private void fetchRestaurantData() {
        ApiService apiService = RetrofitClient.getInstance().create(ApiService.class);
        Call<List<Restaurant>> call = apiService.getRestaurants();
        call.enqueue(new Callback<List<Restaurant>>() {
            @Override
            public void onResponse(Call<List<Restaurant>> call, Response<List<Restaurant>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    restaurantList.clear(); // Clear the existing list
                    restaurantList.addAll(response.body()); // Add the fetched data
                    Log.d("RestaurantActivity", "Fetched restaurants: " + restaurantList.size());
                    restaurantAdapter.notifyDataSetChanged(); // Notify adapter about data change
                } else {
                    Log.d("RestaurantActivity", "Response body is null");
                }
            }

            @Override
            public void onFailure(Call<List<Restaurant>> call, Throwable t) {
                Log.d("RestaurantActivity", "API Call Failed: " + t.getMessage());
            }
        });
    }
}
