package com.example.courtpool;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ChooseLocationActivity extends AppCompatActivity {


    private AppManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_location);

        manager = new AppManager(this);
        manager.findChooseLocationViews(this);

        TextView courtType = manager.getChoose_location_LBL_court();
        courtType.setOnClickListener(v -> {

            //TODO: ADD FUNCTIONALITY FOR MOVING TO COURT TYPE,
            // ONLY AFTER CHOOSING COURT LOCATION
            // IF DIDN'T CHOOSE LOCATION POP UP AN ALERT
            manager.moveToCourtType(ChooseLocationActivity.this);
        });
    }
}