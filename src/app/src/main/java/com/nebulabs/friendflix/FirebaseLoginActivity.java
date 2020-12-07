package com.nebulabs.friendflix;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import es.dmoral.toasty.Toasty;

/**
 * Activity screen for login
 */
public class FirebaseLoginActivity extends AppCompatActivity {
    private static final int RC_SIGN_IN = 123;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_login);

        checkCurrentUser();
        Button googleSignInButton = (Button) findViewById(R.id.googleLogin);
        googleSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createSignInIntent();
            }
        });
    }


    /**
     * Check if user already logged in and bypass login screen
     * Else display a sign in request toast to user
     */
    public void checkCurrentUser() {
        // [START check_current_user]
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // user is signed in
            Intent i = new Intent(this, MainActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
            Toasty.success(getApplicationContext(),"Logged In", Toast.LENGTH_SHORT).show();
        } else {
            // No user is signed in
            Toasty.warning(getApplicationContext(),"Please SIGN IN",Toast.LENGTH_SHORT).show();
        }
        // [END check_current_user]
    }

    /**
     * Initiate firebase sign in process
     */
    public void createSignInIntent() {
        // [START auth_fui_create_intent]
        // Choose authentication providers
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.GoogleBuilder().build());

        // Create and launch sign-in intent
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .build(),
                RC_SIGN_IN);
        // [END auth_fui_create_intent]
    }

    /**
     * Check result of firebase login and display main activity if login authorized
     * @param requestCode
     * @param resultCode
     * @param data
     */
    // [START auth_fui_result]
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK) {
                // Successfully signed in

//                Toast toast = Toast.makeText(getApplicationContext(),
//                        "Sign In Success!",
//                        Toast.LENGTH_SHORT);
//
//                toast.show();

                Toasty.success(getApplicationContext(),"Sign In Success            !",Toast.LENGTH_SHORT).show();

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();

            } else {
                // Sign in failed. If response is null the user canceled the
                // sign-in flow using the back button. Otherwise check
                // response.getError().getErrorCode() and handle the error.
                // ...

//                Toast toast = Toast.makeText(getApplicationContext(),
//                        "Sign In Failed!",
//                        Toast.LENGTH_SHORT);
//
//                toast.show();

                Toasty.error(getApplicationContext(),"Sign In Failed!",Toast.LENGTH_SHORT).show();

            }
        }
    }
    // [END auth_fui_result]

    /**
     * Sign out of the app
     */
    public void signOut() {
        // [START auth_fui_signout]
        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    public void onComplete(@NonNull Task<Void> task) {
                        // ...
                    }
                });
        // [END auth_fui_signout]
    }

}
