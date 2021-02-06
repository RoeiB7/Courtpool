package com.example.courtpool.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.courtpool.objects.Court;
import com.example.courtpool.utils.Adapter_Court;
import com.example.courtpool.utils.AppManager;
import com.example.courtpool.R;

import java.util.ArrayList;


public class ChooseLocationActivity extends AppCompatActivity {


    private AppManager manager;
    private AutoCompleteTextView editText;
    private RecyclerView choose_location_LST_courtsLocations;
    private ArrayList<Court> courts;
    private ArrayList<String> courtsName = new ArrayList<>();
    private Adapter_Court adapter_court;


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

        courts = new ArrayList<>();
        //TODO: ADD COURTS TO LIST FROM DB

        courts.add(new Court("Tel Aviv Tennis Center", "Zvi Nishri St 6, Tel Aviv-Yafo, Israel"));
        courts.add(new Court("Hadar Yosef Tennis Center", "Bekhor Shalom Shitrit St 2, Tel Aviv-Yafo, Israel"));
        courts.add(new Court("Gani Yehushua Tennis Center", "Rokach Blvd 67, Tel Aviv-Yafo, Israel"));
        courts.add(new Court("Maccabi Tennis Club", "Rokach Blvd 73, Tel Aviv-Yafo, Israel"));
        courts.add(new Court("TAU Tennis Club", "Chaim Levanon St 62, Tel Aviv-Yafo, Israel"));


        adapter_court = new Adapter_Court(this, courts);
        adapter_court.setClickListener((view, position) -> {
            Toast.makeText(this, courts.get(position).getName(), Toast.LENGTH_SHORT).show();
            courts.get(position).setChecked(!courts.get(position).getChecked());
            adapter_court.updateOneItem(position);
        });

        choose_location_LST_courtsLocations.setLayoutManager(new LinearLayoutManager(this));
        choose_location_LST_courtsLocations.setAdapter(adapter_court);


        TextView courtType = manager.getChoose_location_LBL_court();
        courtType.setOnClickListener(v -> {
            for (int i = 0; i < courts.size(); i++) {
                if (courts.get(i).getChecked()) {
                    courtsName.add(courts.get(i).getName());
                }
            }
            if (!courtsName.isEmpty()) {
                Log.d("ptt", "courts name list not empty");
                AppManager.user.setCourtLocation(courtsName);
                manager.moveToCourtType(ChooseLocationActivity.this);
            } else {
                Log.d("ptt", "courts name list is empty");
                Toast.makeText(this, "Please choose at least one court", Toast.LENGTH_SHORT).show();
            }
        });


    }


}