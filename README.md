# 🍔 My Food Application

An Android-based food ordering app that allows users to view restaurant listings, check menus, and interact with an intuitive UI. Built with a combination of Java, MongoDB, and REST APIs, this app showcases a complete food browsing experience.

## 📱 App Features

1. **User Authentication**  
   - Sign Up and Login pages with basic validation and secure password handling.

2. **Progress & Location Page**  
   - A progress bar for loading and a location button (without storing or displaying the location).

3. **Restaurant List Page**  
   - Fetches restaurant data (name, image, rating, location) dynamically from MongoDB.
   - Data auto-expands as the database grows.
   - Designed using RecyclerView for efficient scrolling and rendering.

4. **Menu Page**  
   - Displays menu items after selecting a restaurant.
   - Clean navigation between restaurant list and menus.

5. **Modern UI**  
   - Clean, user-friendly interface using Material Design.

---

## 🧰 Tech Stack

- **Frontend:** Java, Android Studio, RecyclerView  
- **Backend:** Node.js (optional for REST API), Express.js  
- **Database:** MongoDB (collection: `list_data`, database: `restaurant_list`)  
- **Data Format:** JSON for restaurant & menu data  
- **Network:** Retrofit for API calls

---

## 🔧 Setup Instructions

### 1. Clone the Repository

git clone https://github.com/your-username/your-repo-name.git

---
 
2. Backend 
Set up your MongoDB cluster and configure the Express server.

Import your JSON data into MongoDB collection list_data.

3. Android Studio
Open the project in Android Studio.

Make sure internet permission is added in AndroidManifest.xml.

Update the base URL in your RetrofitClient class to match your API.

4. Run the App
Use an emulator or connect a physical device.

Click Run.

---

📁 Folder Structure (Simplified)
```markdown
├── app/                      # Android application module 
│   ├── manifests/
│   └── src/main/
│       ├── java/
│       │   └── com/example/my_food_application/
│       │       ├── AddressActivity.java
│       │       ├── CartAdapter.java
│       │       ├── MainActivity.java
│       │       ├── MenuActivity.java
│       │       ├── OrderItem.java
│       │       ├── Restaurant.java
│       │       ├── RestaurantAdapter.java
│       │       ├── RetrofitClient.java
│       │       └── ... (and other .java files)
│       └── res/
│           ├── layout/
│           ├── drawable/
│           └── values/
├── models/                   # Backend Mongoose schemas 
├── node_modules/             # Node.js dependencies 
├── routes/                   # Backend API routes 
├── .gitignore
├── app.js                    # Backend server entry point 
├── package.json              # Backend dependencies and scripts 
└── README.md
```
---

**5. Future Improvements & Author Info**


-   [ ] Add payment integration.
-   [ ] Allow users to save favorite restaurants.
-   [ ] Enable real-time order tracking.
-   [ ] Implement live location tracking.

---

## 🙋‍♂️ Author

**Gangdev Pooniya**

📧 gangdevpooniya1115@gmail.com
