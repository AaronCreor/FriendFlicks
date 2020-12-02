package com.nebulabs.friendflix;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 1;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        firebaseAuth = com.google.firebase.auth.FirebaseAuth.getInstance();
        //this is where we start the Auth state Listener to listen for whether the user is signed in or not
        authStateListener = new FirebaseAuth.AuthStateListener(){
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                // Get signedIn user
                FirebaseUser user = firebaseAuth.getCurrentUser();

                //if user is signed in, we call a helper method to save the user details to Firebase
                if (user != null) {
                    // User is signed in
                    // you could place other firebase code
                    //logic to save the user details to Firebase
                    Log.d("FriendFlix", "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d("FriendFlix", "onAuthStateChanged:signed_out");
                }
            }
        };

        GoogleSignInOptions gso =  new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.firebase_webClient_id))//you can also use R.string.default_web_client_id
                .requestEmail()
                .build();

        SignInButton googleButton = (SignInButton) findViewById(R.id.google_button);
        googleButton.setSize(SignInButton.SIZE_STANDARD);
        /*
        googleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(intent,RC_SIGN_IN);

                Toast toast = Toast.makeText(getApplicationContext(),
                        "LOGIN FAILED!",
                        Toast.LENGTH_SHORT);

                toast.show();
            }
        });

         */


    }
}