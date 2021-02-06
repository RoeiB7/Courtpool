package com.example.courtpool.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.courtpool.utils.AppManager;
import com.example.courtpool.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class CourtTypeActivity extends AppCompatActivity {


    private AppManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_Courtpool);
        setContentView(R.layout.activity_court_type);

        manager = new AppManager(this);
        manager.findCourtTypeViews(this);
        initViews();

    }

    public void initViews() {

        ImageView cement = manager.getCourt_type_IMG_cement();
        cement.setOnClickListener(v -> manager.checkMarkOn(AppManager.CEMENT));

        ImageView grass = manager.getCourt_type_IMG_grass();
        grass.setOnClickListener(v -> manager.checkMarkOn(AppManager.GRASS));

        ImageView synthetic = manager.getCourt_type_IMG_synthetic();
        synthetic.setOnClickListener(v -> manager.checkMarkOn(AppManager.SYNTHETIC));

        ImageView clay = manager.getCourt_type_IMG_clay();
        clay.setOnClickListener(v -> manager.checkMarkOn(AppManager.CLAY));

        TextView moveToSkills = manager.getCourt_type_LBL_skill();
        moveToSkills.setOnClickListener(v -> {
            ArrayList<String> courtType = manager.checkMarkVisibility();
            if (!courtType.isEmpty()) {
                AppManager.user.setCourtTypes(courtType);
                manager.moveToSkill(this);
            } else {
                Toast.makeText(this, "Please choose one or more court types", Toast.LENGTH_LONG).show();
            }
        });
    }
}