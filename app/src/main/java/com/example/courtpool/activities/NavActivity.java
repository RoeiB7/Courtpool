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

    private AppManager manager;
    private BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav);

        manager = new AppManager(this);
        manager.findNavViews(this);
        bottomNav = manager.getNav_BNV_bottomNavigation();
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.nav_fragment_container, new ProfileFragment()).commit();


    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            item -> {
                Fragment selectedFragment = null;
                switch (item.getItemId()) {

                    case R.id.nav_profile:
                        selectedFragment = new ProfileFragment();
                        break;
                    case R.id.nav_matches:
                        selectedFragment = new MatchesFragment();
                        break;
                    case R.id.nav_contacts:
                        selectedFragment = new ContactsFragment();
                        break;

                }
                getSupportFragmentManager().beginTransaction().replace(R.id.nav_fragment_container,
                        selectedFragment).commit();

                return true;

            };
}