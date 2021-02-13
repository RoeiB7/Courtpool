package com.example.courtpool.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.courtpool.R;
import com.example.courtpool.utils.AppManager;

public class NavActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav);

        AppManager manager = new AppManager(this);
        manager.findNavViews(this);

    }
}