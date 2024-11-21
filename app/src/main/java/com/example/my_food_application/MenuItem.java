package com.example.my_food_application;

import android.os.Parcel;
import android.os.Parcelable;

public class MenuItem implements Parcelable {
    private String name;
    private float price;
    private String imageUrl;
    private boolean veg;

    public MenuItem(String name, float price, String imageUrl, boolean veg) {
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
        this.veg = veg;
    }

    protected MenuItem(Parcel in) {
        name = in.readString();
        price = in.readFloat();
        imageUrl = in.readString();
        veg = in.readByte() != 0;
    }

    public static final Creator<MenuItem> CREATOR = new Creator<MenuItem>() {
        @Override
        public MenuItem createFromParcel(Parcel in) {
            return new MenuItem(in);
        }

        @Override
        public MenuItem[] newArray(int size) {
            return new MenuItem[size];
        }
    };

    public String getName() { return name; }
    public float getPrice() { return price; }
    public String getImageUrl() { return imageUrl; }
    public boolean isVeg() { return veg; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeFloat(price);
        dest.writeString(imageUrl);
        dest.writeByte((byte) (veg ? 1 : 0));
    }
}
