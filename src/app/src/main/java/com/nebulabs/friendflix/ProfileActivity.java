package com.nebulabs.friendflix;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.squareup.picasso.Picasso;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        populateData();

        Button logout = (Button) findViewById(R.id.logout_button);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });
    }

    public void populateData(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        TextView user_name = findViewById(R.id.userName);
        TextView user_email = findViewById(R.id.userEmail);
        ImageView profile_image = findViewById(R.id.profileImage);

        user_name.setText(user.getDisplayName());
        Log.d("FIRE",user.getPhotoUrl().toString());
        Log.d("FIRE",user.getEmail());

        Picasso.get().load(user.getPhotoUrl()).into(profile_image);
        user_email.setText(user.getEmail());
    }

    public void signOut() {
        // [START auth_sign_out]
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(this, FirebaseLoginActivity.class);
        startActivity(intent);
        finish();
        // [END auth_sign_out]
    }
}
