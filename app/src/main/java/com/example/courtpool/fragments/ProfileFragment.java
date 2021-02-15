package com.example.courtpool.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.courtpool.R;
import com.example.courtpool.activities.SignInActivity;
import com.example.courtpool.utils.FBManager;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.firestore.DocumentReference;


public class ProfileFragment extends Fragment {

    private ImageView fragment_profile_IMG_profilePic;
    private TextView fragment_profile_LBL_name;
    private TextView fragment_profile_LBL_number;
    private TextView fragment_profile_LBL_email;
    private View view;
    private FBManager fbManager;


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
            new MaterialAlertDialogBuilder(view.getContext())
                    .setTitle("Edit Profile")
                    .setMessage("Do you wish to edit your profile?")
                    .setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
                        //TODO: ADD FUNCTIONALITY FOR EDIT PROFILE
                    })
                    .setNegativeButton("No", null)
                    .show();


        } else if (item.getItemId() == R.id.action_bar_logout) {
            new MaterialAlertDialogBuilder(view.getContext())
                    .setTitle("Log out")
                    .setMessage("Do you wish to log out from your account?")
                    .setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
                        fbManager.getFirebaseAuth().signOut();
                        Intent intent = new Intent(getActivity(), SignInActivity.class);
                        startActivity(intent);
                        ((Activity) getActivity()).overridePendingTransition(0, 0);
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
            }

        });


    }
}
