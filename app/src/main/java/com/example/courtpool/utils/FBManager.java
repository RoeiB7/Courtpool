package com.example.courtpool.utils;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class FBManager {

    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;

    public FBManager() {
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
    }

    public FirebaseAuth getFirebaseAuth() {
        return firebaseAuth;
    }

    public FirebaseFirestore getFirebaseFirestore() {
        return firebaseFirestore;
    }

    public String getUserID() {
        return firebaseAuth.getCurrentUser().getUid();
    }
}
