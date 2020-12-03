package com.nebulabs.friendflix;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

public class ProfileFragment extends Fragment {

    public ProfileFragment(){
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        populateData();

        Button logout = (Button) view.findViewById(R.id.logout_button);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });
    }

    /*
        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.fragment_profile);

    //        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    //        getSupportActionBar().setHomeButtonEnabled(true);
    //        getSupportActionBar().setTitle("Profile");

            populateData();

            Button logout = (Button) findViewById(R.id.logout_button);
            logout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    signOut();
                }
            });
        }
    */
    public void populateData(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        TextView user_name = getView().findViewById(R.id.userName);
        TextView user_email = getView().findViewById(R.id.userEmail);
        ImageView profile_image = getView().findViewById(R.id.userImage);

        user_name.setText(user.getDisplayName());
//        Log.d("FIRE",user.getPhotoUrl().toString());
//        Log.d("FIRE",user.getEmail());
//        Log.d("FIRE",user.getUid());

        Picasso.get().load(user.getPhotoUrl()).into(profile_image);
        user_email.setText(user.getEmail());
    }

    public void signOut() {
        // [START auth_sign_out]
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(getActivity(), FirebaseLoginActivity.class);
        startActivity(intent);
        getActivity().finish();
        // [END auth_sign_out]
    }
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_home:
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

 */


}
