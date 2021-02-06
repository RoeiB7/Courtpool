package com.example.courtpool.utils;

import android.app.Application;

public class App extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        SPManager.init(this);

    }
}
