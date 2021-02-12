package com.example.courtpool.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.courtpool.utils.AppManager;
import com.example.courtpool.R;

import static android.view.View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;

public class GetStartedActivity extends AppCompatActivity {

    private AppManager manager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_started);

        manager = new AppManager(this);
        manager.findGetStartedViews(this);


        Button getStarted = manager.getGet_started_BTN_getStarted();
        getStarted.setOnClickListener(v -> manager.moveToSignUp(this));

        TextView signIn = manager.getGet_started_LBL_signIn();
        signIn.setOnClickListener(v -> manager.moveToSignIn(this));
    }

}