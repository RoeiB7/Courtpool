package com.example.courtpool.activities;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.courtpool.R;
import com.example.courtpool.objects.Court;
import com.example.courtpool.utils.Adapter_Court;
import com.example.courtpool.utils.AppManager;
import com.example.courtpool.utils.FBManager;
import com.google.firebase.firestore.DocumentReference;

import java.util.ArrayList;
import java.util.Map;


public class ChooseLocationActivity extends AppCompatActivity {


    private AppManager manager;
    private AutoCompleteTextView autoCompleteTextView;
    private RecyclerView choose_location_LST_courtsLocations;
    private ArrayList<Court> courts;
    private ArrayList<Court> checkedCourts = new ArrayList<>();
    private ArrayList<String> fsCourts = new ArrayList<>();
    private Adapter_Court adapter_court;
    private FBManager fbManager;
    private String selectedCity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_location);

        manager = new AppManager(this);
        manager.findChooseLocationViews(this);
        fbManager = new FBManager();

        autoCompleteTextView = manager.getChoose_location_ACLBL_enterCity();
        choose_location_LST_courtsLocations = manager.getChoose_location_LST_courtsLocations();
        courts = new ArrayList<>();

        ArrayList<String> cities = new ArrayList<>();
        cities = manager.jsonToList(this, "israel_cities.json", "english_name", cities);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, cities);
        autoCompleteTextView.setAdapter(adapter);

        autoCompleteTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().isEmpty()) {
                    Log.d("ptt", "auto complete is empty");
                    courts.clear();
                    adapter_court = new Adapter_Court(ChooseLocationActivity.this, courts);
                    choose_location_LST_courtsLocations.setLayoutManager(new LinearLayoutManager(ChooseLocationActivity.this));
                    choose_location_LST_courtsLocations.setAdapter(adapter_court);

                } else {
                    courts.clear();
                    autoCompleteTextView.setOnItemClickListener((parent, view, position, id) -> {
                        selectedCity = autoCompleteTextView.getAdapter().getItem(position).toString();

                        DocumentReference documentReference = fbManager.getFirebaseFirestore().collection("cities").document("city");
                        documentReference.addSnapshotListener(ChooseLocationActivity.this, (documentSnapshot, error) -> {
                            if (error != null) {
                                Log.d("ptt", "Listen failed.", error);
                                return;
                            }
                            if (documentSnapshot != null && documentSnapshot.exists()) {
                                Map<String, Object> map = documentSnapshot.getData();

                                fsCourts = (ArrayList<String>) map.get(selectedCity);
                                if (fsCourts != null) {
                                    for (int i = 0; i < fsCourts.size(); i = i + 2) {
                                        courts.add(new Court(fsCourts.get(i), fsCourts.get(i + 1)));
                                    }
                                }
                            } else {
                                Log.d("ptt", "Current data: null");
                            }
                        });
                        adapter_court = new Adapter_Court(ChooseLocationActivity.this, courts);
                        adapter_court.setClickListener(new Adapter_Court.ItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                courts.get(position).setChecked(!courts.get(position).getChecked());
                                adapter_court.updateOneItem(position);
                            }
                        });
                        choose_location_LST_courtsLocations.setLayoutManager(new LinearLayoutManager(ChooseLocationActivity.this));
                        choose_location_LST_courtsLocations.setAdapter(adapter_court);
                    });
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        TextView courtType = manager.getChoose_location_LBL_court();
        courtType.setOnClickListener(v -> {
            for (int i = 0; i < courts.size(); i++) {
                if (courts.get(i).getChecked()) {
                    checkedCourts.add(courts.get(i));
                }
            }
            if (!checkedCourts.isEmpty()) {
                DocumentReference documentReference = fbManager.getFirebaseFirestore().collection("users").document(fbManager.getUserID());
                documentReference.update("courtLocation", checkedCourts)
                        .addOnSuccessListener(aVoid -> {
                            Log.d("ptt", "user updated - location");
                            manager.moveToCourtType(this);
                        })

                        .addOnFailureListener(e -> {
                            Toast.makeText(this, "Error! " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        });

            } else {
                Toast.makeText(this, "Please choose at least one court", Toast.LENGTH_SHORT).show();
            }
        });
    }
}