package com.example.courtpool.activities;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.webkit.MimeTypeMap;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.courtpool.R;
import com.example.courtpool.objects.Upload;
import com.example.courtpool.utils.AppManager;
import com.example.courtpool.utils.Courts_Creator;
import com.example.courtpool.utils.FBManager;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SignUpActivity extends AppCompatActivity {

    private AppManager manager;
    private EditText name, email, phone, password;
    private ImageView profilePic;
    private boolean eye = false;
    private boolean crossEye = false;
    private final int DRAWABLE_RIGHT = 2;
    private FBManager fbManager;
    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri mImageUri;
    private TextView play;
    private StorageReference fileReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        manager = new AppManager(this);
        manager.findSignUpViews(this);
        fbManager = new FBManager();
        initViews();

    }


    @SuppressLint("ClickableViewAccessibility")
    private void initViews() {

        profilePic = manager.getSign_up_IMG_addProfilePic();
        password = manager.getSign_up_EDT_password();
        name = manager.getSign_up_EDT_name();
        email = manager.getSign_up_EDT_email();
        phone = manager.getSign_up_EDT_phone();


        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (count != before && !crossEye) {
                    eye = manager.showEye(password);
                    crossEye = false;
                } else {
                    crossEye = true;
                    if (s.toString().isEmpty()) {
                        eye = manager.showEye(password);
                        password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        crossEye = false;
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        password.setOnTouchListener((v, event) -> {
            if (eye) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getX() >= (password.getWidth() - password.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        manager.switchPasswordVisibility(password);
                        return true;
                    }
                }
            }
            return false;
        });

        play = manager.getSign_up_LBL_play();
        play.setOnClickListener(v -> {

            switch (manager.checkFields()) {

                case 0:
                    Toast.makeText(this, "One or more fields are empty", Toast.LENGTH_LONG).show();
                    break;
                case 1:
                    name.setError("Your name must contain at least 2 letter");
                    break;
                case 2:
                    email.setError("Please fix your email format, i.e example@example.com");
                    break;
                case 3:
                    password.setError("Your password must contain at least 8 characters");
                    break;
                case 4:
                    phone.setError("Your phone number must contain 10 digits");
                    break;
                case 5:
                    fbManager.getFirebaseAuth().createUserWithEmailAndPassword(email.getText().toString().trim(), password.getText().toString().trim())
                            .addOnSuccessListener(authResult -> {
                                Log.d("ptt", "user created");
                                addUserToDB();
                                Courts_Creator courts_creator = new Courts_Creator();
                                courts_creator.createCourts();
                                uploadImage();
                                manager.moveToChooseLocation(this);
                            })
                            .addOnFailureListener(e -> {
                                Toast.makeText(this, "Error! " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            });
                    break;

            }
        });


        profilePic.setOnClickListener(v -> {
            openFileChooser();

        });
    }


    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    private void uploadImage() {

        if (mImageUri != null) {
            fileReference = fbManager.getStorageReference().child(System.currentTimeMillis()
                    + "." + getFileExtension(mImageUri));

            fileReference.putFile(mImageUri).continueWithTask(task -> {
                if (!task.isSuccessful()) {
                    throw Objects.requireNonNull(task.getException());
                }
                return fileReference.getDownloadUrl();
            }).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Uri downloadUri = task.getResult();

                    Upload upload = new Upload(name.getText().toString().trim() + "'s profile picture",
                            downloadUri.toString());

                    DocumentReference documentReference = fbManager.getFirebaseFirestore()
                            .collection("users").document(fbManager.getUserID());

                    documentReference.update("profilePic", upload);

                } else {
                    Toast.makeText(SignUpActivity.this, "upload failed: " + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            mImageUri = data.getData();
            Glide.with(this).load(mImageUri).apply(RequestOptions.circleCropTransform()).into(profilePic);

        }

    }


    private void addUserToDB() {
        DocumentReference documentReference = fbManager.getFirebaseFirestore().collection("users").document(fbManager.getUserID());
        Map<String, Object> user = new HashMap<>();
        user.put("fullName", name.getText().toString().trim());
        user.put("email", email.getText().toString().trim());
        user.put("password", password.getText().toString().trim());
        user.put("phoneNumber", phone.getText().toString().trim());

        documentReference.set(user)
                .addOnSuccessListener(aVoid -> {
                    Log.d("ptt", "onSuccess: user profile is created for " + fbManager.getUserID());
                })
                .addOnFailureListener(e -> {
                    Log.d("ptt", "onFailure: " + e.toString());
                });

    }


}