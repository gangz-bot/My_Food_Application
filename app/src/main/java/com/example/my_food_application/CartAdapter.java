package com.example.my_food_application;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    private final HashMap<MenuItem, Integer> cart;
    private final OnCartUpdateListener cartUpdateListener;
    private final List<Map.Entry<MenuItem, Integer>> cartItems;

    public CartAdapter(HashMap<MenuItem, Integer> cart, OnCartUpdateListener cartUpdateListener) {
        this.cart = cart;
        this.cartUpdateListener = cartUpdateListener;
        this.cartItems = new ArrayList<>(cart.entrySet());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cart, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MenuItem menuItem = cartItems.get(position).getKey();
        int quantity = cartItems.get(position).getValue();

        holder.itemName.setText(menuItem.getName());
        holder.itemPrice.setText(String.format("₹%.2f", menuItem.getPrice()));
        holder.itemQuantity.setText(String.valueOf(quantity));

        // Load item image
        Glide.with(holder.itemView.getContext())
                .load(menuItem.getImageUrl())

                .into(holder.itemImage);

        // Handle "+" button click
        holder.buttonIncrease.setOnClickListener(v -> {
            cart.put(menuItem, quantity + 1);
            cartUpdateListener.onCartUpdated();
            updateCartItems();
        });

        // Handle "-" button click
        holder.buttonDecrease.setOnClickListener(v -> {
            if (quantity > 1) {
                cart.put(menuItem, quantity - 1);
            } else {
                cart.remove(menuItem);
            }
            cartUpdateListener.onCartUpdated();
            updateCartItems();
        });
    }

    private void updateCartItems() {
        this.cartItems.clear();
        this.cartItems.addAll(cart.entrySet());
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    public interface OnCartUpdateListener {
        void onCartUpdated();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView itemName, itemPrice, itemQuantity;
        ImageView itemImage;
        ImageButton buttonIncrease, buttonDecrease;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.textViewCartItemName);
            itemPrice = itemView.findViewById(R.id.textViewCartItemPrice);
            itemQuantity = itemView.findViewById(R.id.textViewCartItemQuantity);
            itemImage = itemView.findViewById(R.id.imageViewCartItem);
            buttonIncrease = itemView.findViewById(R.id.buttonIncreaseQuantity);
            buttonDecrease = itemView.findViewById(R.id.buttonDecreaseQuantity);
        }
    }
}
