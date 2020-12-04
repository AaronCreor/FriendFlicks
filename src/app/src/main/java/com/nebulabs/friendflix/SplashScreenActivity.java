package com.nebulabs.friendflix;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 * Activity to display a splash screen to allow data pre-loading
 */
public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(this, FirebaseLoginActivity.class);
            startActivity(intent);
            finish();

        }, 1000);
    }
}
