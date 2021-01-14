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
    private ImageView cement;
    private ImageView grass;
    private ImageView synthetic;
    private ImageView clay;
    private TextView moveToSkills;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_court_type);

        manager = new AppManager(this);
        manager.findCourtTypeViews(this);

        cement = manager.getCourt_type_IMG_cement();
        cement.setOnClickListener(v -> {
            manager.checkMarkOn("cement");
        });
        grass = manager.getCourt_type_IMG_grass();
        grass.setOnClickListener(v -> {
            manager.checkMarkOn("grass");
        });
        synthetic = manager.getCourt_type_IMG_synthetic();
        synthetic.setOnClickListener(v -> {
            manager.checkMarkOn("synthetic");
        });
        clay = manager.getCourt_type_IMG_clay();
        clay.setOnClickListener(v -> {
            manager.checkMarkOn("clay");
        });

        moveToSkills = manager.getCourt_type_LBL_skill();
        moveToSkills.setOnClickListener(v -> {
            if (manager.checkMarkVisibility()) {

                //TODO: MOVE TO SKILL


            } else {
                Toast.makeText(this,
                        "Please choose one or more court types",
                        Toast.LENGTH_LONG).show();

            }


        });
    }
}