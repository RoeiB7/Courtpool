package com.example.courtpool;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GetStartedActivity extends AppCompatActivity {

    AppManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_started);

        manager = new AppManager(this);
        manager.findGetStartedViews(this);
        Button getStarted = manager.getGetStartedButton();
        getStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manager.moveToSignUp(GetStartedActivity.this);
            }
        });

        TextView signIn = manager.getSignIn();
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manager.moveToSignIn(GetStartedActivity.this);
            }
        });
    }
}