package com.example.courtpool.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.courtpool.objects.Court;
import com.example.courtpool.utilities.Adapter_Court;
import com.example.courtpool.utilities.AppManager;
import com.example.courtpool.R;

import java.util.ArrayList;


public class ChooseLocationActivity extends AppCompatActivity {


    private AppManager manager;
    private AutoCompleteTextView editText;
    private RecyclerView choose_location_LST_courtsLocations;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_location);

        manager = new AppManager(this);
        manager.findChooseLocationViews(this);
        editText = manager.getChoose_location_ACLBL_enterCity();
        choose_location_LST_courtsLocations = manager.getChoose_location_LST_courtsLocations();


        ArrayList<String> cities = new ArrayList<>();
        cities = manager.jsonToList(this, "israel_cities.json", "english_name", cities);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, cities);
        editText.setAdapter(adapter);


        ArrayList<Court> courts = new ArrayList<>();
        //TODO: ADD COURTS TO LIST FROM DB

        courts.add(new Court("Tel Aviv Tennis Center", "Zvi Nishri St 6, Tel Aviv-Yafo, Israel"));
        courts.add(new Court("Hadar Yosef Tennis Center", "Bekhor Shalom Shitrit St 2, Tel Aviv-Yafo, Israel"));
        courts.add(new Court("Gani Yehushua Tennis Center", "Rokach Blvd 67, Tel Aviv-Yafo, Israel"));
        courts.add(new Court("Maccabi Tennis Club", "Rokach Blvd 73, Tel Aviv-Yafo, Israel"));
        courts.add(new Court("TAU Tennis Club", "Chaim Levanon St 62, Tel Aviv-Yafo, Israel"));
        courts.add(new Court("Roeiiiiii", "Hacarmel St 62, Tel Aviv-Yafo, Israel"));


        Adapter_Court adapter_court = new Adapter_Court(this, courts);
        adapter_court.setClickListener((view, position) -> {
            Toast.makeText(ChooseLocationActivity.this, courts.get(position).getName(), Toast.LENGTH_SHORT).show();
            //TODO: ADD OR REMOVE CHECKMARK, ADD SELECTED COURTS TO USER PREFERENCES IN DB
        });

        choose_location_LST_courtsLocations.setLayoutManager(new LinearLayoutManager(this));
        choose_location_LST_courtsLocations.setAdapter(adapter_court);

        TextView courtType = manager.getChoose_location_LBL_court();
        courtType.setOnClickListener(v ->

                //TODO: ADD FUNCTIONALITY FOR MOVING TO COURT TYPE,
                // ONLY AFTER CHOOSING COURT LOCATION
                // IF DIDN'T CHOOSE LOCATION POP UP AN ALERT
                manager.moveToCourtType(ChooseLocationActivity.this)
        );
    }


}