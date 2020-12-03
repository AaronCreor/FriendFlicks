package com.nebulabs.friendflix;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {

    public void replaceFragments(Class fragmentClass) {
        Fragment fragment = null;
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_container_view, fragment)
                .commit();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomTabView = findViewById(R.id.bottom_navigation);
        bottomTabView.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_view,new MyListFragment()).commit();


    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFrag = null;

            switch (item.getItemId()) {
                case R.id.accountTab:
                    selectedFrag = new ProfileFragment();
                    break;
                case R.id.groupsTab:
                    selectedFrag = new GroupsFragment();
                    break;
                case R.id.moviesTab:
                    selectedFrag = new MyListFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_view,selectedFrag).commit();

            return true;
        }
    };

}