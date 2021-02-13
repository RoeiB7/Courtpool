package com.example.courtpool.utils;

import android.app.Application;

import androidx.appcompat.app.AppCompatDelegate;

public class App extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        SPManager.init(this);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);


    }
}
