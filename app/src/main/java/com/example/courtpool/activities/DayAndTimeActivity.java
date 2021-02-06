package com.example.courtpool.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.courtpool.utils.AppManager;
import com.example.courtpool.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class DayAndTimeActivity extends AppCompatActivity {


    private AppManager manager;
    private FirebaseAuth fAuth;
    private FirebaseFirestore fStore;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_Courtpool);
        setContentView(R.layout.activity_day_and_time);

        manager = new AppManager(this);
        manager.findDayAndTimeViews(this);
        manager.setBallAlpha();
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        LinearLayout morningSunday = manager.getDay_and_time_LAY_sunday_morning();
        morningSunday.setOnClickListener(v -> manager.selectDay(1));

        LinearLayout midDaySunday = manager.getDay_and_time_LAY_sunday_mid_day();
        midDaySunday.setOnClickListener(v -> manager.selectDay(2));

        LinearLayout eveningSunday = manager.getDay_and_time_LAY_sunday_evening();
        eveningSunday.setOnClickListener(v -> manager.selectDay(3));

        LinearLayout morningMonday = manager.getDay_and_time_LAY_monday_morning();
        morningMonday.setOnClickListener(v -> manager.selectDay(4));

        LinearLayout midDayMonday = manager.getDay_and_time_LAY_monday_mid_day();
        midDayMonday.setOnClickListener(v -> manager.selectDay(5));

        LinearLayout eveningMonday = manager.getDay_and_time_LAY_monday_evening();
        eveningMonday.setOnClickListener(v -> manager.selectDay(6));

        LinearLayout morningTuesday = manager.getDay_and_time_LAY_tuesday_morning();
        morningTuesday.setOnClickListener(v -> manager.selectDay(7));

        LinearLayout midDayTuesday = manager.getDay_and_time_LAY_tuesday_mid_day();
        midDayTuesday.setOnClickListener(v -> manager.selectDay(8));

        LinearLayout eveningTuesday = manager.getDay_and_time_LAY_tuesday_evening();
        eveningTuesday.setOnClickListener(v -> manager.selectDay(9));

        LinearLayout morningWednesday = manager.getDay_and_time_LAY_wednesday_morning();
        morningWednesday.setOnClickListener(v -> manager.selectDay(10));

        LinearLayout midDayWednesday = manager.getDay_and_time_LAY_wednesday_mid_day();
        midDayWednesday.setOnClickListener(v -> manager.selectDay(11));

        LinearLayout eveningWednesday = manager.getDay_and_time_LAY_wednesday_evening();
        eveningWednesday.setOnClickListener(v -> manager.selectDay(12));

        LinearLayout morningThursday = manager.getDay_and_time_LAY_thursday_morning();
        morningThursday.setOnClickListener(v -> manager.selectDay(13));

        LinearLayout midDayThursday = manager.getDay_and_time_LAY_thursday_mid_day();
        midDayThursday.setOnClickListener(v -> manager.selectDay(14));

        LinearLayout eveningThursday = manager.getDay_and_time_LAY_thursday_evening();
        eveningThursday.setOnClickListener(v -> manager.selectDay(15));

        LinearLayout morningFriday = manager.getDay_and_time_LAY_friday_morning();
        morningFriday.setOnClickListener(v -> manager.selectDay(16));

        LinearLayout midDayFriday = manager.getDay_and_time_LAY_friday_mid_day();
        midDayFriday.setOnClickListener(v -> manager.selectDay(17));

        LinearLayout eveningFriday = manager.getDay_and_time_LAY_friday_evening();
        eveningFriday.setOnClickListener(v -> manager.selectDay(18));

        LinearLayout morningSaturday = manager.getDay_and_time_LAY_saturday_morning();
        morningSaturday.setOnClickListener(v -> manager.selectDay(19));

        LinearLayout midDaySaturday = manager.getDay_and_time_LAY_saturday_mid_day();
        midDaySaturday.setOnClickListener(v -> manager.selectDay(20));

        LinearLayout eveningSaturday = manager.getDay_and_time_LAY_saturday_evening();
        eveningSaturday.setOnClickListener(v -> manager.selectDay(21));

        TextView moveToMatches = manager.getDay_and_time_LBL_find();
        moveToMatches.setOnClickListener(v -> {

            if (manager.checkDaySelected()) {
                manager.updateMap();
                AppManager.user.setPlayTime(manager.getUpdatedMap());
                fAuth.createUserWithEmailAndPassword(AppManager.user.getEmail(), AppManager.user.getPassword())
                        .addOnSuccessListener(authResult -> {
                            Toast.makeText(this, "User created", Toast.LENGTH_SHORT).show();
                            addUserToDB();
                            manager.moveToMatches(this);
                        })
                        .addOnFailureListener(e -> {
                            Toast.makeText(this, "Error! " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        });
            } else {
                Toast.makeText(this, "Please choose at least one day", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void addUserToDB() {
        userID = fAuth.getCurrentUser().getUid();
        DocumentReference documentReference = fStore.collection("users").document(userID);
        documentReference.set(AppManager.user)
                .addOnSuccessListener(aVoid -> {
                    Log.d("ptt", "onSuccess: user profile is created for " + userID);
                })
                .addOnFailureListener(e -> {
                    Log.d("ptt", "onFailure: " + e.toString());
                });

    }
}