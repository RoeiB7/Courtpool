package com.example.courtpool.utils;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.Objects;

public class FBManager {

    private final FirebaseAuth firebaseAuth;
    private final FirebaseFirestore firebaseFirestore;
    private final StorageReference storageReference;


    public static final String KEY_NAME = "name";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_PHONE = "phone";
    public static final String KEY_LOCATION = "courtLocation";
    public static final String KEY_TYPE = "courtTypes";
    public static final String KEY_TIME = "playTime";
    public static final String KEY_SKILL = "skill";
    public static final String KEY_IMAGE = "profileImage";
    public static final String KEY_CONTACTS = "contacts";

    public FBManager() {
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference("uploads");
    }

    public FirebaseAuth getFirebaseAuth() {
        return firebaseAuth;
    }

    public FirebaseFirestore getFirebaseFirestore() {
        return firebaseFirestore;
    }

    public String getUserID() {
        return Objects.requireNonNull(firebaseAuth.getCurrentUser()).getUid();
    }

    public StorageReference getStorageReference() {
        return storageReference;
    }
}
