package com.example.courtpool.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.courtpool.utilities.AppManager;
import com.example.courtpool.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetStartedActivity extends AppCompatActivity {

    AppManager manager;

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