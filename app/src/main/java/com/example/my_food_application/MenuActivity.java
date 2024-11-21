package com.example.my_food_application;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MenuActivity extends AppCompatActivity implements MenuAdapter.OnCartUpdateListener {
    private RecyclerView recyclerView;
    private MenuAdapter menuAdapter;
    private HashMap<MenuItem, Integer> cart = new HashMap<>();
    private TextView cartBadge;
    private CheckBox checkBoxVeg, checkBoxNonVeg;
    private List<MenuItem> allMenuItems = new ArrayList<>();
    private List<MenuItem> filteredMenuItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        recyclerView = findViewById(R.id.recyclerViewMenu);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        cartBadge = findViewById(R.id.cartBadge);

        checkBoxVeg = findViewById(R.id.checkBoxVeg);
        checkBoxNonVeg = findViewById(R.id.checkBoxNonVeg);

        // Set checkbox tick colors
        checkBoxVeg.setButtonTintList(getResources().getColorStateList(android.R.color.holo_green_light));
        checkBoxNonVeg.setButtonTintList(getResources().getColorStateList(android.R.color.holo_red_light));

        // Add click listeners to checkboxes
        checkBoxVeg.setOnClickListener(v -> filterMenu(true));
        checkBoxNonVeg.setOnClickListener(v -> filterMenu(false));

        FloatingActionButton fabCart = findViewById(R.id.fabCart);
        fabCart.setOnClickListener(v -> {
            Intent intent = new Intent(MenuActivity.this, CartActivity.class);
            intent.putExtra("cart", new HashMap<>(cart));
            startActivity(intent);
        });

        String restaurantId = getIntent().getStringExtra("restaurantId");
        fetchMenu(restaurantId);
    }

    private void fetchMenu(String restaurantId) {
        ApiService apiService = RetrofitClient.getInstance().create(ApiService.class);
        apiService.getMenu(restaurantId).enqueue(new retrofit2.Callback<List<MenuItem>>() {
            @Override
            public void onResponse(retrofit2.Call<List<MenuItem>> call, retrofit2.Response<List<MenuItem>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    allMenuItems = response.body();
                    resetMenu(); // Initially show all items
                }
            }

            @Override
            public void onFailure(retrofit2.Call<List<MenuItem>> call, Throwable t) {
                // Handle failure
            }
        });
    }

    private void filterMenu(boolean showVeg) {
        // Uncheck the opposite checkbox
        if (showVeg) {
            checkBoxNonVeg.setChecked(false);
        } else {
            checkBoxVeg.setChecked(false);
        }

        // Filter items based on selection
        filteredMenuItems.clear();
        for (MenuItem item : allMenuItems) {
            if ((showVeg && item.isVeg()) || (!showVeg && !item.isVeg())) {
                filteredMenuItems.add(item);
            }
        }

        // Update the adapter
        menuAdapter.updateMenu(filteredMenuItems);
    }

    private void resetMenu() {
        filteredMenuItems.clear();
        filteredMenuItems.addAll(allMenuItems);
        menuAdapter = new MenuAdapter(filteredMenuItems, cart, this);
        recyclerView.setAdapter(menuAdapter);
    }

    @Override
    public void onCartUpdated() {
        int totalItems = 0;
        for (int quantity : cart.values()) {
            totalItems += quantity;
        }

        if (totalItems > 0) {
            cartBadge.setVisibility(View.VISIBLE);
            cartBadge.setText(String.valueOf(totalItems));
        } else {
            cartBadge.setVisibility(View.GONE);
        }
    }
}
