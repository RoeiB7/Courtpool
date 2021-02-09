package com.example.courtpool.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.courtpool.utils.AppManager;
import com.example.courtpool.R;
import com.example.courtpool.utils.FBManager;
import com.google.firebase.firestore.DocumentReference;

public class SkillActivity extends AppCompatActivity {

    private AppManager manager;
    private FBManager fbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_Courtpool);
        setContentView(R.layout.activity_skill);


        manager = new AppManager(this);
        manager.findSkillViews(this);
        manager.setTennisAlpha();

        fbManager = new FBManager();

        ImageView levelOne = manager.getSkill_IMG_level_one();
        levelOne.setOnClickListener(v -> manager.isSkillSelected(1));

        ImageView levelTwo = manager.getSkill_IMG_level_two();
        levelTwo.setOnClickListener(v -> manager.isSkillSelected(2));

        ImageView levelThree = manager.getSkill_IMG_level_three();
        levelThree.setOnClickListener(v -> manager.isSkillSelected(3));

        TextView moveToTime = manager.getSkill_LBL_when_playing();
        moveToTime.setOnClickListener(v -> {
            if (manager.checkImageAlpha().length() != 0) {
                DocumentReference documentReference = fbManager.getFirebaseFirestore().collection("users").document(fbManager.getUserID());
                documentReference.update("skill", manager.checkImageAlpha())
                        .addOnSuccessListener(aVoid -> {
                            Log.d("ptt", "user updated - skill");
                            manager.moveToDayAndTime(this);
                        })

                        .addOnFailureListener(e -> {
                            Toast.makeText(this, "Error! " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        });
            } else {
                Toast.makeText(this, "Please choose one skill level", Toast.LENGTH_LONG).show();
            }

        });
    }
}