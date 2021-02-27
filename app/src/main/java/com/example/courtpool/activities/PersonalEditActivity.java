package com.example.courtpool.activities;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.MimeTypeMap;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.courtpool.R;
import com.example.courtpool.utils.AppManager;
import com.example.courtpool.utils.FBManager;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.storage.StorageReference;

import java.util.Objects;

public class PersonalEditActivity extends AppCompatActivity {

    private EditText name, phone;
    private ImageView profilePic;
    private AppCompatButton button;
    private TextView finishEdit;
    private FBManager fbManager;
    private AppManager manager;
    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri mImageUri;
    private StorageReference fileReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_edit);

        findViews();
        fbManager = new FBManager();
        manager = new AppManager(this);
        initViews();

    }

    private void findViews() {
        name = findViewById(R.id.personal_edit_EDT_name);
        phone = findViewById(R.id.personal_edit_EDT_phone);
        profilePic = findViewById(R.id.personal_edit_IMG_addProfilePic);
        button = findViewById(R.id.personal_edit_BTN_uploadImage);
        finishEdit = findViewById(R.id.personal_edit_LBL_finish);

    }

    private void initViews() {

        phone.addTextChangedListener(new TextWatcher() {
            int keyDel;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                phone.setOnKeyListener((v, keyCode, event) -> {

                    if (keyCode == KeyEvent.KEYCODE_DEL)
                        keyDel = 1;
                    return false;
                });

                if (keyDel == 0) {
                    int len = phone.getText().length();
                    if (len == 3) {
                        phone.setText(phone.getText().toString() + " - ");
                        phone.setSelection(phone.getText().length());
                    }
                } else {
                    keyDel = 0;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        finishEdit.setOnClickListener(v -> {

            switch (checkEditFields()) {

                case 0:
                    Toast.makeText(this, "One or more fields are empty", Toast.LENGTH_LONG).show();
                    break;
                case 1:
                    name.setError("Your name must contain at least 2 letter");
                    manager.closeKeyboard(this);
                    break;
                case 2:
                    phone.setError("Your phone number must contain 10 digits");
                    manager.closeKeyboard(this);
                    break;
                case 3:
                    DocumentReference documentReference = fbManager.getFirebaseFirestore().collection("users").document(fbManager.getUserID());
                    documentReference.update(FBManager.KEY_NAME, name.getText().toString().trim(), FBManager.KEY_PHONE, phone.getText().toString().trim())
                            .addOnSuccessListener(aVoid -> {
                                Log.d(AppManager.TAG, "user updated");
                                uploadImage();
                                manager.moveToNav(this);
                            })

                            .addOnFailureListener(e -> {
                                Toast.makeText(this, "Error! " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            });

                    break;

            }
        });

        button.setOnClickListener(v -> openFileChooser());

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

                    DocumentReference documentReference = fbManager.getFirebaseFirestore()
                            .collection("users").document(fbManager.getUserID());

                    documentReference.update(FBManager.KEY_IMAGE, downloadUri.toString());

                } else {
                    Toast.makeText(this, "upload failed: " + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            DocumentReference documentReference = fbManager.getFirebaseFirestore()
                    .collection("users").document(fbManager.getUserID());

            documentReference.update(FBManager.KEY_IMAGE, "N/A");
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
            if (mImageUri != null) {
                Glide.with(this).load(mImageUri).apply(RequestOptions.circleCropTransform()).into(profilePic);
            }
        }

    }


    public int checkEditFields() {
        if (manager.isEmpty(name) || manager.isEmpty(phone)) {
            return 0;
        } else if (name.getText().toString().trim().length() < 2) {
            return 1;
        } else if (phone.getText().toString().trim().length() != 13)
            return 2;
        else {
            return 3;
        }
    }


}