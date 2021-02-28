package com.example.courtpool.utils;

import android.content.Context;
import android.content.Intent;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.courtpool.R;
import com.example.courtpool.activities.ChooseLocationActivity;
import com.example.courtpool.activities.CourtTypeActivity;
import com.example.courtpool.activities.DayAndTimeActivity;
import com.example.courtpool.activities.NavActivity;
import com.example.courtpool.activities.SignInActivity;
import com.example.courtpool.activities.SignUpActivity;
import com.example.courtpool.activities.SkillActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AppManager {


    private Button get_started_BTN_getStarted;
    private Button sign_in_BTN_signIn;
    private Button sign_up_BTN_uploadImage;


    private TextView get_started_LBL_signIn;
    private TextView sign_in_LBL_signUp;
    private TextView sign_up_LBL_play;
    private TextView choose_location_LBL_court;
    private TextView court_type_LBL_skill;
    private TextView skill_LBL_when_playing;
    private TextView day_and_time_LBL_find;
    private AutoCompleteTextView choose_location_ACLBL_enterCity;

    private EditText sign_in_EDT_email;
    private EditText sign_in_EDT_password;
    private EditText sign_up_EDT_name;
    private EditText sign_up_EDT_email;
    private EditText sign_up_EDT_password;
    private EditText sign_up_EDT_phone;

    private ImageView sign_up_IMG_addProfilePic;
    private ImageView court_type_IMG_cement;
    private ImageView court_type_IMG_grass;
    private ImageView court_type_IMG_synthetic;
    private ImageView court_type_IMG_clay;
    private ImageView court_type_IMG_checkMarkHard;
    private ImageView court_type_IMG_checkMarkGrass;
    private ImageView court_type_IMG_checkMarkSynthetic;
    private ImageView court_type_IMG_checkMarkClay;
    private ImageView skill_IMG_level_one;
    private ImageView skill_IMG_level_two;
    private ImageView skill_IMG_level_three;
    private ImageView day_and_time_IMG_sunday_morning;
    private ImageView day_and_time_IMG_sunday_mid_day;
    private ImageView day_and_time_IMG_sunday_evening;
    private ImageView day_and_time_IMG_monday_morning;
    private ImageView day_and_time_IMG_monday_mid_day;
    private ImageView day_and_time_IMG_monday_evening;
    private ImageView day_and_time_IMG_tuesday_morning;
    private ImageView day_and_time_IMG_tuesday_mid_day;
    private ImageView day_and_time_IMG_tuesday_evening;
    private ImageView day_and_time_IMG_wednesday_morning;
    private ImageView day_and_time_IMG_wednesday_mid_day;
    private ImageView day_and_time_IMG_wednesday_evening;
    private ImageView day_and_time_IMG_thursday_morning;
    private ImageView day_and_time_IMG_thursday_mid_day;
    private ImageView day_and_time_IMG_thursday_evening;
    private ImageView day_and_time_IMG_friday_morning;
    private ImageView day_and_time_IMG_friday_mid_day;
    private ImageView day_and_time_IMG_friday_evening;
    private ImageView day_and_time_IMG_saturday_morning;
    private ImageView day_and_time_IMG_saturday_mid_day;
    private ImageView day_and_time_IMG_saturday_evening;


    private LinearLayout day_and_time_LAY_sunday_morning;
    private LinearLayout day_and_time_LAY_sunday_mid_day;
    private LinearLayout day_and_time_LAY_sunday_evening;
    private LinearLayout day_and_time_LAY_monday_morning;
    private LinearLayout day_and_time_LAY_monday_mid_day;
    private LinearLayout day_and_time_LAY_monday_evening;
    private LinearLayout day_and_time_LAY_tuesday_morning;
    private LinearLayout day_and_time_LAY_tuesday_mid_day;
    private LinearLayout day_and_time_LAY_tuesday_evening;
    private LinearLayout day_and_time_LAY_wednesday_morning;
    private LinearLayout day_and_time_LAY_wednesday_mid_day;
    private LinearLayout day_and_time_LAY_wednesday_evening;
    private LinearLayout day_and_time_LAY_thursday_morning;
    private LinearLayout day_and_time_LAY_thursday_mid_day;
    private LinearLayout day_and_time_LAY_thursday_evening;
    private LinearLayout day_and_time_LAY_friday_morning;
    private LinearLayout day_and_time_LAY_friday_mid_day;
    private LinearLayout day_and_time_LAY_friday_evening;
    private LinearLayout day_and_time_LAY_saturday_morning;
    private LinearLayout day_and_time_LAY_saturday_mid_day;
    private LinearLayout day_and_time_LAY_saturday_evening;


    private CheckBox sign_in_CBX_rememberMe;

    private RecyclerView choose_location_LST_courtsLocations;
    private BottomNavigationView nav_BNV_bottomNavigation;

    private boolean visibility = false;
    private int daysSelected = 0;
    public static final String CEMENT = "Cement";
    public static final String GRASS = "Grass";
    public static final String SYNTHETIC = "Synthetic";
    public static final String CLAY = "Clay";
    public static final String TAG = "ptt";

    private Map<String, ArrayList<String>> playTime = new HashMap<>();
    private ArrayList<String> sunday = new ArrayList<>();
    private ArrayList<String> monday = new ArrayList<>();
    private ArrayList<String> tuesday = new ArrayList<>();
    private ArrayList<String> wednesday = new ArrayList<>();
    private ArrayList<String> thursday = new ArrayList<>();
    private ArrayList<String> friday = new ArrayList<>();
    private ArrayList<String> saturday = new ArrayList<>();


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
        sign_up_BTN_uploadImage = activity.findViewById(R.id.sign_up_BTN_uploadImage);
        sign_up_EDT_name = activity.findViewById(R.id.sign_up_EDT_name);
        sign_up_EDT_email = activity.findViewById(R.id.sign_up_EDT_email);
        sign_up_EDT_password = activity.findViewById(R.id.sign_up_EDT_password);
        sign_up_EDT_phone = activity.findViewById(R.id.sign_up_EDT_phone);
        sign_up_LBL_play = activity.findViewById(R.id.sign_up_LBL_play);
    }


    public void findChooseLocationViews(AppCompatActivity activity) {
        choose_location_LBL_court = activity.findViewById(R.id.choose_location_LBL_court);
        choose_location_ACLBL_enterCity = activity.findViewById(R.id.choose_location_ACLBL_enterCity);
        choose_location_LST_courtsLocations = activity.findViewById(R.id.choose_location_LST_courtsLocations);
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


    public void findSkillViews(AppCompatActivity activity) {
        skill_IMG_level_one = activity.findViewById(R.id.skill_IMG_level_one);
        skill_IMG_level_two = activity.findViewById(R.id.skill_IMG_level_two);
        skill_IMG_level_three = activity.findViewById(R.id.skill_IMG_level_three);
        skill_LBL_when_playing = activity.findViewById(R.id.skill_LBL_when_playing);
    }

    public void findDayAndTimeViews(AppCompatActivity activity) {
        day_and_time_IMG_sunday_morning = activity.findViewById(R.id.day_and_time_IMG_sunday_morning);
        day_and_time_IMG_sunday_mid_day = activity.findViewById(R.id.day_and_time_IMG_sunday_mid_day);
        day_and_time_IMG_sunday_evening = activity.findViewById(R.id.day_and_time_IMG_sunday_evening);
        day_and_time_IMG_monday_morning = activity.findViewById(R.id.day_and_time_IMG_monday_morning);
        day_and_time_IMG_monday_mid_day = activity.findViewById(R.id.day_and_time_IMG_monday_mid_day);
        day_and_time_IMG_monday_evening = activity.findViewById(R.id.day_and_time_IMG_monday_evening);
        day_and_time_IMG_tuesday_morning = activity.findViewById(R.id.day_and_time_IMG_tuesday_morning);
        day_and_time_IMG_tuesday_mid_day = activity.findViewById(R.id.day_and_time_IMG_tuesday_mid_day);
        day_and_time_IMG_tuesday_evening = activity.findViewById(R.id.day_and_time_IMG_tuesday_evening);
        day_and_time_IMG_wednesday_morning = activity.findViewById(R.id.day_and_time_IMG_wednesday_morning);
        day_and_time_IMG_wednesday_mid_day = activity.findViewById(R.id.day_and_time_IMG_wednesday_mid_day);
        day_and_time_IMG_wednesday_evening = activity.findViewById(R.id.day_and_time_IMG_wednesday_evening);
        day_and_time_IMG_thursday_morning = activity.findViewById(R.id.day_and_time_IMG_thursday_morning);
        day_and_time_IMG_thursday_mid_day = activity.findViewById(R.id.day_and_time_IMG_thursday_mid_day);
        day_and_time_IMG_thursday_evening = activity.findViewById(R.id.day_and_time_IMG_thursday_evening);
        day_and_time_IMG_friday_morning = activity.findViewById(R.id.day_and_time_IMG_friday_morning);
        day_and_time_IMG_friday_mid_day = activity.findViewById(R.id.day_and_time_IMG_friday_mid_day);
        day_and_time_IMG_friday_evening = activity.findViewById(R.id.day_and_time_IMG_friday_evening);
        day_and_time_IMG_saturday_morning = activity.findViewById(R.id.day_and_time_IMG_saturday_morning);
        day_and_time_IMG_saturday_mid_day = activity.findViewById(R.id.day_and_time_IMG_saturday_mid_day);
        day_and_time_IMG_saturday_evening = activity.findViewById(R.id.day_and_time_IMG_saturday_evening);


        day_and_time_LAY_sunday_morning = activity.findViewById(R.id.day_and_time_LAY_sunday_morning);
        day_and_time_LAY_sunday_mid_day = activity.findViewById(R.id.day_and_time_LAY_sunday_mid_day);
        day_and_time_LAY_sunday_evening = activity.findViewById(R.id.day_and_time_LAY_sunday_evening);
        day_and_time_LAY_monday_morning = activity.findViewById(R.id.day_and_time_LAY_monday_morning);
        day_and_time_LAY_monday_mid_day = activity.findViewById(R.id.day_and_time_LAY_monday_mid_day);
        day_and_time_LAY_monday_evening = activity.findViewById(R.id.day_and_time_LAY_monday_evening);
        day_and_time_LAY_tuesday_morning = activity.findViewById(R.id.day_and_time_LAY_tuesday_morning);
        day_and_time_LAY_tuesday_mid_day = activity.findViewById(R.id.day_and_time_LAY_tuesday_mid_day);
        day_and_time_LAY_tuesday_evening = activity.findViewById(R.id.day_and_time_LAY_tuesday_evening);
        day_and_time_LAY_wednesday_morning = activity.findViewById(R.id.day_and_time_LAY_wednesday_morning);
        day_and_time_LAY_wednesday_mid_day = activity.findViewById(R.id.day_and_time_LAY_wednesday_mid_day);
        day_and_time_LAY_wednesday_evening = activity.findViewById(R.id.day_and_time_LAY_wednesday_evening);
        day_and_time_LAY_thursday_morning = activity.findViewById(R.id.day_and_time_LAY_thursday_morning);
        day_and_time_LAY_thursday_mid_day = activity.findViewById(R.id.day_and_time_LAY_thursday_mid_day);
        day_and_time_LAY_thursday_evening = activity.findViewById(R.id.day_and_time_LAY_thursday_evening);
        day_and_time_LAY_friday_morning = activity.findViewById(R.id.day_and_time_LAY_friday_morning);
        day_and_time_LAY_friday_mid_day = activity.findViewById(R.id.day_and_time_LAY_friday_mid_day);
        day_and_time_LAY_friday_evening = activity.findViewById(R.id.day_and_time_LAY_friday_evening);
        day_and_time_LAY_saturday_morning = activity.findViewById(R.id.day_and_time_LAY_saturday_morning);
        day_and_time_LAY_saturday_mid_day = activity.findViewById(R.id.day_and_time_LAY_saturday_mid_day);
        day_and_time_LAY_saturday_evening = activity.findViewById(R.id.day_and_time_LAY_saturday_evening);

        day_and_time_LBL_find = activity.findViewById(R.id.day_and_time_LBL_find);

    }

    public void findNavViews(AppCompatActivity activity) {
        nav_BNV_bottomNavigation = activity.findViewById(R.id.nav_BNV_bottomNavigation);
    }


    public void moveToSignUp(AppCompatActivity activity) {
        Intent intent = new Intent(activity, SignUpActivity.class);
        activity.startActivity(intent);
        activity.finish();
    }

    public void moveToSignIn(AppCompatActivity activity) {
        Intent intent = new Intent(activity, SignInActivity.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(0, 0);
    }

    public void moveToChooseLocation(AppCompatActivity activity) {
        Intent intent = new Intent(activity, ChooseLocationActivity.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(0, 0);
        activity.finish();
    }

    public void moveToCourtType(AppCompatActivity activity) {
        Intent intent = new Intent(activity, CourtTypeActivity.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(0, 0);
        activity.finish();
    }

    public void moveToSkill(AppCompatActivity activity) {
        Intent intent = new Intent(activity, SkillActivity.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(0, 0);
        activity.finish();
    }

    public void moveToDayAndTime(AppCompatActivity activity) {
        Intent intent = new Intent(activity, DayAndTimeActivity.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(0, 0);
        activity.finish();
    }

    public void moveToNav(AppCompatActivity activity) {
        Intent intent = new Intent(activity, NavActivity.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(0, 0);
        activity.finish();
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
        } else if (sign_up_EDT_name.getText().toString().trim().length() < 2) {
            return 1;
        } else if (isEmpty(sign_up_EDT_email) || !sign_up_EDT_email.getText().toString().contains("@")) {
            return 2;
        } else if (sign_up_EDT_password.getText().toString().trim().length() < 8) {
            return 3;
        } else if (sign_up_EDT_phone.getText().toString().trim().length() != 13)
            return 4;
        else {
            return 5;
        }
    }


    public void checkMarkOn(String checkMark) {

        switch (checkMark) {
            case CEMENT:
                if (isVisible(court_type_IMG_checkMarkHard)) {
                    court_type_IMG_checkMarkHard.setVisibility(View.INVISIBLE);
                } else {
                    court_type_IMG_checkMarkHard.setVisibility(View.VISIBLE);
                }
                break;
            case GRASS:
                if (isVisible(court_type_IMG_checkMarkGrass)) {
                    court_type_IMG_checkMarkGrass.setVisibility(View.INVISIBLE);
                } else {
                    court_type_IMG_checkMarkGrass.setVisibility(View.VISIBLE);
                }
                break;
            case SYNTHETIC:
                if (isVisible(court_type_IMG_checkMarkSynthetic)) {
                    court_type_IMG_checkMarkSynthetic.setVisibility(View.INVISIBLE);
                } else {
                    court_type_IMG_checkMarkSynthetic.setVisibility(View.VISIBLE);
                }
                break;
            case CLAY:
                if (isVisible(court_type_IMG_checkMarkClay)) {
                    court_type_IMG_checkMarkClay.setVisibility(View.INVISIBLE);
                } else {
                    court_type_IMG_checkMarkClay.setVisibility(View.VISIBLE);
                }
                break;
        }
    }

    public ArrayList<String> checkMarkVisibility() {
        ArrayList<String> lst = new ArrayList<>();
        if (isVisible(court_type_IMG_checkMarkHard)) {
            lst.add(CEMENT);
        }
        if (isVisible(court_type_IMG_checkMarkGrass)) {
            lst.add(GRASS);
        }
        if (isVisible(court_type_IMG_checkMarkSynthetic)) {
            lst.add(SYNTHETIC);
        }
        if (isVisible(court_type_IMG_checkMarkClay)) {
            lst.add(CLAY);
        }

        return lst;

    }

    public boolean isVisible(ImageView imageView) {
        return imageView.getVisibility() == View.VISIBLE;
    }

    public boolean tennisBallsAlpha(ImageView imageView) {
        if (imageView.getImageAlpha() == 51) {
            imageView.setImageAlpha(255);
            daysSelected++;
            return true;
        } else {
            imageView.setImageAlpha(51);
            daysSelected--;
            return false;
        }
    }


    public void selectDay(int day) {

        String MORNING = "morning";
        String MID_DAY = "mid day";
        String EVENING = "evening";
        switch (day) {

            case 1:
                if (tennisBallsAlpha(day_and_time_IMG_sunday_morning)) {
                    sunday.add(MORNING);
                } else {
                    sunday.remove(MORNING);
                }
                break;
            case 2:
                if (tennisBallsAlpha(day_and_time_IMG_sunday_mid_day)) {
                    sunday.add(MID_DAY);
                } else {
                    sunday.remove(MID_DAY);
                }
                break;
            case 3:
                if (tennisBallsAlpha(day_and_time_IMG_sunday_evening)) {
                    sunday.add(EVENING);
                } else {
                    sunday.remove(EVENING);
                }
                break;
            case 4:
                if (tennisBallsAlpha(day_and_time_IMG_monday_morning)) {
                    monday.add(MORNING);
                } else {
                    monday.remove(MORNING);
                }
                break;
            case 5:
                if (tennisBallsAlpha(day_and_time_IMG_monday_mid_day)) {
                    monday.add(MID_DAY);
                } else {
                    monday.remove(MID_DAY);
                }
                break;
            case 6:
                if (tennisBallsAlpha(day_and_time_IMG_monday_evening)) {
                    monday.add(EVENING);
                } else {
                    monday.remove(EVENING);
                }
                break;
            case 7:
                if (tennisBallsAlpha(day_and_time_IMG_tuesday_morning)) {
                    tuesday.add(MORNING);
                } else {
                    tuesday.remove(MORNING);
                }
                break;
            case 8:
                if (tennisBallsAlpha(day_and_time_IMG_tuesday_mid_day)) {
                    tuesday.add(MID_DAY);
                } else {
                    tuesday.remove(MID_DAY);
                }
                break;
            case 9:
                if (tennisBallsAlpha(day_and_time_IMG_tuesday_evening)) {
                    tuesday.add(EVENING);
                } else {
                    tuesday.remove(EVENING);
                }
                break;
            case 10:
                if (tennisBallsAlpha(day_and_time_IMG_wednesday_morning)) {
                    wednesday.add(MORNING);
                } else {
                    wednesday.remove(MORNING);
                }
                break;
            case 11:
                if (tennisBallsAlpha(day_and_time_IMG_wednesday_mid_day)) {
                    wednesday.add(MID_DAY);
                } else {
                    wednesday.remove(MID_DAY);
                }
                break;
            case 12:
                if (tennisBallsAlpha(day_and_time_IMG_wednesday_evening)) {
                    wednesday.add(EVENING);
                } else {
                    wednesday.remove(EVENING);
                }
                break;
            case 13:
                if (tennisBallsAlpha(day_and_time_IMG_thursday_morning)) {
                    thursday.add(MORNING);
                } else {
                    thursday.remove(MORNING);
                }
                break;
            case 14:
                if (tennisBallsAlpha(day_and_time_IMG_thursday_mid_day)) {
                    thursday.add(MID_DAY);
                } else {
                    thursday.remove(MID_DAY);
                }
                break;
            case 15:
                if (tennisBallsAlpha(day_and_time_IMG_thursday_evening)) {
                    thursday.add(EVENING);
                } else {
                    thursday.remove(EVENING);
                }
                break;
            case 16:
                if (tennisBallsAlpha(day_and_time_IMG_friday_morning)) {
                    friday.add(MORNING);
                } else {
                    friday.remove(MORNING);
                }
                break;
            case 17:
                if (tennisBallsAlpha(day_and_time_IMG_friday_mid_day)) {
                    friday.add(MID_DAY);
                } else {
                    friday.remove(MID_DAY);
                }
                break;
            case 18:
                if (tennisBallsAlpha(day_and_time_IMG_friday_evening)) {
                    friday.add(EVENING);
                } else {
                    friday.remove(EVENING);
                }
                break;
            case 19:
                if (tennisBallsAlpha(day_and_time_IMG_saturday_morning)) {
                    saturday.add(MORNING);
                } else {
                    saturday.remove(MORNING);
                }
                break;
            case 20:
                if (tennisBallsAlpha(day_and_time_IMG_saturday_mid_day)) {
                    saturday.add(MID_DAY);
                } else {
                    saturday.remove(MID_DAY);
                }
                break;
            case 21:
                if (tennisBallsAlpha(day_and_time_IMG_saturday_evening)) {
                    saturday.add(EVENING);
                } else {
                    saturday.remove(EVENING);
                }
                break;
        }
    }

    public void updateMap() {
        if (!sunday.isEmpty()) {
            playTime.put("sunday", sunday);
        }
        if (!monday.isEmpty()) {
            playTime.put("monday", monday);
        }
        if (!tuesday.isEmpty()) {
            playTime.put("tuesday", tuesday);
        }
        if (!wednesday.isEmpty()) {
            playTime.put("wednesday", wednesday);
        }
        if (!thursday.isEmpty()) {
            playTime.put("thursday", thursday);
        }
        if (!friday.isEmpty()) {
            playTime.put("friday", friday);
        }
        if (!saturday.isEmpty()) {
            playTime.put("saturday", saturday);
        }

    }


    public void isSkillSelected(int level) {

        switch (level) {
            case 1:
                if (skill_IMG_level_one.getImageAlpha() == 255) {
                    skill_IMG_level_one.setImageAlpha(127);
                } else {
                    skill_IMG_level_one.setImageAlpha(255);
                    skill_IMG_level_two.setImageAlpha(127);
                    skill_IMG_level_three.setImageAlpha(127);
                }
                break;
            case 2:
                if (skill_IMG_level_two.getImageAlpha() == 255) {
                    skill_IMG_level_two.setImageAlpha(127);
                } else {
                    skill_IMG_level_two.setImageAlpha(255);
                    skill_IMG_level_one.setImageAlpha(127);
                    skill_IMG_level_three.setImageAlpha(127);
                }
                break;
            case 3:
                if (skill_IMG_level_three.getImageAlpha() == 255) {
                    skill_IMG_level_three.setImageAlpha(127);
                } else {
                    skill_IMG_level_three.setImageAlpha(255);
                    skill_IMG_level_two.setImageAlpha(127);
                    skill_IMG_level_one.setImageAlpha(127);
                }
                break;
        }
    }

    public String checkImageAlpha() {
        if (skill_IMG_level_one.getImageAlpha() == 255) {
            return "Advanced";
        } else if (skill_IMG_level_two.getImageAlpha() == 255) {
            return "Intermediate";
        } else {
            return "Beginner";
        }
    }

    public boolean checkDaySelected() {
        return daysSelected != 0;
    }


    public void setTennisAlpha() {
        skill_IMG_level_one.setImageAlpha(127);
        skill_IMG_level_two.setImageAlpha(127);
        skill_IMG_level_three.setImageAlpha(127);
    }

    public void setBallAlpha() {
        day_and_time_IMG_sunday_morning.setImageAlpha(51);
        day_and_time_IMG_sunday_mid_day.setImageAlpha(51);
        day_and_time_IMG_sunday_evening.setImageAlpha(51);
        day_and_time_IMG_monday_morning.setImageAlpha(51);
        day_and_time_IMG_monday_mid_day.setImageAlpha(51);
        day_and_time_IMG_monday_evening.setImageAlpha(51);
        day_and_time_IMG_tuesday_morning.setImageAlpha(51);
        day_and_time_IMG_tuesday_mid_day.setImageAlpha(51);
        day_and_time_IMG_tuesday_evening.setImageAlpha(51);
        day_and_time_IMG_wednesday_morning.setImageAlpha(51);
        day_and_time_IMG_wednesday_mid_day.setImageAlpha(51);
        day_and_time_IMG_wednesday_evening.setImageAlpha(51);
        day_and_time_IMG_thursday_morning.setImageAlpha(51);
        day_and_time_IMG_thursday_mid_day.setImageAlpha(51);
        day_and_time_IMG_thursday_evening.setImageAlpha(51);
        day_and_time_IMG_friday_morning.setImageAlpha(51);
        day_and_time_IMG_friday_mid_day.setImageAlpha(51);
        day_and_time_IMG_friday_evening.setImageAlpha(51);
        day_and_time_IMG_saturday_morning.setImageAlpha(51);
        day_and_time_IMG_saturday_mid_day.setImageAlpha(51);
        day_and_time_IMG_saturday_evening.setImageAlpha(51);


    }

    public ArrayList<String> jsonToList(AppCompatActivity activity, String filename, String keyword, ArrayList<String> arrayList) {

        String json;
        try {
            InputStream is = activity.getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, StandardCharsets.UTF_8);
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                arrayList.add(obj.getString(keyword));
            }
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }

        return arrayList;
    }

    public void closeKeyboard(AppCompatActivity activity) {
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }


    public TextView getGet_started_LBL_signIn() {
        return get_started_LBL_signIn;
    }

    public Button getGet_started_BTN_getStarted() {
        return get_started_BTN_getStarted;
    }

    public Button getSign_in_BTN_signIn() {
        return sign_in_BTN_signIn;
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

    public ImageView getSign_up_IMG_addProfilePic() {
        return sign_up_IMG_addProfilePic;
    }

    public Button getSign_up_BTN_uploadImage() {
        return sign_up_BTN_uploadImage;
    }

    public EditText getSign_up_EDT_name() {
        return sign_up_EDT_name;
    }

    public EditText getSign_up_EDT_email() {
        return sign_up_EDT_email;
    }

    public EditText getSign_up_EDT_phone() {
        return sign_up_EDT_phone;
    }

    public TextView getSign_up_LBL_play() {
        return sign_up_LBL_play;
    }

    public EditText getSign_up_EDT_password() {
        return sign_up_EDT_password;
    }

    public TextView getChoose_location_LBL_court() {
        return choose_location_LBL_court;
    }

    public AutoCompleteTextView getChoose_location_ACLBL_enterCity() {
        return choose_location_ACLBL_enterCity;
    }

    public RecyclerView getChoose_location_LST_courtsLocations() {
        return choose_location_LST_courtsLocations;
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

    public TextView getSkill_LBL_when_playing() {
        return skill_LBL_when_playing;
    }

    public ImageView getSkill_IMG_level_one() {
        return skill_IMG_level_one;
    }

    public ImageView getSkill_IMG_level_two() {
        return skill_IMG_level_two;
    }

    public ImageView getSkill_IMG_level_three() {
        return skill_IMG_level_three;
    }

    public TextView getDay_and_time_LBL_find() {
        return day_and_time_LBL_find;
    }

    public LinearLayout getDay_and_time_LAY_sunday_morning() {
        return day_and_time_LAY_sunday_morning;
    }

    public LinearLayout getDay_and_time_LAY_sunday_mid_day() {
        return day_and_time_LAY_sunday_mid_day;
    }

    public LinearLayout getDay_and_time_LAY_sunday_evening() {
        return day_and_time_LAY_sunday_evening;
    }

    public LinearLayout getDay_and_time_LAY_monday_morning() {
        return day_and_time_LAY_monday_morning;
    }

    public LinearLayout getDay_and_time_LAY_monday_mid_day() {
        return day_and_time_LAY_monday_mid_day;
    }

    public LinearLayout getDay_and_time_LAY_monday_evening() {
        return day_and_time_LAY_monday_evening;
    }

    public LinearLayout getDay_and_time_LAY_tuesday_morning() {
        return day_and_time_LAY_tuesday_morning;
    }

    public LinearLayout getDay_and_time_LAY_tuesday_mid_day() {
        return day_and_time_LAY_tuesday_mid_day;
    }

    public LinearLayout getDay_and_time_LAY_tuesday_evening() {
        return day_and_time_LAY_tuesday_evening;
    }

    public LinearLayout getDay_and_time_LAY_wednesday_morning() {
        return day_and_time_LAY_wednesday_morning;
    }

    public LinearLayout getDay_and_time_LAY_wednesday_mid_day() {
        return day_and_time_LAY_wednesday_mid_day;
    }

    public LinearLayout getDay_and_time_LAY_wednesday_evening() {
        return day_and_time_LAY_wednesday_evening;
    }

    public LinearLayout getDay_and_time_LAY_thursday_morning() {
        return day_and_time_LAY_thursday_morning;
    }

    public LinearLayout getDay_and_time_LAY_thursday_mid_day() {
        return day_and_time_LAY_thursday_mid_day;
    }

    public LinearLayout getDay_and_time_LAY_thursday_evening() {
        return day_and_time_LAY_thursday_evening;
    }

    public LinearLayout getDay_and_time_LAY_friday_morning() {
        return day_and_time_LAY_friday_morning;
    }

    public LinearLayout getDay_and_time_LAY_friday_mid_day() {
        return day_and_time_LAY_friday_mid_day;
    }

    public LinearLayout getDay_and_time_LAY_friday_evening() {
        return day_and_time_LAY_friday_evening;
    }

    public LinearLayout getDay_and_time_LAY_saturday_morning() {
        return day_and_time_LAY_saturday_morning;
    }

    public LinearLayout getDay_and_time_LAY_saturday_mid_day() {
        return day_and_time_LAY_saturday_mid_day;
    }

    public LinearLayout getDay_and_time_LAY_saturday_evening() {
        return day_and_time_LAY_saturday_evening;
    }

    public BottomNavigationView getNav_BNV_bottomNavigation() {
        return nav_BNV_bottomNavigation;
    }

    public Map<String, ArrayList<String>> getUpdatedMap() {
        return playTime;
    }


}