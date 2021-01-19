package com.example.courtpool;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CourtTypeActivity extends AppCompatActivity {


    private AppManager manager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_court_type);

        manager = new AppManager(this);
        manager.findCourtTypeViews(this);

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
            if (manager.checkMarkVisibility()) {
                manager.moveToSkill(this);
            } else {
                Toast.makeText(this,
                        "Please choose one or more court types",
                        Toast.LENGTH_LONG).show();
            }
        });
    }
}