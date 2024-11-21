package com.example.my_food_application;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddressActivity extends AppCompatActivity {
    private EditText editTextAddress, editTextTownArea, editTextCity, editTextPhoneNumber;
    private Button buttonSaveAddress;
    private String orderId; // To receive the order ID from the previous activity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        // Initialize views
        editTextAddress = findViewById(R.id.editTextAddress);
        editTextTownArea = findViewById(R.id.editTextTownArea);
        editTextCity = findViewById(R.id.editTextCity);
        editTextPhoneNumber = findViewById(R.id.editTextPhoneNumber);
        buttonSaveAddress = findViewById(R.id.buttonSaveAddress);

        // Get the order ID passed from the previous activity
        orderId = getIntent().getStringExtra("orderId");

        // Save Address button listener
        buttonSaveAddress.setOnClickListener(v -> saveAddress());
    }

    private void saveAddress() {
        String address = editTextAddress.getText().toString().trim();
        String townArea = editTextTownArea.getText().toString().trim();
        String city = editTextCity.getText().toString().trim();
        String phoneNumber = editTextPhoneNumber.getText().toString().trim();

        // Validate input fields
        if (address.isEmpty() || townArea.isEmpty() || city.isEmpty() || phoneNumber.isEmpty()) {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!phoneNumber.startsWith("+91")) {
            phoneNumber = "+91" + phoneNumber;
        }

        String fullAddress = address + ", " + townArea + ", " + city;

        // Make API call to save the address for the order
        ApiService apiService = RetrofitClient.getInstance().create(ApiService.class);
        AddressRequest addressRequest = new AddressRequest(orderId, fullAddress, address, townArea, city, phoneNumber);

        apiService.saveAddress(orderId, addressRequest).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(AddressActivity.this, "Address saved successfully", Toast.LENGTH_SHORT).show();
                    showDeliveryOptions();
                } else {
                    Toast.makeText(AddressActivity.this, "Failed to save address", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(AddressActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showDeliveryOptions() {
        // Create a dialog for delivery options
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Only Cash on Delivery");

        String[] options = {"Take Away", "Restaurant Delivery"};
        builder.setItems(options, (dialog, which) -> {
            String selectedOption = options[which];
            sendMessage(selectedOption);
        });

        builder.show();
    }

    private void sendMessage(String option) {
        ApiService apiService = RetrofitClient.getInstance().create(ApiService.class);
        String phoneNumber = editTextPhoneNumber.getText().toString().trim();
        String orderDetails = "Your order for " + option + " has been placed.";

        apiService.sendMessage(new MessageRequest(phoneNumber, orderDetails)).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(AddressActivity.this, "Message sent successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AddressActivity.this, "Failed to send message", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(AddressActivity.this, "Error sending message: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
