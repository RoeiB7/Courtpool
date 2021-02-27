package com.example.courtpool.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
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
import com.example.courtpool.activities.PlayerProfileActivity;
import com.example.courtpool.objects.User;
import com.example.courtpool.utils.Adapter_Players;
import com.example.courtpool.utils.FBManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.Map;

public class MatchesFragment extends Fragment {

    private View view;
    private ArrayList<User> matched_players;
    private ArrayList<String> matched_locations;
    private ArrayList<String> userLocations;
    private ArrayList<String> myCourts;
    private FBManager fbManager;
    private RecyclerView fragment_matches_LST_players;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_matches, container, false);
        findViews(view);
        fbManager = new FBManager();
        getMyCourts();
        showPlayers();
        return view;
    }


    private void findViews(View view) {
        fragment_matches_LST_players = view.findViewById(R.id.fragment_matches_LST_players);

    }


    private void showPlayers() {
        matched_players = new ArrayList<>();
        matched_locations = new ArrayList<>();

        fbManager.getFirebaseFirestore().collection("users").get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {

                            if (!document.getId().equals(fbManager.getUserID())) {
                                String id = document.getId();
                                String name = document.getString(FBManager.KEY_NAME);
                                String email = document.getString(FBManager.KEY_EMAIL);
                                String password = document.getString(FBManager.KEY_PASSWORD);
                                String phone = document.getString(FBManager.KEY_PHONE);
                                String skill = document.getString(FBManager.KEY_SKILL);
                                String image = document.getString(FBManager.KEY_IMAGE);

                                userLocations = (ArrayList<String>) document.getData().get(FBManager.KEY_LOCATION);

                                for (int i = 0; i < userLocations.size(); i++) {
                                    if (myCourts.contains(userLocations.get(i))) {
                                        matched_locations.add(userLocations.get(i));
                                    }
                                }


                                if (!matched_locations.isEmpty()) {
                                    ArrayList<String> types = (ArrayList<String>) document.getData().get(FBManager.KEY_TYPE);
                                    Map<String, ArrayList<String>> time = (Map<String, ArrayList<String>>) document.getData().get(FBManager.KEY_TIME);


                                    User user = new User(id, name, email, password, phone, matched_locations, types, skill, time, image);

                                    matched_players.add(user);
                                }
                            }

                        }

                        Adapter_Players adapter_players = new Adapter_Players(view.getContext(), matched_players);


                        adapter_players.setClickListener((view, position) -> {
                            Intent intent = new Intent(getActivity(), PlayerProfileActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("player1", matched_players.get(position));
                            intent.putExtra("player2", bundle);
                            startActivity(intent);

                        });

                        adapter_players.setLongClickListener((v, position) -> {

                            User user = matched_players.get(position);
                            DocumentReference documentReference = fbManager.getFirebaseFirestore()
                                    .collection("users")
                                    .document(fbManager.getUserID())
                                    .collection(FBManager.KEY_CONTACTS)
                                    .document(user.getName() + " " + user.getId());

                            documentReference.set(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if (task.isSuccessful()) {
                                        Toast toast = Toast.makeText(view.getContext(), "Saved!", Toast.LENGTH_SHORT);
                                        toast.setGravity(Gravity.CENTER, 0, 0);
                                        toast.show();
                                    } else {
                                        Toast toast = Toast.makeText(view.getContext(), "Faild to save!", Toast.LENGTH_SHORT);
                                        toast.setGravity(Gravity.CENTER, 0, 0);
                                        toast.show();
                                    }

                                }
                            });


                        });

                        fragment_matches_LST_players.setLayoutManager(new LinearLayoutManager(view.getContext()));
                        fragment_matches_LST_players.setAdapter(adapter_players);
                    }
                })

                .addOnFailureListener(e -> {
                    Toast.makeText(view.getContext(), "Error! " + e.getMessage(), Toast.LENGTH_SHORT).show();

                });


    }


    public void getMyCourts() {
        DocumentReference documentReference = fbManager.getFirebaseFirestore().collection("users").document(fbManager.getUserID());
        documentReference.addSnapshotListener((documentSnapshot, error) -> {
            if (error != null) {
                return;
            }
            if (documentSnapshot.exists()) {
                myCourts = (ArrayList<String>) documentSnapshot.getData().get(FBManager.KEY_LOCATION);
            }
        });

    }


}