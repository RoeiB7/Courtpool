package com.example.courtpool.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.courtpool.R;
import com.example.courtpool.objects.Court;
import com.example.courtpool.objects.User;
import com.example.courtpool.utils.Adapter_Profile_Court;
import com.example.courtpool.utils.FBManager;
import com.google.firebase.firestore.DocumentReference;

import java.util.ArrayList;

public class PlayerProfileActivity extends AppCompatActivity {

    private User user;
    private TextView player_profile_LBL_name;
    private TextView player_profile_LBL_number;
    private TextView player_profile_LBL_skill;
    private TextView player_profile_LBL_changeHours;
    private TextView player_profile_LBL_email;
    private ImageView player_profile_IMG_profilePic;
    private ImageView player_fragment_IMG_backArrow;
    private Spinner player_profile_LST_courtType;
    private Spinner player_profile_LST_days;
    private RecyclerView player_profile_LST_courts;
    private ArrayList<String> hours;
    private ArrayList<String> courts_strings;
    private FBManager fbManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_profile);

        Intent intent = this.getIntent();
        Bundle bundle = intent.getBundleExtra("player2");

        user = (User) bundle.getSerializable("player1");

        fbManager = new FBManager();
        findPlayerProfile();
        if (user == null) {
            Log.d("ptt", "user is null");
        } else {
            initViews();
        }
    }

    public void findPlayerProfile() {
        player_profile_LBL_name = findViewById(R.id.player_profile_LBL_name);
        player_profile_LBL_number = findViewById(R.id.player_profile_LBL_number);
        player_profile_LBL_email = findViewById(R.id.player_profile_LBL_email);
        player_profile_LBL_skill = findViewById(R.id.player_profile_LBL_skill);
        player_profile_LBL_changeHours = findViewById(R.id.player_profile_LBL_changeHours);
        player_profile_IMG_profilePic = findViewById(R.id.player_profile_IMG_profilePic);
        player_fragment_IMG_backArrow = findViewById(R.id.player_fragment_IMG_backArrow);
        player_profile_LST_courtType = findViewById(R.id.player_profile_LST_courtType);
        player_profile_LST_days = findViewById(R.id.player_profile_LST_days);
        player_profile_LST_courts = findViewById(R.id.player_profile_LST_courts);
    }

    public void initViews() {
        Glide.with(this)
                .load(user.getProfileImage())
                .apply(RequestOptions.circleCropTransform())
                .into(player_profile_IMG_profilePic);

        player_profile_LBL_name.setText(user.getName());
        player_profile_LBL_number.setText(user.getPhone());
        player_profile_LBL_email.setText(user.getEmail());
        player_profile_LBL_skill.setText(user.getSkill());

        ArrayAdapter<String> type_adapter = new ArrayAdapter<>(this, R.layout.spinner_item, user.getCourtTypes());
        player_profile_LST_courtType.setAdapter(type_adapter);

        ArrayList<String> days_strings = new ArrayList<>(user.getPlayTime().keySet());
        ArrayAdapter<String> days_adapter = new ArrayAdapter<>(this, R.layout.spinner_item, days_strings);
        player_profile_LST_days.setAdapter(days_adapter);


        hours = new ArrayList<>();
        player_profile_LST_days.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String day = parent.getItemAtPosition(position).toString();
                hours = user.getPlayTime().get(day);

                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < hours.size(); i++) {
                    sb.append(hours.get(i));
                    if (hours.size() - 1 > i) {
                        sb.append(", ");
                    }

                }
                player_profile_LBL_changeHours.setText(sb);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        DocumentReference documentReference = fbManager.getFirebaseFirestore().collection("users").document(user.getId());
        documentReference.addSnapshotListener((documentSnapshot, error) -> {
            if (error != null) {
                return;
            }
            if (documentSnapshot.exists()) {

                courts_strings = (ArrayList<String>) documentSnapshot.getData().get(FBManager.KEY_LOCATION);

                ArrayList<Court> courts = new ArrayList<>();
                for (int i = 0; i < courts_strings.size(); i = i + 2) {
                    String court_name = courts_strings.get(i);
                    String court_address = courts_strings.get(i + 1);
                    courts.add(new Court(court_name, court_address));
                }

                Adapter_Profile_Court adapter_profile_court = new Adapter_Profile_Court(this, courts);
                player_profile_LST_courts.setLayoutManager(new LinearLayoutManager(this));
                player_profile_LST_courts.setAdapter(adapter_profile_court);

            }

        });


        player_fragment_IMG_backArrow.setOnClickListener(v -> finish());
    }

}