package com.example.courtpool.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.courtpool.R;
import com.example.courtpool.activities.ChooseLocationActivity;
import com.example.courtpool.activities.CourtTypeActivity;
import com.example.courtpool.activities.DayAndTimeActivity;
import com.example.courtpool.activities.PersonalEditActivity;
import com.example.courtpool.activities.SignInActivity;
import com.example.courtpool.activities.SkillActivity;
import com.example.courtpool.objects.Court;
import com.example.courtpool.utils.Adapter_Profile_Court;
import com.example.courtpool.utils.FBManager;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.firestore.DocumentReference;

import java.util.ArrayList;
import java.util.Map;


public class ProfileFragment extends Fragment {

    private ImageView fragment_profile_IMG_profilePic,
            fragment_profile_IMG_editSkill,
            fragment_profile_IMG_editType,
            fragment_profile_IMG_editTime,
            fragment_profile_IMG_editCourts;

    private TextView fragment_profile_LBL_name,
            fragment_profile_LBL_number,
            fragment_profile_LBL_email,
            fragment_profile_LBL_skill,
            fragment_profile_LBL_changeHours;

    private Spinner fragment_profile_LST_courtType, fragment_profile_LST_days;
    private RecyclerView fragment_profile_LST_courts;
    private AppCompatButton fragment_profile_BTN_editPersonal;

    private ArrayList<String> hours, days_strings, courts_strings;
    private Map<String, ArrayList<String>> playTimeMap;

    private View view;
    private FBManager fbManager;
    private boolean isEdit = false;


    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        findViews(view);
        fbManager = new FBManager();
        retrieveData();


        return view;


    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.actionbar_settings, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.action_bar_edit) {

            if (!isEdit) {
                fragment_profile_IMG_editSkill.setVisibility(View.VISIBLE);
                fragment_profile_IMG_editType.setVisibility(View.VISIBLE);
                fragment_profile_IMG_editTime.setVisibility(View.VISIBLE);
                fragment_profile_IMG_editCourts.setVisibility(View.VISIBLE);
                fragment_profile_BTN_editPersonal.setVisibility(View.VISIBLE);
                isEdit = true;
            } else {
                fragment_profile_IMG_editSkill.setVisibility(View.INVISIBLE);
                fragment_profile_IMG_editType.setVisibility(View.INVISIBLE);
                fragment_profile_IMG_editTime.setVisibility(View.INVISIBLE);
                fragment_profile_IMG_editCourts.setVisibility(View.INVISIBLE);
                fragment_profile_BTN_editPersonal.setVisibility(View.INVISIBLE);
                isEdit = false;
            }
            fragment_profile_IMG_editSkill.setOnClickListener(v -> {
                Intent intent = new Intent(getActivity(), SkillActivity.class);
                intent.putExtra("edit", 1);
                startActivity(intent);
                getActivity().overridePendingTransition(0, 0);
            });

            fragment_profile_IMG_editType.setOnClickListener(v -> {
                Intent intent = new Intent(getActivity(), CourtTypeActivity.class);
                intent.putExtra("edit", 1);
                startActivity(intent);
                getActivity().overridePendingTransition(0, 0);
            });

            fragment_profile_IMG_editTime.setOnClickListener(v -> {
                Intent intent = new Intent(getActivity(), DayAndTimeActivity.class);
                intent.putExtra("edit", 1);
                startActivity(intent);
                getActivity().overridePendingTransition(0, 0);
            });

            fragment_profile_IMG_editCourts.setOnClickListener(v -> {
                Intent intent = new Intent(getActivity(), ChooseLocationActivity.class);
                intent.putExtra("edit", 1);
                startActivity(intent);
                getActivity().overridePendingTransition(0, 0);
            });

            fragment_profile_BTN_editPersonal.setOnClickListener(v -> {
                Intent intent = new Intent(getActivity(), PersonalEditActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(0, 0);
            });

        } else if (item.getItemId() == R.id.action_bar_logout) {
            new MaterialAlertDialogBuilder(view.getContext())
                    .setTitle("Log out")
                    .setMessage("Do you wish to log out from your account?")
                    .setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
                        fbManager.getFirebaseAuth().signOut();
                        Intent intent = new Intent(getActivity(), SignInActivity.class);
                        startActivity(intent);
                        getActivity().finish();
                        getActivity().overridePendingTransition(0, 0);
                    })
                    .setNegativeButton("No", null)
                    .show();
        }

        return true;
    }

    private void findViews(View view) {
        fragment_profile_IMG_profilePic = view.findViewById(R.id.fragment_profile_IMG_profilePic);
        fragment_profile_LBL_name = view.findViewById(R.id.fragment_profile_LBL_name);
        fragment_profile_LBL_number = view.findViewById(R.id.fragment_profile_LBL_number);
        fragment_profile_LBL_email = view.findViewById(R.id.fragment_profile_LBL_email);
        fragment_profile_LBL_skill = view.findViewById(R.id.fragment_profile_LBL_skill);
        fragment_profile_LST_courtType = view.findViewById(R.id.fragment_profile_LST_courtType);
        fragment_profile_LST_courts = view.findViewById(R.id.fragment_profile_LST_courts);
        fragment_profile_LBL_changeHours = view.findViewById(R.id.fragment_profile_LBL_changeHours);
        fragment_profile_LST_days = view.findViewById(R.id.fragment_profile_LST_days);
        fragment_profile_IMG_editSkill = view.findViewById(R.id.fragment_profile_IMG_editSkill);
        fragment_profile_IMG_editType = view.findViewById(R.id.fragment_profile_IMG_editType);
        fragment_profile_IMG_editTime = view.findViewById(R.id.fragment_profile_IMG_editTime);
        fragment_profile_IMG_editCourts = view.findViewById(R.id.fragment_profile_IMG_editCourts);
        fragment_profile_BTN_editPersonal = view.findViewById(R.id.fragment_profile_BTN_editPersonal);
    }

    private void retrieveData() {
        DocumentReference documentReference = fbManager.getFirebaseFirestore().collection("users").document(fbManager.getUserID());

        documentReference.addSnapshotListener((documentSnapshot, error) -> {
            if (error != null) {
                return;
            }
            if (documentSnapshot.exists()) {
                if (documentSnapshot.getString(FBManager.KEY_IMAGE).equals("N/A")) {
                    Glide.with(view).load(R.drawable.ic_user).apply(RequestOptions.circleCropTransform()).into(fragment_profile_IMG_profilePic);
                } else {
                    Glide.with(view).load(documentSnapshot.getString(FBManager.KEY_IMAGE)).apply(RequestOptions.circleCropTransform()).into(fragment_profile_IMG_profilePic);
                }
                fragment_profile_LBL_name.setText(documentSnapshot.getString(FBManager.KEY_NAME));
                fragment_profile_LBL_number.setText(documentSnapshot.getString(FBManager.KEY_PHONE));
                fragment_profile_LBL_email.setText(documentSnapshot.getString(FBManager.KEY_EMAIL));
                fragment_profile_LBL_skill.setText(documentSnapshot.getString(FBManager.KEY_SKILL));

                ArrayList<String> types = (ArrayList<String>) documentSnapshot.getData().get(FBManager.KEY_TYPE);

                ArrayAdapter<String> type_adapter = new ArrayAdapter<>(view.getContext(), R.layout.spinner_item, types);
                fragment_profile_LST_courtType.setAdapter(type_adapter);

                courts_strings = (ArrayList<String>) documentSnapshot.getData().get(FBManager.KEY_LOCATION);

                ArrayList<Court> courts = new ArrayList<>();
                for (int i = 0; i < courts_strings.size(); i = i + 2) {
                    String court_name = courts_strings.get(i);
                    String court_address = courts_strings.get(i + 1);
                    courts.add(new Court(court_name, court_address));
                }

                Adapter_Profile_Court adapter_profile_court = new Adapter_Profile_Court(view.getContext(), courts);
                fragment_profile_LST_courts.setLayoutManager(new LinearLayoutManager(view.getContext()));
                fragment_profile_LST_courts.setAdapter(adapter_profile_court);


                playTimeMap = (Map<String, ArrayList<String>>) documentSnapshot.getData().get(FBManager.KEY_TIME);
                days_strings = new ArrayList<>(playTimeMap.keySet());
                ArrayAdapter<String> days_adapter = new ArrayAdapter<>(view.getContext(), R.layout.spinner_item, days_strings);
                fragment_profile_LST_days.setAdapter(days_adapter);
                hours = new ArrayList<>();


                fragment_profile_LST_days.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        String day = parent.getItemAtPosition(position).toString();
                        hours = playTimeMap.get(day);

                        StringBuilder sb = new StringBuilder();
                        for (int i = 0; i < hours.size(); i++) {
                            sb.append(hours.get(i));
                            if (hours.size() - 1 > i) {
                                sb.append(", ");
                            }

                        }
                        fragment_profile_LBL_changeHours.setText(sb);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }
        });
    }
}