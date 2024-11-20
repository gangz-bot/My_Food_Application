package com.example.my_food_application;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RestaurantActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RestaurantAdapter restaurantAdapter;
    private List<Restaurant> restaurantList = new ArrayList<>();
    private List<Restaurant> filteredList = new ArrayList<>();
    private Button buttonSortByRating;
    private EditText editTextSearch;
    private ImageView imageViewSearchIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

        recyclerView = findViewById(R.id.recyclerViewRestaurants);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        buttonSortByRating = findViewById(R.id.buttonSortByRating);
        editTextSearch = findViewById(R.id.editTextSearch);
        imageViewSearchIcon = findViewById(R.id.imageViewSearchIcon);

        // Load data
        setupRestaurants();

        // Handle sorting by rating
        buttonSortByRating.setOnClickListener(v -> {
            if (!filteredList.isEmpty()) {
                Collections.sort(filteredList, (r1, r2) -> Double.compare(r2.getRating(), r1.getRating()));
                restaurantAdapter.notifyDataSetChanged();
                Toast.makeText(RestaurantActivity.this, "Sorted by rating", Toast.LENGTH_SHORT).show();
            }
        });

        // Handle search functionality
        imageViewSearchIcon.setOnClickListener(v -> {
            String query = editTextSearch.getText().toString().trim();
            searchRestaurants(query);
        });
    }

    // Setup hardcoded restaurants
    private void setupRestaurants() {
        restaurantList.add(new Restaurant(
                "Spicy Bowl", 4.5, "https://lh3.googleusercontent.com/p/AF1QipPDHQP38TyR91p_DCUHBdhkROcnCn8pY4L7HhAD=s1360-w1360-h1020", "1"));
        restaurantList.add(new Restaurant(
                "Cafe Marigold", 4.2, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRZ15l69f5UM-sh0imqmH72UDOTg2W8pOTVHA&s", "2"));
        restaurantList.add(new Restaurant(
                "Students Cafe", 4.8, "https://b.zmtcdn.com/data/pictures/0/20468250/7afa98287e14dfa1b7d5ed605f620ff1.jpg?fit=around|960:500&crop=960:500;", "3"));
        restaurantList.add(new Restaurant(
                "Mayank Restaurant", 3.9, "https://content.jdmagicbox.com/comp/dehradun/s6/9999px135.x135.170607163038.n1s6/catalogue/mayank-dabha-dehradun-restaurants-o9t6d.jpg", "4"));
        restaurantList.add(new Restaurant(
                "Kandoli Adda", 4.3, "https://b.zmtcdn.com/data/pictures/7/19475467/6392472a2a3d490f970e53fa3966280b.jpg?fit=around|960:500&crop=960:500;", "5"));
        restaurantList.add(new Restaurant(
                "Jims Corner", 4.1, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSbJEYQDK_QA4v-R02ILMudf1PyNVFe7faAsA&s", "6"));

        filteredList.addAll(restaurantList);

        restaurantAdapter = new RestaurantAdapter(filteredList, this::openMenuActivity);
        recyclerView.setAdapter(restaurantAdapter);
    }

    // Open MenuActivity when a restaurant is clicked
    private void openMenuActivity(String restaurantId, String restaurantName) {
        Intent intent = new Intent(this, MenuActivity.class);
        intent.putExtra("restaurantId", restaurantId);
        intent.putExtra("restaurantName", restaurantName);
        startActivity(intent);
    }

    // Search for restaurants
    private void searchRestaurants(String query) {
        filteredList.clear();
        if (!query.isEmpty()) {
            for (Restaurant restaurant : restaurantList) {
                if (restaurant.getName().toLowerCase().contains(query.toLowerCase())) {
                    filteredList.add(restaurant);
                }
            }
            if (filteredList.isEmpty()) {
                Toast.makeText(this, "No results found", Toast.LENGTH_SHORT).show();
            }
        } else {
            filteredList.addAll(restaurantList);
        }
        restaurantAdapter.notifyDataSetChanged();
    }
}
