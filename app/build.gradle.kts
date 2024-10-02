plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.my_food_application"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.my_food_application"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }



}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation("com.google.android.gms:play-services-maps:18.1.0")
//    implementation ("org.mongodb:mongodb-driver-sync:5.1.4")
//    implementation ("org.mongodb:mongodb-driver-sync:latest-version")
    // Google Play services location library
    implementation("com.google.android.gms:play-services-location:21.0.1")
    implementation ("com.google.android.gms:play-services-location:latest-version")
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")



}

