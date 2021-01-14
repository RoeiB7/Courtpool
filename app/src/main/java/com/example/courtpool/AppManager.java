package com.example.courtpool;

import android.content.Intent;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.method.SingleLineTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class AppManager {


    private Button get_started_BTN_getStarted;
    private Button sign_in_BTN_signIn;

    private TextView get_started_LBL_signIn;
    private TextView sign_in_LBL_signUp;
    private TextView sign_up_LBL_play;
    private TextView choose_location_LBL_court;
    private TextView court_type_LBL_skill;

    private EditText sign_in_EDT_email;
    private EditText sign_in_EDT_password;
    private EditText sign_up_EDT_name;
    private EditText sign_up_EDT_email;
    private EditText sign_up_EDT_password;
    private EditText sign_up_EDT_phone;
    private EditText choose_location_EDT_enterCity;

    private ImageView sign_up_IMG_addProfilePic;
    private ImageView court_type_IMG_cement;
    private ImageView court_type_IMG_grass;
    private ImageView court_type_IMG_synthetic;
    private ImageView court_type_IMG_clay;
    private ImageView court_type_IMG_checkMarkHard;
    private ImageView court_type_IMG_checkMarkGrass;
    private ImageView court_type_IMG_checkMarkSynthetic;


    private ImageView court_type_IMG_checkMarkClay;

    private CheckBox sign_in_CBX_rememberMe;

    private RecyclerView choose_location_RCV_courtsLocations;

    private boolean visibility = false;

    public AppManager(AppCompatActivity activity) {

    }


    public void findGetStartedViews(AppCompatActivity activity) {
        get_started_BTN_getStarted = activity.findViewById(R.id.get_started_BTN_getStarted);
        get_started_LBL_signIn = activity.findViewById(R.id.get_started_LBL_signIn);
    }

    public void findSignInViews(AppCompatActivity activity) {
        sign_in_BTN_signIn = activity.findViewById(R.id.sign_in_BTN_signIn);
        sign_in_LBL_signUp = activity.findViewById(R.id.sign_in_LBL_signUp);
        sign_in_EDT_email = activity.findViewById(R.id.sign_in_EDT_email);
        sign_in_EDT_password = activity.findViewById(R.id.sign_in_EDT_password);
        sign_in_CBX_rememberMe = activity.findViewById(R.id.sign_in_CBX_rememberMe);

    }

    public void findSignUpViews(AppCompatActivity activity) {
        sign_up_IMG_addProfilePic = activity.findViewById(R.id.sign_up_IMG_addProfilePic);
        sign_up_EDT_name = activity.findViewById(R.id.sign_up_EDT_name);
        sign_up_EDT_email = activity.findViewById(R.id.sign_up_EDT_email);
        sign_up_EDT_password = activity.findViewById(R.id.sign_up_EDT_password);
        sign_up_EDT_phone = activity.findViewById(R.id.sign_up_EDT_phone);
        sign_up_LBL_play = activity.findViewById(R.id.sign_up_LBL_play);
    }

    public void findChooseLocationViews(AppCompatActivity activity) {
        choose_location_LBL_court = activity.findViewById(R.id.choose_location_LBL_court);
        choose_location_EDT_enterCity = activity.findViewById(R.id.choose_location_EDT_enterCity);
        choose_location_RCV_courtsLocations = activity.findViewById(R.id.choose_location_RCV_courtsLocations);
    }

    public void findCourtTypeViews(AppCompatActivity activity) {
        court_type_IMG_cement = activity.findViewById(R.id.court_type_IMG_cement);
        court_type_IMG_grass = activity.findViewById(R.id.court_type_IMG_grass);
        court_type_IMG_synthetic = activity.findViewById(R.id.court_type_IMG_synthetic);
        court_type_IMG_clay = activity.findViewById(R.id.court_type_IMG_clay);
        court_type_IMG_checkMarkHard = activity.findViewById(R.id.court_type_IMG_checkMarkHard);
        court_type_IMG_checkMarkGrass = activity.findViewById(R.id.court_type_IMG_checkMarkGrass);
        court_type_IMG_checkMarkSynthetic = activity.findViewById(R.id.court_type_IMG_checkMarkSynthetic);
        court_type_IMG_checkMarkClay = activity.findViewById(R.id.court_type_IMG_checkMarkClay);
        court_type_LBL_skill = activity.findViewById(R.id.court_type_LBL_skill);
    }


    public void moveToSignUp(AppCompatActivity activity) {
        Intent intent = new Intent(activity, SignUpActivity.class);
        activity.startActivity(intent);
    }

    public void moveToSignIn(AppCompatActivity activity) {
        Intent intent = new Intent(activity, SignInActivity.class);
        activity.startActivity(intent);
    }

    public void moveToChooseLocation(AppCompatActivity activity) {
        Intent intent = new Intent(activity, ChooseLocationActivity.class);
        activity.startActivity(intent);
    }

    public void moveToCourtType(AppCompatActivity activity) {
        Intent intent = new Intent(activity, CourtTypeActivity.class);
        activity.startActivity(intent);
    }

    public boolean showEye(EditText editText) {
        if (!isEmpty(editText)) {
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

    public boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }

    public int checkFields() {
        if (isEmpty(sign_up_EDT_name) ||
                isEmpty(sign_up_EDT_email) ||
                isEmpty(sign_up_EDT_password) ||
                isEmpty(sign_up_EDT_phone)) {
            return 0;
        } else if (sign_up_EDT_name.getText().toString().trim().length() < 3) {
            return 1;
        } else if (isEmpty(sign_up_EDT_email) || !sign_up_EDT_email.getText().toString().contains("@")) {
            return 2;
        } else if (sign_up_EDT_password.getText().toString().trim().length() < 8) {
            return 3;
        } else if (sign_up_EDT_phone.getText().toString().trim().length() != 10)
            return 4;
        else {
            return 5;
        }
    }


    public void checkMarkOn(String checkMark) {

        switch (checkMark) {
            case "cement":
                if (isVisible(court_type_IMG_checkMarkHard)) {
                    court_type_IMG_checkMarkHard.setVisibility(View.INVISIBLE);
                } else {
                    court_type_IMG_checkMarkHard.setVisibility(View.VISIBLE);
                }
                break;
            case "grass":
                if (isVisible(court_type_IMG_checkMarkGrass)) {
                    court_type_IMG_checkMarkGrass.setVisibility(View.INVISIBLE);
                } else {
                    court_type_IMG_checkMarkGrass.setVisibility(View.VISIBLE);
                }
                break;
            case "synthetic":
                if (isVisible(court_type_IMG_checkMarkSynthetic)) {
                    court_type_IMG_checkMarkSynthetic.setVisibility(View.INVISIBLE);
                } else {
                    court_type_IMG_checkMarkSynthetic.setVisibility(View.VISIBLE);
                }
                break;
            case "clay":
                if (isVisible(court_type_IMG_checkMarkClay)) {
                    court_type_IMG_checkMarkClay.setVisibility(View.INVISIBLE);
                } else {
                    court_type_IMG_checkMarkClay.setVisibility(View.VISIBLE);
                }
                break;
        }
    }

    public boolean checkMarkVisibility() {
        return isVisible(court_type_IMG_checkMarkHard)
                || isVisible(court_type_IMG_checkMarkGrass)
                || isVisible(court_type_IMG_checkMarkSynthetic)
                || isVisible(court_type_IMG_checkMarkClay);
    }

    public boolean isVisible(ImageView imageView) {
        return imageView.getVisibility() == View.VISIBLE;
    }

    public Button getGet_started_BTN_getStarted() {
        return get_started_BTN_getStarted;
    }

    public Button getSign_in_BTN_signIn() {
        return sign_in_BTN_signIn;
    }

    public TextView getGet_started_LBL_signIn() {
        return get_started_LBL_signIn;
    }

    public TextView getSign_in_LBL_signUp() {
        return sign_in_LBL_signUp;
    }

    public EditText getSign_in_EDT_email() {
        return sign_in_EDT_email;
    }

    public EditText getSign_in_EDT_password() {
        return sign_in_EDT_password;
    }

    public CheckBox getSign_in_CBX_rememberMe() {
        return sign_in_CBX_rememberMe;
    }

    public TextView getSign_up_LBL_play() {
        return sign_up_LBL_play;
    }

    public EditText getSign_up_EDT_name() {
        return sign_up_EDT_name;
    }

    public EditText getSign_up_EDT_email() {
        return sign_up_EDT_email;
    }

    public EditText getSign_up_EDT_password() {
        return sign_up_EDT_password;
    }

    public EditText getSign_up_EDT_phone() {
        return sign_up_EDT_phone;
    }

    public ImageView getSign_up_IMG_addProfilePic() {
        return sign_up_IMG_addProfilePic;
    }

    public TextView getChoose_location_LBL_court() {
        return choose_location_LBL_court;
    }

    public EditText getChoose_location_EDT_enterCity() {
        return choose_location_EDT_enterCity;
    }

    public RecyclerView getChoose_location_RCV_courtsLocations() {
        return choose_location_RCV_courtsLocations;
    }

    public TextView getCourt_type_LBL_skill() {
        return court_type_LBL_skill;
    }

    public ImageView getCourt_type_IMG_cement() {
        return court_type_IMG_cement;
    }

    public ImageView getCourt_type_IMG_grass() {
        return court_type_IMG_grass;
    }

    public ImageView getCourt_type_IMG_synthetic() {
        return court_type_IMG_synthetic;
    }

    public ImageView getCourt_type_IMG_clay() {
        return court_type_IMG_clay;
    }


}
