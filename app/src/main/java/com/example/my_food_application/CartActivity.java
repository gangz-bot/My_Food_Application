package com.example.my_food_application;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        addAddressButton.setOnClickListener(v -> saveOrderAndProceedToAddress());
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
        totalPriceTextView.setText(String.format("Total: ₹%.2f", totalPrice));
    }

    private void saveOrderAndProceedToAddress() {
        List<OrderItem> orderItems = new ArrayList<>();
        double totalPrice = 0;

        for (Map.Entry<MenuItem, Integer> entry : cart.entrySet()) {
            MenuItem menuItem = entry.getKey();
            int quantity = entry.getValue();
            orderItems.add(new OrderItem(menuItem.getName(), quantity, menuItem.getPrice()));
            totalPrice += menuItem.getPrice() * quantity;
        }

        ApiService apiService = RetrofitClient.getInstance().create(ApiService.class);
        OrderRequest orderRequest = new OrderRequest(orderItems, totalPrice);

        apiService.saveOrder(orderRequest).enqueue(new Callback<OrderResponse>() {
            @Override
            public void onResponse(Call<OrderResponse> call, Response<OrderResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    String orderId = response.body().getOrderId();
                    Intent intent = new Intent(CartActivity.this, AddressActivity.class);
                    intent.putExtra("orderId", orderId);
                    startActivity(intent);
                } else {
                    Toast.makeText(CartActivity.this, "Failed to save order", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<OrderResponse> call, Throwable t) {
                Toast.makeText(CartActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
