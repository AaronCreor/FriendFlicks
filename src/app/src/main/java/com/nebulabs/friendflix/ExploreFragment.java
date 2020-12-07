package com.nebulabs.friendflix;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

import es.dmoral.toasty.Toasty;

/**
 * Fragment to display the user profile and logout button
 */
public class ExploreFragment extends Fragment {

    String movieID;

    TextView movieTitle;
    TextView movieYear;
    ImageView moviePoster;
    TextView movieSynopsis;

    JSONObject obj;

    String movieTitleValue;
    String movieYearValue;
    String moviePosterValue;
    String movieSynopsisValue;

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

        movieTitle = getView().findViewById(R.id.explore_movietitle);
        movieYear = getView().findViewById(R.id.explore_movieyear);
        moviePoster = getView().findViewById(R.id.explore_movieposter);
        movieSynopsis = getView().findViewById(R.id.explore_moviesynopsis);


        renderMovieInfo();

        if(!MainActivity.exploreScreenAlreadyCreated) {
            try {
                showRandomMovie();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        MainActivity.exploreScreenAlreadyCreated = true;

        MaterialButton skip = view.findViewById(R.id.explore_skipbutton);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    showRandomMovie();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        MaterialButton like = view.findViewById(R.id.explore_likebutton);
        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    // put movie in friendList [implement this here]
                    String userEmail = MainActivity.userEmail;
                    UsersData usersData = MainActivity.usersData;
                    User user = usersData.getUserByEmail(userEmail);
                    user.addMovie(movieID, movieTitleValue, Integer.parseInt(movieYearValue),  moviePosterValue);
                    Toasty.info(view.getContext(), movieID, Toast.LENGTH_SHORT).show();

                    // and then...
                    showRandomMovie();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void showRandomMovie() throws IOException {
        movieID = generateRandomMovieID();
        String getRequestURL = "https://www.omdbapi.com/?apikey=" + Omdb.KEY + "&i=" + movieID;

        Thread t = new Thread() {
            public void run() {
                MainActivity.responseExplore = Omdb.sendGetRequest(getRequestURL);
            }
        };
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        renderMovieInfo();
    }

    public void renderMovieInfo() {
        try {
            obj = new JSONObject(MainActivity.responseExplore);
            movieID = obj.getString("imdbID");
            movieTitleValue = obj.getString("Title");
            movieYearValue = obj.getString("Year");
            moviePosterValue = obj.getString("Poster");
            movieSynopsisValue = obj.getString("Plot");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        movieTitle.setText(movieTitleValue);
        movieYear.setText(movieYearValue);
        Picasso.get().load(moviePosterValue).into(moviePoster);
        movieSynopsis.setText(movieSynopsisValue);
    }

    String generateRandomMovieID() throws IOException {
//        int min = 1;
//        int max = 2404811;
//
//        //Generate random int value from 1 to 999999999
//        int random_int = (int)(Math.random() * (max - min + 1) + min);
//        String trailingIDnumber = Integer.toString(random_int);
//        if(trailingIDnumber.length() < 7) {
//            int zeroesToBeAdded = 7 - trailingIDnumber.length();
//            String zeroesString = "";
//            while(zeroesString.length() < zeroesToBeAdded)
//                zeroesString = zeroesString + "0";
//            trailingIDnumber = zeroesString + trailingIDnumber;
//        }
//        return "tt" + trailingIDnumber;

        // With csv file
        InputStream is = getResources().openRawResource(R.raw.imdbmovies);
        ArrayList<String> movies = Omdb.fillImdbArray(is);
//        Generate random int value from 0 to movies.length
        int min = 0;
        int max = movies.size();
        int random_int = (int)(Math.random() * (max - min + 1) + min);
        String movieID = movies.get(random_int);

        // check if this movieID is already in the user's "My List"
        // if it is, run the movieID generator again
        String userEmail = MainActivity.userEmail;
        UsersData usersData = MainActivity.usersData;
        User user = usersData.getUserByEmail(userEmail);
        Iterator<Movie> movieIterator = user.movieList.iterator();
        while(movieIterator.hasNext()) {
            Movie currentMovie = movieIterator.next();
            if(currentMovie != null) {
                if(currentMovie.id.equals(movieID)) {
                    return generateRandomMovieID();
                }
            }
        }

        return movieID;
    }

}
