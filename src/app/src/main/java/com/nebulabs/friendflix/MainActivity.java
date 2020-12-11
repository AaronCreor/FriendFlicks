package com.nebulabs.friendflix;

import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.KeyEvent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.android.volley.RequestQueue;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseUser;

import java.io.InputStream;

import es.dmoral.toasty.Toasty;

/**
 * Main activity screen containing all fragments and navigation views
 */
public class MainActivity extends AppCompatActivity {

    public static String userEmail = "jonnyboy@gmail.com";
    public static UsersData usersData = new UsersData();
//    public static MoviesData moviesData = new MoviesData();
    public static String responseExplore = "If you see this, then something went wrong";
    public static String responseMovie = "If you see this, then something went wrong";
    public static boolean exploreScreenAlreadyCreated = false;
    public static boolean movieScreenAlreadyCreated = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomTabView = findViewById(R.id.bottom_navigation);
        bottomTabView.setOnNavigationItemSelectedListener(navListener);

        bottomTabView.getMenu().getItem(0).setChecked(true);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_view,new ExploreFragment()).commit();
    }

    /**
     * Navigation listener for top navigation bar
     */
    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFrag = null;

            switch (item.getItemId()) {
                case R.id.exploreTab:
                    selectedFrag = new ExploreFragment();
                    break;
                case R.id.accountTab:
                    selectedFrag = new ProfileFragment();
                    break;
                case R.id.groupsTab:
                    selectedFrag = new FriendsFragment();
                    break;
                case R.id.moviesTab:
                    selectedFrag = new MyListFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_view,selectedFrag).commit();

            return true;
        }
    };
/*
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
*/
    boolean doubleBackToExitPressedOnce = false;

    /**
     * Handle back presses from activity screen
     */
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toasty.info(this, "Please click BACK again to exit", Toasty.LENGTH_SHORT).show();


        BottomNavigationView bottomTabView = findViewById(R.id.bottom_navigation);
        bottomTabView.getMenu().getItem(0).setChecked(true);

        Fragment selectedFrag = null;
        selectedFrag = new MyListFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_view,selectedFrag).commit();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }

}