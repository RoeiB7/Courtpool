package com.example.courtpool;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

public class SignInActivity extends AppCompatActivity {

    private AppManager manager;
    private EditText password;
    private boolean eye = false;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        manager = new AppManager(this);
        manager.findSignInViews(this);

        password = manager.getSignInPasswordEDTEDT();

        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                eye = manager.showEye(password);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });


        password.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_RIGHT = 2;
                if (eye) {
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        if (event.getX() >= (password.getWidth() - password.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                            manager.switchPasswordVisibility(password);
                            return true;
                        }
                    }
                }
                return false;
            }
        });


    }
}