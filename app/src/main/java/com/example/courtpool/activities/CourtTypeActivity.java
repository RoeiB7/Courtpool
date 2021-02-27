package com.example.courtpool.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.courtpool.utils.AppManager;
import com.example.courtpool.R;
import com.example.courtpool.utils.FBManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class CourtTypeActivity extends AppCompatActivity {


    private AppManager manager;
    private FBManager fbManager;
    private TextView moveToSkills;
    private int editValue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_court_type);

        manager = new AppManager(this);
        manager.findCourtTypeViews(this);

        Intent intent = this.getIntent();
        editValue = intent.getIntExtra("edit", -1);

        moveToSkills = manager.getCourt_type_LBL_skill();

        if (editValue == 1) {
            moveToSkills.setText("Finish editing");
        }
        fbManager = new FBManager();

        initViews();

    }

    private void initViews() {

        ImageView cement = manager.getCourt_type_IMG_cement();
        cement.setOnClickListener(v -> manager.checkMarkOn(AppManager.CEMENT));

        ImageView grass = manager.getCourt_type_IMG_grass();
        grass.setOnClickListener(v -> manager.checkMarkOn(AppManager.GRASS));

        ImageView synthetic = manager.getCourt_type_IMG_synthetic();
        synthetic.setOnClickListener(v -> manager.checkMarkOn(AppManager.SYNTHETIC));

        ImageView clay = manager.getCourt_type_IMG_clay();
        clay.setOnClickListener(v -> manager.checkMarkOn(AppManager.CLAY));

        moveToSkills.setOnClickListener(v -> {
            ArrayList<String> courtType = manager.checkMarkVisibility();
            if (!courtType.isEmpty()) {
                DocumentReference documentReference = fbManager.getFirebaseFirestore().collection("users").document(fbManager.getUserID());
                documentReference.update(FBManager.KEY_TYPE, courtType)
                        .addOnSuccessListener(aVoid -> {
                            Log.d(AppManager.TAG, "user updated - court type");

                            if (editValue == 1) {
                                manager.moveToNav(this);
                            } else {
                                manager.moveToSkill(this);
                            }
                        })

                        .addOnFailureListener(e -> {
                            Toast.makeText(this, "Error! " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        });


            } else {
                Toast.makeText(this, "Please choose one or more court types", Toast.LENGTH_LONG).show();
            }
        });
    }
}