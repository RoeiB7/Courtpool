package com.example.courtpool.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import com.example.courtpool.utilities.AppManager;
import com.example.courtpool.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class ChooseLocationActivity extends AppCompatActivity {


    private AppManager manager;
    private ArrayList<String> cities;
    private AutoCompleteTextView editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_location);

        manager = new AppManager(this);
        manager.findChooseLocationViews(this);
        editText = manager.getChoose_location_ACLBL_enterCity();
        cities = new ArrayList<>();

        cities = manager.jsonToList(this, "israel_cities.json", "english_name", cities);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, cities);
        editText.setAdapter(adapter);

        TextView courtType = manager.getChoose_location_LBL_court();
        courtType.setOnClickListener(v ->

                //TODO: ADD FUNCTIONALITY FOR MOVING TO COURT TYPE,
                // ONLY AFTER CHOOSING COURT LOCATION
                // IF DIDN'T CHOOSE LOCATION POP UP AN ALERT
                manager.moveToCourtType(ChooseLocationActivity.this)
        );
    }


}