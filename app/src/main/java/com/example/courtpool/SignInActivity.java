package com.example.courtpool;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SignInActivity extends AppCompatActivity {

    private AppManager manager;
    private EditText password;
    private boolean eye = false;
    private boolean crossEye = false;
    private final int DRAWABLE_RIGHT = 2;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        manager = new AppManager(this);
        manager.findSignInViews(this);

        TextView signUp = manager.getSignUpLBL();
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manager.moveToSignUp(SignInActivity.this);
            }
        });

        password = manager.getSignInPasswordEDTEDT();


        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (count != before && !crossEye) {
                    eye = manager.showEye(password);
                    crossEye = false;
                } else {
                    crossEye = true;
                    if (s.toString().isEmpty()) {
                        eye = manager.showEye(password);
                        password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        crossEye = false;
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });


        password.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (eye) {
                    if (event.getAction() == MotionEvent.ACTION_UP) {
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