package com.example.courtpool;

import android.content.Intent;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.method.SingleLineTransformationMethod;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AppManager {


    private Button getStartedBTN;
    private Button signInBTN;

    private TextView signInLBL;
    private TextView signUpLBL;


    private EditText signInEmailEDT;
    private EditText signInPasswordEDT;

    private CheckBox rememberMeCBX;

    private boolean visibility = false;

    public AppManager(AppCompatActivity activity) {

    }


    public void findGetStartedViews(AppCompatActivity activity) {
        getStartedBTN = activity.findViewById(R.id.get_started_BTN_getStarted);
        signInLBL = activity.findViewById(R.id.get_started_LBL_signIn);
    }

    public void findSignInViews(AppCompatActivity activity) {
        signInBTN = activity.findViewById(R.id.sign_in_BTN_signIn);
        signUpLBL = activity.findViewById(R.id.sign_in_LBL_signUp);
        signInEmailEDT = activity.findViewById(R.id.sign_in_EDT_email);
        signInPasswordEDT = activity.findViewById(R.id.sign_in_EDT_password);
        rememberMeCBX = activity.findViewById(R.id.sign_in_CBX_rememberMe);

    }


    public void moveToSignUp(AppCompatActivity activity) {
        Intent intent = new Intent(activity, SignUpActivity.class);
        activity.startActivity(intent);
    }

    public void moveToSignIn(AppCompatActivity activity) {
        Intent intent = new Intent(activity, SignInActivity.class);
        activity.startActivity(intent);
    }

    public boolean showEye(EditText editText) {
        if (!editText.getText().toString().equals("")) {
            editText.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_visibility, 0);
            return true;
        } else {
            editText.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            return false;
        }
    }


    public void switchPasswordVisibility(EditText editText) {
        if (!visibility) {
            editText.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_invisible, 0);
            editText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            visibility = true;
        } else {
            editText.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_visibility, 0);
            editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
            visibility = false;
        }
    }

    public Button getGetStartedButton() {
        return getStartedBTN;
    }

    public TextView getSignIn() {
        return signInLBL;
    }

    public EditText getSignInPasswordEDTEDT() {
        return signInPasswordEDT;
    }
}
