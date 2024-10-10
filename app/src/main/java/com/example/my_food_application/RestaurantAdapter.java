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
    private List<Restaurant> restaurantList;

    public RestaurantAdapter(List<Restaurant> restaurantList) {
        this.restaurantList = restaurantList;
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
        holder.location.setText(restaurant.getLocation()); // Display location

        // Load restaurant image using Glide
        Glide.with(holder.itemView.getContext())
                .load(restaurant.getImageUrl()) // Replace with your image URL
                .into(holder.imageViewRestaurant);
    }

    @Override
    public int getItemCount() {
        return restaurantList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView restaurantName, rating, location;
        ImageView imageViewRestaurant;

        public ViewHolder(View itemView) {
            super(itemView);
            restaurantName = itemView.findViewById(R.id.textViewRestaurantName);
            rating = itemView.findViewById(R.id.textViewRating);
            location = itemView.findViewById(R.id.textViewLocation); // Reference to location TextView
            imageViewRestaurant = itemView.findViewById(R.id.imageViewRestaurant); // Reference to ImageView
        }
    }
}
