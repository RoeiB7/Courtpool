package com.example.courtpool;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {

    private AppManager manager;
    private EditText password;
    private boolean eye = false;
    private boolean crossEye = false;
    private final int DRAWABLE_RIGHT = 2;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        manager = new AppManager(this);
        manager.findSignUpViews(this);

        ImageView profilePic = manager.getSign_up_IMG_addProfilePic();
        profilePic.setOnClickListener(v -> {

            //TODO: ADD FUNCTIONALITY FOR UPLOADING PICTURE & SAVE IN DB

        });

        TextView play = manager.getSign_up_LBL_play();
        play.setOnClickListener(v -> {

            switch (manager.checkFields()) {

                case 0:
                    Toast.makeText(this,
                            "One or more fields are empty",
                            Toast.LENGTH_LONG).show();
                    break;
                case 1:
                    Toast.makeText(this,
                            "Your name must contain at least 3 letter",
                            Toast.LENGTH_LONG).show();
                    break;
                case 2:
                    Toast.makeText(this,
                            "Please fix your email format, i.e example@example.com",
                            Toast.LENGTH_LONG).show();
                    break;
                case 3:
                    Toast.makeText(this,
                            "Your password must contain at least 8 characters",
                            Toast.LENGTH_LONG).show();
                    break;
                case 4:
                    Toast.makeText(this,
                            "Your phone number must contain 10 digits",
                            Toast.LENGTH_LONG).show();
                    break;
                case 5:
                    manager.moveToChooseLocation(this);
                    //TODO: ADD FUNCTIONALITY FOR SIGN UP, SAVE IN DB ALL FIELDS
            }

        });


        password = manager.getSign_up_EDT_password();

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

        password.setOnTouchListener((v, event) -> {
            if (eye) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getX() >= (password.getWidth() - password.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        manager.switchPasswordVisibility(password);
                        return true;
                    }
                }
            }
            return false;
        });


    }
}