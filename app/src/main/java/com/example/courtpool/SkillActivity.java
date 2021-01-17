package com.example.courtpool;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SkillActivity extends AppCompatActivity {

    AppManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skill);


        manager = new AppManager(this);
        manager.findSkillViews(this);
        manager.setTennisAlpha();

        ImageView levelOne = manager.getSkill_IMG_level_one();
        levelOne.setOnClickListener(v -> {
            manager.isSelected(1);
        });

        ImageView levelTwo = manager.getSkill_IMG_level_two();
        levelTwo.setOnClickListener(v -> {
            manager.isSelected(2);
        });

        ImageView levelThree = manager.getSkill_IMG_level_three();
        levelThree.setOnClickListener(v -> {
            manager.isSelected(3);
        });

        TextView moveToTime = manager.getSkill_LBL_when_playing();
        moveToTime.setOnClickListener(v -> {
            if (manager.checkImageAlpha()) {
                //TODO:MOVE TO CHOOSE DAY & TIME
                Toast.makeText(this,
                        "moving to day & time",
                        Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this,
                        "Please choose one skill level",
                        Toast.LENGTH_LONG).show();
            }

        });
    }
}