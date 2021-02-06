package com.example.courtpool.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.courtpool.utils.AppManager;
import com.example.courtpool.R;
import com.example.courtpool.utils.SPManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignInActivity extends AppCompatActivity {

    private AppManager manager;
    private EditText signin_password;
    private EditText signin_email;
    private boolean eye = false;
    private boolean crossEye = false;
    private final int DRAWABLE_RIGHT = 2;
    private final String PASSWORD = "password";
    private final String EMAIL = "email";
    private FirebaseAuth fAuth;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        manager = new AppManager(this);
        manager.findSignInViews(this);
        fAuth = FirebaseAuth.getInstance();

        signin_password = manager.getSign_in_EDT_password();
        signin_email = manager.getSign_in_EDT_email();

        String email = SPManager.getInstance().getString(EMAIL, "NA");
        String password = SPManager.getInstance().getString(PASSWORD, "NA");

        if (!email.equals("NA") && !password.equals("NA")) {
            signin_email.setText(email);
            signin_password.setText(password);
        }

        TextView signUp = manager.getSign_in_LBL_signUp();
        signUp.setOnClickListener(v -> manager.moveToSignUp(this));

        Button signIn = manager.getSign_in_BTN_signIn();
        signIn.setOnClickListener(v -> {
            if (manager.isEmpty(signin_email)) {
                signin_email.setError("Email is required");
                return;
            }

            if (manager.isEmpty(signin_password)) {
                signin_password.setError("Password is required");
                return;
            }

            fAuth.signInWithEmailAndPassword(signin_email.getText().toString().trim(), signin_password.getText().toString().trim())

                    .addOnSuccessListener(authResult -> {
                        Toast.makeText(this, "Logged in", Toast.LENGTH_SHORT).show();
                        manager.moveToMatches(this);
                    })

                    .addOnFailureListener(e -> {
                        Toast.makeText(this, "Error! " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    });
        });

        signin_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (count != before && !crossEye) {
                    eye = manager.showEye(signin_password);
                    crossEye = false;
                } else {
                    crossEye = true;
                    if (s.toString().isEmpty()) {
                        eye = manager.showEye(signin_password);
                        signin_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        crossEye = false;
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        signin_password.setOnTouchListener((v, event) -> {
            if (eye) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getX() >= (signin_password.getWidth() - signin_password.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        manager.switchPasswordVisibility(signin_password);
                        return true;
                    }
                }
            }
            return false;
        });


        CheckBox rememberMe = manager.getSign_in_CBX_rememberMe();
        rememberMe.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked && !manager.isEmpty(signin_email) && !manager.isEmpty(signin_password) && signin_email.getText().toString().contains("@") && signin_password.getText().toString().length() > 7) {
                String mail = signin_email.getText().toString().trim();
                String pass = signin_password.getText().toString().trim();
                SPManager.getInstance().putString(EMAIL, mail);
                SPManager.getInstance().putString(PASSWORD, pass);
            }
        });

    }


}