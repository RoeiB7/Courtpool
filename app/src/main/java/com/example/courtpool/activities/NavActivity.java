package com.example.courtpool.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.courtpool.R;
import com.example.courtpool.fragments.ContactsFragment;
import com.example.courtpool.fragments.MatchesFragment;
import com.example.courtpool.fragments.ProfileFragment;
import com.example.courtpool.utils.AppManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class NavActivity extends AppCompatActivity {

    private MatchesFragment matchesFragment;
    private ProfileFragment profileFragment;
    private ContactsFragment contactsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav);

        AppManager manager = new AppManager(this);
        manager.findNavViews(this);
        
        profileFragment = new ProfileFragment();
        matchesFragment = new MatchesFragment();
        contactsFragment = new ContactsFragment();

        BottomNavigationView bottomNav = manager.getNav_BNV_bottomNavigation();
        getSupportFragmentManager().beginTransaction().replace(R.id.nav_fragment_container, profileFragment).commit();

        bottomNav.setOnNavigationItemSelectedListener(item -> {
            Fragment selectedFragment;

            if (item.getItemId() == R.id.nav_profile) {
                selectedFragment = profileFragment;
            } else if (item.getItemId() == R.id.nav_matches) {
                selectedFragment = matchesFragment;
            } else {
                selectedFragment = contactsFragment;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.nav_fragment_container,
                    selectedFragment).commit();
            return true;
        });
    }


}