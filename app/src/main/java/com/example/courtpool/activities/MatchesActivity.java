package com.example.courtpool.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.courtpool.utils.AppManager;
import com.example.courtpool.R;

public class MatchesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matches);

        AppManager manager = new AppManager(this);
        manager.findMatchesViews(this);

        //TODO: CREATE NEW BOTTOM NAVIGATION ACTIVITY FOR SWITCHING BETWEEN PROFILE/MATCHES/CHAT(MAYBE)

    }
}