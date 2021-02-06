package com.example.courtpool.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.courtpool.objects.User;
import com.example.courtpool.utils.AppManager;
import com.example.courtpool.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {

    private AppManager manager;
    private EditText name, email, phone, password;
    private ImageView profilePic;
    private boolean eye = false;
    private boolean crossEye = false;
    private final int DRAWABLE_RIGHT = 2;

    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        manager = new AppManager(this);
        manager.findSignUpViews(this);
        initViews();

    }


    @SuppressLint("ClickableViewAccessibility")
    public void initViews() {

        profilePic = manager.getSign_up_IMG_addProfilePic();
        password = manager.getSign_up_EDT_password();
        name = manager.getSign_up_EDT_name();
        email = manager.getSign_up_EDT_email();
        phone = manager.getSign_up_EDT_phone();

        profilePic.setOnClickListener(v -> {

            //TODO: ADD FUNCTIONALITY FOR UPLOADING PICTURE

        });


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

        TextView play = manager.getSign_up_LBL_play();
        play.setOnClickListener(v -> {

            switch (manager.checkFields()) {

                case 0:
                    Toast.makeText(this, "One or more fields are empty", Toast.LENGTH_LONG).show();
                    break;
                case 1:
                    name.setError("Your name must contain at least 2 letter");
                    break;
                case 2:
                    email.setError("Please fix your email format, i.e example@example.com");
                    break;
                case 3:
                    password.setError("Your password must contain at least 8 characters");
                    break;
                case 4:
                    phone.setError("Your phone number must contain 10 digits");
                    break;
                case 5:
                    AppManager.user.setName(name.getText().toString().trim());
                    AppManager.user.setEmail(email.getText().toString().trim());
                    AppManager.user.setPassword(password.getText().toString().trim());
                    AppManager.user.setPhone(phone.getText().toString().trim());
                    manager.moveToChooseLocation(this);
                    break;

            }
        });
    }
}