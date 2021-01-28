package com.example.courtpool.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.courtpool.utilities.AppManager;
import com.example.courtpool.R;

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
        levelOne.setOnClickListener(v -> manager.isSkillSelected(1));

        ImageView levelTwo = manager.getSkill_IMG_level_two();
        levelTwo.setOnClickListener(v -> manager.isSkillSelected(2));

        ImageView levelThree = manager.getSkill_IMG_level_three();
        levelThree.setOnClickListener(v -> manager.isSkillSelected(3));

        TextView moveToTime = manager.getSkill_LBL_when_playing();
        moveToTime.setOnClickListener(v -> {
            if (manager.checkImageAlpha()) {
                manager.moveToDayAndTime(this);
            } else {
                Toast.makeText(this,
                        "Please choose one skill level",
                        Toast.LENGTH_LONG).show();
            }

        });
    }
}