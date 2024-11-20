package com.example.my_food_application;

import android.content.Context;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

public class MenuItemCartCounter {
    private TextView cartBadge;
    private Context context;

    public MenuItemCartCounter(Toolbar toolbar, Context context) {
        this.context = context;
        cartBadge = toolbar.findViewById(R.id.cartBadge);
    }

    public void updateCartCount(int count) {
        if (cartBadge != null) {
            cartBadge.setText(String.valueOf(count));
        }
    }
}
