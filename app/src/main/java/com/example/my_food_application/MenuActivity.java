package com.example.my_food_application;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.HashMap;
import java.util.List;

public class MenuActivity extends AppCompatActivity implements MenuAdapter.OnCartUpdateListener {
    private RecyclerView recyclerView;
    private MenuAdapter menuAdapter;
    private HashMap<MenuItem, Integer> cart = new HashMap<>();
    private TextView cartBadge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        recyclerView = findViewById(R.id.recyclerViewMenu);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        cartBadge = findViewById(R.id.cartBadge);

        FloatingActionButton fabCart = findViewById(R.id.fabCart);
        fabCart.setOnClickListener(v -> {
            // Navigate to CartActivity
            Intent intent = new Intent(MenuActivity.this, CartActivity.class);
            intent.putExtra("cart", new HashMap<>(cart)); // Pass cart details to the CartActivity
            startActivity(intent);
        });

        String restaurantId = getIntent().getStringExtra("restaurantId");
        String restaurantName = getIntent().getStringExtra("restaurantName");

        if (restaurantName != null) {
            setTitle(restaurantName); // Set the activity title to the restaurant name
        }

        if (restaurantId != null) {
            fetchMenu(restaurantId); // Fetch menu for the restaurant
        }
    }

    private void fetchMenu(String restaurantId) {
        ApiService apiService = RetrofitClient.getInstance().create(ApiService.class);
        apiService.getMenu(restaurantId).enqueue(new retrofit2.Callback<List<MenuItem>>() {
            @Override
            public void onResponse(retrofit2.Call<List<MenuItem>> call, retrofit2.Response<List<MenuItem>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Set the menu adapter with fetched menu items
                    menuAdapter = new MenuAdapter(response.body(), cart, MenuActivity.this);
                    recyclerView.setAdapter(menuAdapter);
                }
            }

            @Override
            public void onFailure(retrofit2.Call<List<MenuItem>> call, Throwable t) {
                // Handle API call failure (Optional: Add logging or Toast message here)
            }
        });
    }

    @Override
    public void onCartUpdated() {
        int totalItems = cart.values().stream().mapToInt(Integer::intValue).sum(); // Calculate total items in the cart
        cartBadge.setVisibility(totalItems > 0 ? View.VISIBLE : View.GONE); // Show/hide the badge
        cartBadge.setText(String.valueOf(totalItems)); // Update the badge count
    }
}
