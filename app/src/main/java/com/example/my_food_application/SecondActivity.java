package com.example.my_food_application;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page2nd); // Ensure this points to your second layout

        double latitude = getIntent().getDoubleExtra("latitude", 0);
        double longitude = getIntent().getDoubleExtra("longitude", 0);

        TextView locationTextView = findViewById(R.id.text_view_location); // Ensure this ID exists in page2nd.xml
        locationTextView.setText("Latitude: " + latitude + "\nLongitude: " + longitude);
    }
}
