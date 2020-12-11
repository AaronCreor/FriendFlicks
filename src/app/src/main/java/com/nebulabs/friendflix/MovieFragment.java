package com.nebulabs.friendflix;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.DividerItemDecoration;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.arlib.floatingsearchview.FloatingSearchView;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import es.dmoral.toasty.Toasty;

/**
 * Fragment to display a single movie from movie list
 */
public class MovieFragment extends Fragment {

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

    ArrayList<String> userMovies;

    public RequestQueue MyRequestQueue;
    public FirebaseUser user;
    public String userID;

    int pressed = 0;

    public MovieFragment(String movieID){
        this.movieID = movieID;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyRequestQueue = Volley.newRequestQueue(getActivity());
        user = FirebaseAuth.getInstance().getCurrentUser();
        userID = user.getUid();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        checkIfLiked();
        return inflater.inflate(R.layout.fragment_movie,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        movieTitle = getView().findViewById(R.id.movie_movietitle);
        movieYear = getView().findViewById(R.id.movie_movieyear);
        moviePoster = getView().findViewById(R.id.movie_movieposter);
        movieSynopsis = getView().findViewById(R.id.movie_moviesynopsis);

        try {
            showMovie();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Back Button
        MaterialButton movieBackButton = view.findViewById(R.id.movie_backbutton);
        movieBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((FragmentActivity) view.getContext()).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container_view, new MyListFragment())
                        .commit();
            }
        });
    }

    public void showMovie() throws IOException {
        String getRequestURL = "https://www.omdbapi.com/?apikey=" + Omdb.KEY + "&i=" + movieID;

        Thread t = new Thread() {
            public void run() {
                MainActivity.responseMovie = Omdb.sendGetRequest(getRequestURL);
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
            obj = new JSONObject(MainActivity.responseMovie);
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


    public void setButtonText(boolean liked) {
        // Like Button
        MaterialButton movieFavButton = getView().findViewById(R.id.movie_likebutton);

        Movie thisMovie = new Movie(movieID, movieTitleValue, Integer.parseInt(movieYearValue), moviePosterValue);
        String userEmail = MainActivity.userEmail;
        UsersData usersData = MainActivity.usersData;
        User user = usersData.getUserByEmail(userEmail);

        if(liked)
            movieFavButton.setText("Remove");
        else
            movieFavButton.setText("Like");
        movieFavButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pressed == 1){
//                    movieFavButton.setImageResource(R.drawable.movie_favourite);
                    v.getBackground().setColorFilter(getResources().getColor(R.color.colorPrimary),PorterDuff.Mode.SRC_ATOP);
                    pressed = 0;
                } else {
                    v.getBackground().setColorFilter(Color.WHITE,PorterDuff.Mode.SRC_ATOP);
//                    movieFavButton.setImageResource(R.drawable.ic_baseline_favorite_24);
                    pressed = 1;
                }

                if(movieFavButton.getText().toString().contains("Like")) {
                    postMovieToUser();
                }

                else {
                    removeMovieFromUser();
                }

//                Toasty.info(view.getContext(), movieID, Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void checkIfLiked() {
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

                                if(movie.getString("mid").contains(movieID)) {
                                    setButtonText(true);
                                    return;
                                }
                                else {
                                }
//
//                                // Display the formatted json data in text view
//                                mTextView.append(firstName +" " + lastName +"\nAge : " + age);
//                                mTextView.append("\n\n");
                            }
                            setButtonText(false);
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

    public void postMovieToUser(){
        String url = "https://friendflix.herokuapp.com/addmovie/" + userID + "/" + movieID;
        StringRequest MyStringRequest = new StringRequest(Request.Method.PUT, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                checkIfLiked();
                Toasty.success(getView().getContext(), "Added to My List", Toast.LENGTH_SHORT).show();
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

    public void removeMovieFromUser(){
        String url = "https://friendflix.herokuapp.com/removemovie/" + userID + "/" + movieID;
        StringRequest MyStringRequest = new StringRequest(Request.Method.PUT, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                checkIfLiked();
                Toasty.info(getView().getContext(), "Removed from My List", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {
                //This code is executed if there is an error.
            }
        }) {
//            protected Map<String, String> getParams() {
//                Map<String, String> MyData = new HashMap<String, String>();
//                MyData.put("userid", userID);
//                MyData.put("mid", movieID);
//                return MyData;
//            }
        };


        MyRequestQueue.add(MyStringRequest);
    }
}

