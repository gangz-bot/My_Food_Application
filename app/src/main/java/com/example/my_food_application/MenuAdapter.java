package com.example.my_food_application;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.HashMap;
import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {
    private final List<MenuItem> menuItems;
    private final HashMap<MenuItem, Integer> cart; // To store cart quantities
    private final OnCartUpdateListener cartUpdateListener; // Interface to notify cart updates

    public MenuAdapter(List<MenuItem> menuItems, HashMap<MenuItem, Integer> cart, OnCartUpdateListener cartUpdateListener) {
        this.menuItems = menuItems;
        this.cart = cart;
        this.cartUpdateListener = cartUpdateListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_menu, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MenuItem menuItem = menuItems.get(position);
        holder.name.setText(menuItem.getName());
        holder.price.setText(String.format("$%.2f", menuItem.getPrice()));

        // Set quantity from cart
        int quantity = cart.getOrDefault(menuItem, 0);
        holder.quantity.setText(String.valueOf(quantity));

        // Load image dynamically
        Glide.with(holder.itemView.getContext())
                .load(menuItem.getImageUrl())
                .into(holder.image);

        // Handle "+" button click
        holder.increaseButton.setOnClickListener(v -> {
            int currentQuantity = cart.getOrDefault(menuItem, 0);
            cart.put(menuItem, currentQuantity + 1);
            holder.quantity.setText(String.valueOf(currentQuantity + 1));
            cartUpdateListener.onCartUpdated();
        });

        // Handle "-" button click
        holder.decreaseButton.setOnClickListener(v -> {
            int currentQuantity = cart.getOrDefault(menuItem, 0);
            if (currentQuantity > 0) {
                cart.put(menuItem, currentQuantity - 1);
                holder.quantity.setText(String.valueOf(currentQuantity - 1));
                cartUpdateListener.onCartUpdated();
            }
        });
    }

    @Override
    public int getItemCount() {
        return menuItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, price, quantity, increaseButton, decreaseButton;
        ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textViewMenuItemName);
            price = itemView.findViewById(R.id.textViewMenuItemPrice);
            image = itemView.findViewById(R.id.imageViewMenuItem);
            quantity = itemView.findViewById(R.id.textViewQuantity);
            increaseButton = itemView.findViewById(R.id.buttonIncrease); // Treated as TextView
            decreaseButton = itemView.findViewById(R.id.buttonDecrease); // Treated as TextView
        }
    }

    public interface OnCartUpdateListener {
        void onCartUpdated(); // Notify when cart is updated
    }
}
