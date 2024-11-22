package com.example.my_food_application;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CheckBox;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MenuActivity extends AppCompatActivity implements MenuAdapter.OnCartUpdateListener {
    private RecyclerView recyclerView;
    private MenuAdapter menuAdapter;
    private HashMap<MenuItem, Integer> cart = new HashMap<>();
    private TextView cartBadge;
    private CheckBox checkBoxVeg, checkBoxNonVeg;
    private List<MenuItem> allMenuItems = new ArrayList<>();
    private List<MenuItem> filteredMenuItems = new ArrayList<>();

    // Hardcoded menus for restaurants
    private final HashMap<String, List<MenuItem>> restaurantMenus = new HashMap<String, List<MenuItem>>() {{
        put("1", List.of(
                new MenuItem("French Fries", 100, "https://images.immediate.co.uk/production/volatile/sites/30/2021/03/French-fries-b9e3e0c.jpg?resize=768,574", true),
                new MenuItem("Chicken Wings", 250, "https://www.giverecipe.com/wp-content/uploads/2023/01/Air-Fryer-Whole-Chicken-Wings-Recipe.jpg", false),
                new MenuItem("Veg Burger", 150, "https://simple-veganista.com/wp-content/uploads/2013/08/quinoa-white-bean-veggie-burger-recipe-1.jpg", true),
                new MenuItem("Cheese Sandwich", 120, "https://cdn.loveandlemons.com/wp-content/uploads/2023/01/grilled-cheese.jpg", true),
                new MenuItem("Cold Coffee", 180, "https://rakskitchen.net/wp-content/uploads/2016/12/Cold-coffee-recipe-500x500.jpg", true)
        ));

        put("2", List.of(
                new MenuItem("Paneer Tikka", 200, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT48LlaZW3Qcftr_ccBIkjWAYRBfrNylt7-HA&s", true),
                new MenuItem("Garlic Bread", 120, "https://www.ambitiouskitchen.com/wp-content/uploads/2023/02/Garlic-Bread-4.jpg", true),
                new MenuItem("Tomato Soup", 80, "https://www.cubesnjuliennes.com/wp-content/uploads/2022/02/Homemade-Tomato-Soup-Recipe.jpg", true),
                new MenuItem("Spring Rolls", 140, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTviV6CLzy3Uub11eOuKOxO1p8WtvnxhDaa_w&ss", true),
                new MenuItem("Hot Chocolate", 160, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR7lYXC3PNgeBYT3LzgOu8Jffp-glQIIVundA&s", true)
        ));

        put("3", List.of(
                new MenuItem("Paratha", 200, "https://pipingpotcurry.com/wp-content/uploads/2022/11/Aloo-Paratha-Piping-Pot-Curry-500x375.jpg", true),
                new MenuItem("Paneer Paratha", 120, "https://maayeka.com/wp-content/uploads/2023/12/broccoli-paneer-paratha-recipe-a-500x375.jpg", true),
                new MenuItem("Veggie Wrap", 150, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSFJcpnVd3vNj-_oSo_ydj3xIwtdNEruL8kKA&s", true),
                new MenuItem("Dal Fry", 350, "https://www.mrishtanna.com/wp-content/uploads/2019/10/dal-tadka-instant-pot-480x270.jpg", true),
                new MenuItem("Naan", 60, "https://www.vegrecipesofindia.com/wp-content/uploads/2022/12/garlic-naan-3.jpg", true)
        ));
        put("4", List.of(
                new MenuItem("Maggie", 200, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRdtgnjW-DDVCuOUr7qwBps72C0SA583M3ixA&s", true),
                new MenuItem("Rolls", 120, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSSDYT68s04vYAzXDKJcStnzHrY4zGZqgq_sA&s", true),
                new MenuItem("Chicken Momos", 100, "https://thewhiskaddict.com/wp-content/uploads/2020/02/IMG_2848-2-360x360.jpg", false),
                new MenuItem("Hakka Noodles", 180, "https://thewhiskaddict.com/wp-content/uploads/2020/02/IMG_2848-2-360x360.jpg", true),
                new MenuItem("Chocolate Shake", 150, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQllGEYL-ijJSy7o0iAoLgJrZqtnv02CYJRrQ&s", true)
        ));
        put("5", List.of(
                new MenuItem("Dosa", 200, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRx-glTW9VpM8O7nW1los6X5F9VkIOaj07rgw&s", true),
                new MenuItem("Chai", 12, "https://www.munatycooking.com/wp-content/uploads/2024/04/Three-glasses-filled-with-karak-chai-500x500.jpg", true),
                new MenuItem("Idli", 80, "https://www.foodie-trail.com/recipe_recipe/idli-rice-rava/", true),
                new MenuItem("Vada", 90, "https://food.ndtv.com/recipe-healthy-medu-vada-958364", true),
                new MenuItem("Rava Kesari", 100, "https://www.vegrecipesofindia.com/wp-content/uploads/2021/06/kesari-recipe-1-500x500.jpg", true)
        ));
        put("6", List.of(
                new MenuItem("Butter chicken", 200, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTQIXPI_NvRJqcCL-SW8CfdMEwoBR7rXcR82g&s", false),
                new MenuItem("Chicken Fries", 120, "https://realfoodbydad.com/wp-content/uploads/2017/02/Chicken-Fries-Real-Food-by-Dad-683x1024.jpg", false),
                new MenuItem("Grilled Fish", 300, "https://cookingwithclaudy.com/wp-content/uploads/2024/07/20240729172022_IMG_7891.jpg", false),
                new MenuItem("Chicken Biryani", 250, "https://i.ytimg.com/vi/oskLFFcvhGI/maxresdefault.jpg", false),
                new MenuItem("Mutton Curry", 400, "https://www.whiskaffair.com/wp-content/uploads/2019/04/Punjabi-Mutton-Curry-5.jpg", false)
        ));
    }};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        recyclerView = findViewById(R.id.recyclerViewMenu);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        cartBadge = findViewById(R.id.cartBadge);

        checkBoxVeg = findViewById(R.id.checkBoxVeg);
        checkBoxNonVeg = findViewById(R.id.checkBoxNonVeg);

        FloatingActionButton fabCart = findViewById(R.id.fabCart);
        fabCart.setOnClickListener(v -> {
            Intent intent = new Intent(MenuActivity.this, CartActivity.class);
            intent.putExtra("cart", new HashMap<>(cart));
            startActivity(intent);
        });

        String restaurantId = getIntent().getStringExtra("restaurantId");
        loadMenu(restaurantId);

        setupCheckBoxListeners();
    }

    private void loadMenu(String restaurantId) {
        allMenuItems = restaurantMenus.getOrDefault(restaurantId, new ArrayList<>());
        resetMenu();
    }

    private void setupCheckBoxListeners() {
        checkBoxVeg.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                checkBoxNonVeg.setChecked(false);
                filterMenu(true);
            } else {
                resetMenu();
            }
        });

        checkBoxNonVeg.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                checkBoxVeg.setChecked(false);
                filterMenu(false);
            } else {
                resetMenu();
            }
        });
    }

    private void filterMenu(boolean showVeg) {
        filteredMenuItems.clear();
        for (MenuItem item : allMenuItems) {
            if ((showVeg && item.isVeg()) || (!showVeg && !item.isVeg())) {
                filteredMenuItems.add(item);
            }
        }
        menuAdapter.updateMenu(filteredMenuItems);
    }

    private void resetMenu() {
        filteredMenuItems.clear();
        filteredMenuItems.addAll(allMenuItems);
        menuAdapter = new MenuAdapter(filteredMenuItems, cart, this);
        recyclerView.setAdapter(menuAdapter);
    }

    @Override
    public void onCartUpdated() {
        int totalItems = 0;
        for (int quantity : cart.values()) {
            totalItems += quantity;
        }

        if (totalItems > 0) {
            cartBadge.setVisibility(View.VISIBLE);
            cartBadge.setText(String.valueOf(totalItems));
        } else {
            cartBadge.setVisibility(View.GONE);
        }
    }
}
