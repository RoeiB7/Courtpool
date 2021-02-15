package com.example.courtpool.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.courtpool.R;
import com.example.courtpool.fragments.ContactsFragment;
import com.example.courtpool.fragments.MatchesFragment;
import com.example.courtpool.fragments.ProfileFragment;
import com.example.courtpool.utils.AppManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class NavActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav);

        AppManager manager = new AppManager(this);
        manager.findNavViews(this);
        BottomNavigationView bottomNav = manager.getNav_BNV_bottomNavigation();
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.nav_fragment_container, new ProfileFragment()).commit();


    }

    private final BottomNavigationView.OnNavigationItemSelectedListener navListener =
            item -> {
                Fragment selectedFragment = null;

                if (item.getItemId() == R.id.nav_profile) {
                    selectedFragment = new ProfileFragment();
                } else if (item.getItemId() == R.id.nav_matches) {
                    selectedFragment = new MatchesFragment();
                } else {
                    selectedFragment = new ContactsFragment();
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.nav_fragment_container,
                        selectedFragment).commit();
                return true;
            };
}