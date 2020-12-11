package com.nebulabs.friendflix;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.arlib.floatingsearchview.FloatingSearchView;
import com.example.flatdialoglibrary.dialog.FlatDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Fragment to display movie list of a user
 */
public class MyListFragment extends Fragment {

    RecyclerView recyclerView;
    MoviesRecyclerAdapter moviesRecyclerAdapter;

    ArrayList<String> userMovies;
    List<String[]> moviesList;
    ArrayList<Movie> allMovies;

    public RequestQueue MyRequestQueue;
    public FirebaseUser user;
    public String userID;

    String movieID;



    public MyListFragment(){}

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
        return inflater.inflate(R.layout.fragment_mylist,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        moviesList = new ArrayList<String[]>();

        getMovies();




//        String userEmail = MainActivity.userEmail;
//        UsersData usersData = MainActivity.usersData;
//        User user = usersData.getUserByEmail(userEmail);
//        Iterator<Movie> movieIterator = user.movieList.iterator();
//        while(movieIterator.hasNext()) {
//            Movie currentMovie = movieIterator.next();
////            Movie currentMovie = MainActivity.moviesData.getMovieByID(currentMovieID);
//            if(currentMovie != null) {
//                String[] input = new String[4];
//                input[0] = currentMovie.id;
//                input[1] = currentMovie.name;
//                input[2] = Integer.toString(currentMovie.year);
//                input[3] = currentMovie.poster;
//                moviesList.add(0, input);
//            }
//        }


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


                                Iterator<Movie> allMoviesIterator = allMovies.iterator();
                                while(allMoviesIterator.hasNext()) {
                                    Movie currentAllMovie = allMoviesIterator.next();
                                    if(movie.getString("mid").contains(currentAllMovie.id)) {
                                        String input[] = new String[4];
                                        input[0] = currentAllMovie.id;
                                        input[1] = currentAllMovie.name;
                                        input[2] = Integer.toString(currentAllMovie.year);
                                        input[3] = currentAllMovie.poster;
                                        moviesList.add(0, input);
                                    }
                                }
//
//                                // Display the formatted json data in text view
//                                mTextView.append(firstName +" " + lastName +"\nAge : " + age);
//                                mTextView.append("\n\n");
                            }
                            recyclerView = getView().findViewById(R.id.recyclerViewMyList);
                            moviesRecyclerAdapter = new MoviesRecyclerAdapter(moviesList);
                            recyclerView.setAdapter(moviesRecyclerAdapter);
                            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
                            recyclerView.addItemDecoration(dividerItemDecoration);

                            FloatingSearchView mSearchView = getView().findViewById(R.id.mylist_searchBar);
                            mSearchView.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {
                                @Override
                                public void onSearchTextChanged(String oldQuery, final String newQuery) {
                                    moviesRecyclerAdapter.getFilter().filter(newQuery);
                                }
                            });
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

    public void getMovies() {
        allMovies = new ArrayList<Movie>();
        String url = "https://friendflix.herokuapp.com/movies";

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
                                String mid = movie.getString("mid");
                                String moviename = movie.getString("moviename");
                                String year = movie.getString("year");
                                String posterurl = movie.getString("posterurl");
                                String[] input = new String[4];
                                allMovies.add(new Movie(mid, moviename, Integer.parseInt(year), posterurl));
//
//                                // Display the formatted json data in text view
//                                mTextView.append(firstName +" " + lastName +"\nAge : " + age);
//                                mTextView.append("\n\n");
                            }
                            getUserMovies();
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
