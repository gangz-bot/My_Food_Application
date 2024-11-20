package com.example.my_food_application;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {
    private Button locationButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page2nd);

        locationButton = findViewById(R.id.locationButton);

        // Simulate loading with a progress bar
        new Handler().postDelayed(() -> locationButton.setVisibility(View.VISIBLE), 2000);

        locationButton.setOnClickListener(view -> {
            // Navigate to RestaurantActivity
            Intent intent = new Intent(SecondActivity.this, RestaurantActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
