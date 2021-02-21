package com.example.courtpool.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.courtpool.R;
import com.example.courtpool.objects.User;
import com.example.courtpool.utils.Adapter_Matches;
import com.example.courtpool.utils.Adapter_Profile_Court;
import com.example.courtpool.utils.FBManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class MatchesFragment extends Fragment {

    private View view;
    private ArrayList<User> matched_players;
    private FBManager fbManager;
    private RecyclerView fragment_matches_LST_players;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_matches, container, false);
        findViews(view);
        fbManager = new FBManager();
        showPlayers();
        return view;
    }


    private void findViews(View view) {
        fragment_matches_LST_players = view.findViewById(R.id.fragment_matches_LST_players);

    }


    private void showPlayers() {
        matched_players = new ArrayList<>();
        fbManager.getFirebaseFirestore().collection("users").get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {

                            if (!document.getId().equals(fbManager.getUserID())) {
                                String name = document.getString(FBManager.KEY_NAME);
                                String email = document.getString(FBManager.KEY_EMAIL);
                                String password = document.getString(FBManager.KEY_PASSWORD);
                                String phone = document.getString(FBManager.KEY_PHONE);
                                String skill = document.getString(FBManager.KEY_SKILL);
                                String image = document.getString(FBManager.KEY_IMAGE);


                                //TODO: ADD CONDITIONS FOR ADDING PLAYER, ONLY WITH THE SAME COURTS

                                ArrayList<String> locations = (ArrayList<String>) document.getData().get(FBManager.KEY_LOCATION);
                                ArrayList<String> types = (ArrayList<String>) document.getData().get(FBManager.KEY_TYPE);
                                Map<String, ArrayList<String>> time = (Map<String, ArrayList<String>>) document.getData().get(FBManager.KEY_TIME);

                                User user = new User(name, email, password, phone, locations, types, skill, time, image);

                                matched_players.add(user);
                            }

                        }

                        Adapter_Matches adapter_matches = new Adapter_Matches(view.getContext(), matched_players);
                        adapter_matches.setClickListener(new Adapter_Matches.ItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {

                                //TODO: MOVE TO FULL PLAYER PROFILE

                            }
                        });

                        adapter_matches.setLongClickListener(new Adapter_Matches.ItemLongClickListener() {
                            @Override
                            public void onLongClick(View v, int position) {

                                //TODO: SAVE PLAYER TO CONTACTS

                            }
                        });


                        fragment_matches_LST_players.setLayoutManager(new LinearLayoutManager(view.getContext()));
                        fragment_matches_LST_players.setAdapter(adapter_matches);
                    }
                })

                .addOnFailureListener(e -> {
                    Toast.makeText(view.getContext(), "Error! " + e.getMessage(), Toast.LENGTH_SHORT).show();

                });


    }
}
