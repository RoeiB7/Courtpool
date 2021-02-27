package com.example.courtpool.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ContactsFragment extends Fragment {

    private View view;
    private User user;
    private ArrayList<User> contacts;
    private RecyclerView fragment_contacts_LST_players;
    private Adapter_Players adapter_players;
    private FBManager fbManager;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_contacts, container, false);
        findViews(view);
        fbManager = new FBManager();
        contacts = new ArrayList<>();
        user = new User();
        getContacts();
        return view;
    }

    private void findViews(View view) {
        fragment_contacts_LST_players = view.findViewById(R.id.fragment_contacts_LST_players);
    }

    public void getContacts() {
        fbManager.getFirebaseFirestore().collection("users").
                document(fbManager.getUserID()).collection(FBManager.KEY_CONTACTS).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot documentSnapshots) {
                if (documentSnapshots.isEmpty()) {
                    Log.d("empty", "onSuccess: LIST EMPTY");
                    return;
                } else {
                    for (DocumentSnapshot ds : documentSnapshots.getDocuments()) {
                        user = ds.toObject(User.class);
                        contacts.add(user);
                    }
                    showContacts();
                }
            }
        });
    }

    private void showContacts() {
        adapter_players = new Adapter_Players(view.getContext(), contacts);
        showUser();
        removeContact();
        fragment_contacts_LST_players.setLayoutManager(new LinearLayoutManager(view.getContext()));
        fragment_contacts_LST_players.setAdapter(adapter_players);

    }

    private void showUser() {

        adapter_players.setClickListener((view, position) -> {
            Intent intent = new Intent(getActivity(), PlayerProfileActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("player1", contacts.get(position));
            intent.putExtra("player2", bundle);
            startActivity(intent);
        });

    }


    private void removeContact() {
        adapter_players.setLongClickListener(new Adapter_Players.ItemLongClickListener() {
            @Override
            public void onLongClick(View v, int position) {
                new MaterialAlertDialogBuilder(view.getContext())
                        .setTitle("Remove Contact")
                        .setMessage("Do you wish to remove this contact ?")
                        .setPositiveButton("Yes", (dialog, which) -> {


                            fbManager.getFirebaseFirestore().collection("users").
                                    document(fbManager.getUserID())
                                    .collection(FBManager.KEY_CONTACTS)
                                    .document(contacts.get(position).getName() + " " + contacts.get(position).getId()).delete()
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                contacts.remove(position);
                                                adapter_players.notifyItemRemoved(position);
                                                adapter_players.notifyItemRangeRemoved(position, contacts.size());
                                            }
                                        }
                                    });


                        })
                        .setNegativeButton("No", null)
                        .show();
            }
        });

    }
}
