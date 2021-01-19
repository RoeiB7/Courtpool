package com.example.courtpool;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MatchesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matches);

        AppManager manager = new AppManager(this);
        manager.findMatchesViews(this);

    }
}