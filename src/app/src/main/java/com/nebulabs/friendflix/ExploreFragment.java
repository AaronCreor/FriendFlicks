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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import es.dmoral.toasty.Toasty;

/**
 * Fragment to display the user profile and logout button
 */
public class ExploreFragment extends Fragment {

    RequestQueue MyRequestQueue;
    FirebaseUser user;
    String userID;

    String movieID;

    ArrayList<String> userMovies;

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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyRequestQueue = Volley.newRequestQueue(getActivity());
        user = FirebaseAuth.getInstance().getCurrentUser();
        userID = user.getUid();
        getUserMovies();
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
                    // put movie in movieList
                    Toasty.success(view.getContext(), "Added to My List", Toast.LENGTH_SHORT).show();
                    postMovieToUser();

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

        postMovie(movieID, movieTitleValue, movieYearValue, moviePosterValue);
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
        Iterator<String> movieIterator = userMovies.iterator();
        while(movieIterator.hasNext()) {
            String currentMovie = movieIterator.next();
            if(currentMovie != null) {
                if(currentMovie.equals(movieID)) {
                    return generateRandomMovieID();
                }
            }
        }

        return movieID;
    }

    public void postMovie(final String mid, final String moviename, final String year, final String posterurl){
        String url = "https://friendflix.herokuapp.com/movies/create"; // <----enter your post url here
        StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //This code is executed if the server responds, whether or not the response contains data.
                //The String 'response' contains the server's response.
            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {
                //This code is executed if there is an error.
            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> MyData = new HashMap<String, String>();
                MyData.put("mid", mid);
                MyData.put("moviename", moviename);
                MyData.put("year", year);
                MyData.put("posterurl", posterurl);
                return MyData;
            }
        };


        MyRequestQueue.add(MyStringRequest);
    }

    public void postMovieToUser(){
        String url = "https://friendflix.herokuapp.com/addmovie/" + userID + "/" + movieID;
        StringRequest MyStringRequest = new StringRequest(Request.Method.PUT, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {
                //This code is executed if there is an error.
            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> MyData = new HashMap<String, String>();
                MyData.put("userid", userID);
                MyData.put("mid", movieID);
                return MyData;
            }
        };


        MyRequestQueue.add(MyStringRequest);
    }

    public void getUserMovies() {
        userMovies = new ArrayList<String>();
        String url = "https://friendflix.herokuapp.com/users/" + userID + "/" + "getmovies";

        // Initialize a new JsonArrayRequest instance
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // Do something with response
                        //mTextView.setText(response.toString());

                        // Process the JSON
                        try{
                            // Loop through the array elements
                            for(int i=0;i<response.length();i++){
                                // Get current json object
                                JSONObject movie = response.getJSONObject(i);

                                // Get the current movie (json object) data
                                userMovies.add(0, movie.getString("mid"));
//
//                                // Display the formatted json data in text view
//                                mTextView.append(firstName +" " + lastName +"\nAge : " + age);
//                                mTextView.append("\n\n");
                            }
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        // Do something when error occurred
//                        Snackbar.make(
//                                mCLayout,
//                                "Error...",
//                                Snackbar.LENGTH_LONG
//                        ).show();
                    }
                }
        );

        // Add JsonArrayRequest to the RequestQueue
        MyRequestQueue.add(jsonArrayRequest);
    }

}
