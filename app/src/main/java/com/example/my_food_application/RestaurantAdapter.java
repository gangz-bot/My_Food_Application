package com.example.my_food_application;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.ViewHolder> {
    private final List<Restaurant> restaurantList;
    private final RestaurantClickListener clickListener;

    public RestaurantAdapter(List<Restaurant> restaurantList, RestaurantClickListener clickListener) {
        this.restaurantList = restaurantList;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_restaurant, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Restaurant restaurant = restaurantList.get(position);
        holder.restaurantName.setText(restaurant.getName());
        holder.rating.setText("Rating: " + restaurant.getRating());
        holder.location.setText(restaurant.getLocation());

        // Load restaurant image
        Glide.with(holder.itemView.getContext())
                .load(restaurant.getImage())
                .into(holder.imageViewRestaurant);

        // Set onClickListener for item
        holder.itemView.setOnClickListener(v -> {
            if (clickListener != null) {
                clickListener.onRestaurantClick(restaurant.getLocation(), restaurant.getName());
            }
        });
    }

    @Override
    public int getItemCount() {
        return restaurantList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView restaurantName, rating, location;
        ImageView imageViewRestaurant;

        public ViewHolder(View itemView) {
            super(itemView);
            restaurantName = itemView.findViewById(R.id.textViewRestaurantName);
            rating = itemView.findViewById(R.id.textViewRating);
            location = itemView.findViewById(R.id.textViewLocation);
            imageViewRestaurant = itemView.findViewById(R.id.imageViewRestaurant);
        }
    }

    // Interface for restaurant click events
    public interface RestaurantClickListener {
        void onRestaurantClick(String restaurantId, String restaurantName);
    }
}
