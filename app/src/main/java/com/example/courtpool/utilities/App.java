package com.example.courtpool.utilities;

import android.app.Application;

public class App extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        SPManager.init(this);
        Signal.init(this);

    }
}