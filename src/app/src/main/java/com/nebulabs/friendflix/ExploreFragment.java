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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Fragment to display the user profile and logout button
 */
public class ExploreFragment extends Fragment {

    public ExploreFragment(){
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_explore,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView theResponse = getView().findViewById(R.id.explore_mainText);
        theResponse.setText(MainActivity.response);

        if(!MainActivity.exploreScreenAlreadyCreated)
            showRandomMovie();
        MainActivity.exploreScreenAlreadyCreated = true;

        Button skip = (Button) view.findViewById(R.id.explore_skip_button);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRandomMovie();
            }
        });
    }

    public void showRandomMovie() {
        String movieID = generateRandomMovieID();
        String getRequestURL = "https://www.omdbapi.com/?apikey=" + Omdb.KEY + "&i=" + movieID;
        TextView theResponse = getView().findViewById(R.id.explore_mainText);

        Thread t = new Thread() {
            public void run() {
                MainActivity.response = Omdb.sendGetRequest(getRequestURL);
            }
        };
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        final JSONObject obj;
        try {
            obj = new JSONObject(MainActivity.response);
            MainActivity.response = obj.getString("Title");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        theResponse.setText(MainActivity.response);
    }

    String generateRandomMovieID() {
        int min = 1;
        int max = 2404811;

        //Generate random int value from 1 to 999999999
        int random_int = (int)(Math.random() * (max - min + 1) + min);
        String trailingIDnumber = Integer.toString(random_int);
        if(trailingIDnumber.length() < 7) {
            int zeroesToBeAdded = 7 - trailingIDnumber.length();
            String zeroesString = "";
            while(zeroesString.length() < zeroesToBeAdded)
                zeroesString = zeroesString + "0";
            trailingIDnumber = zeroesString + trailingIDnumber;
        }
        return "tt" + trailingIDnumber;
    }

}
