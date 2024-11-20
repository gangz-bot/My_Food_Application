package com.example.my_food_application;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartActivity extends AppCompatActivity implements CartAdapter.OnCartUpdateListener {
    private RecyclerView recyclerViewCart;
    private CartAdapter cartAdapter;
    private TextView totalPriceTextView;
    private Button addAddressButton;

    private HashMap<MenuItem, Integer> cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        recyclerViewCart = findViewById(R.id.recyclerViewCart);
        totalPriceTextView = findViewById(R.id.textViewTotalPrice);
        addAddressButton = findViewById(R.id.buttonAddAddress);

        recyclerViewCart.setLayoutManager(new LinearLayoutManager(this));

        // Retrieve cart data from intent
        cart = (HashMap<MenuItem, Integer>) getIntent().getSerializableExtra("cart");

        if (cart == null) {
            cart = new HashMap<>();
        }

        // Set up cart adapter
        cartAdapter = new CartAdapter(cart, this);
        recyclerViewCart.setAdapter(cartAdapter);

        // Calculate initial total price
        updateTotalPrice();

        // Handle "Add Address" button click
        addAddressButton.setOnClickListener(v -> {
            Intent intent = new Intent(CartActivity.this, AddressActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public void onCartUpdated() {
        updateTotalPrice();
    }

    private void updateTotalPrice() {
        double totalPrice = 0;
        for (Map.Entry<MenuItem, Integer> entry : cart.entrySet()) {
            totalPrice += entry.getKey().getPrice() * entry.getValue();
        }
        totalPriceTextView.setText(String.format("Total: $%.2f", totalPrice));
    }
}
