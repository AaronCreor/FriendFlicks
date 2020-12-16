package com.nebulabs.friendflix;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.DividerItemDecoration;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.arlib.floatingsearchview.FloatingSearchView;
import com.example.flatdialoglibrary.dialog.FlatDialog;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import es.dmoral.toasty.Toasty;


/**
 * Fragment to display a single friend from friend list
 */
public class FriendFragment extends Fragment {

    boolean buttonJustChecked = false;

    boolean clickFlag = false; // fix for click registering twice

    RecyclerView recyclerView;
    MoviesRecyclerAdapter moviesRecyclerAdapter;

    String id;
    String name;
    String email;
    String picture;

    ArrayList<String> userMovies;
    List<String[]> moviesList;
    ArrayList<Movie> allMovies;

    public RequestQueue MyRequestQueue;
    public FirebaseUser user;
    public String userID;

    public FriendFragment(String id, String name, String email, String picture){
        this.id = id;
        this.name = name;
        this.email = email;
        this.picture = picture;
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
        return inflater.inflate(R.layout.fragment_friend,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageButton friendBackButton = view.findViewById(R.id.friend_backButton);
        friendBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((FragmentActivity) view.getContext()).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container_view, new FriendsFragment())
                        .commit();
            }
        });

        TextView friendName = getView().findViewById(R.id.friendName);
        String[] splitStr = name.split("\\s+");
        name = splitStr[0];
        friendName.setText(name);
        TextView friendEmail = getView().findViewById(R.id.friendEmail);
        friendEmail.setText(email);
        ImageView friendImage = getView().findViewById(R.id.friendImage);
        Picasso.get().load(picture).into(friendImage);



        moviesList = new ArrayList<String[]>();

//        populateTheirList();
        getMovies();



        FloatingSearchView mSearchView = view.findViewById(R.id.friend_searchBar);
        mSearchView.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {
            @Override
            public void onSearchTextChanged(String oldQuery, final String newQuery) {
                if(newQuery.equals("") && buttonJustChecked) {
                    // do nothing
                    buttonJustChecked = false;
                }
                else {
                    moviesRecyclerAdapter.getFilter().filter(newQuery);
                }
            }
        });
        mSearchView.setDismissOnOutsideClick(true);
        mSearchView.setOnFocusChangeListener(new FloatingSearchView.OnFocusChangeListener() {
            @Override
            public void onFocus() {

            }

            @Override
            public void onFocusCleared() {
                mSearchView.clearQuery();
            }
        });

//        moviesList.clear();
//        recyclerView.removeAllViews();
//        populateCommonMatches();

        FloatingActionButton addGroup_fab = view.findViewById(R.id.friend_remove);
        addGroup_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toasty.info(getContext(),"ADD GROUP",Toasty.LENGTH_SHORT).show();
                removeFriend();
            }
        });
    }

    void populateTheirList() {
        UsersData usersData = MainActivity.usersData;
        User friend = usersData.getUserByEmail(email);
        Iterator<Movie> movieIterator = friend.movieList.iterator();
        while(movieIterator.hasNext()) {
            Movie currentMovie = movieIterator.next();
//            Movie currentMovie = MainActivity.moviesData.getMovieByID(currentMovieID);
            if(currentMovie != null) {
                String[] input = new String[4];
                input[0] = currentMovie.id;
                input[1] = currentMovie.name;
                input[2] = Integer.toString(currentMovie.year);
                input[3] = currentMovie.poster;
                moviesList.add(0, input);
            }
        }
//        this.unfilteredMoviesList = new ArrayList<String[]>(moviesList);
    }

    void populateCommonMatches() {
        UsersData usersData = MainActivity.usersData;
        User user = usersData.getUserByEmail(MainActivity.userEmail);
        User friend = usersData.getUserByEmail(email);
        Iterator<Movie> movieIterator = friend.movieList.iterator();
        while(movieIterator.hasNext()) {
            Movie currentMovie = movieIterator.next();
            if(user.movieList.contains(currentMovie)) {
                if(currentMovie != null) {
                    String[] input = new String[4];
                    input[0] = currentMovie.id;
                    input[1] = currentMovie.name;
                    input[2] = Integer.toString(currentMovie.year);
                    input[3] = currentMovie.poster;
                    moviesList.add(0, input);
                }
            }
        }
    }

    void removeFriend() {

        final FlatDialog flatDialog = new FlatDialog(getContext());
        flatDialog.setTitle("REMOVE FRIEND")
                .setSubtitle("Are you sure you want to remove " + name + " from your Friends List?")
                .setFirstButtonText("CONFIRM")
                .setSecondButtonText("CANCEL")
                .withFirstButtonListner(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        flatDialog.dismiss();
                        // call remove friend function
                        removeFriendnow();

                            // move this stuff into that function's response


                    }
                })
                .withSecondButtonListner(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        flatDialog.dismiss();
                    }
                })
                .show();
    }




    public void getUserMovies() {
        userMovies = new ArrayList<String>();
        String url = "https://friendflix.herokuapp.com/users/" + id + "/" + "getmovies";

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
                            recyclerView = getView().findViewById(R.id.recyclerViewFriend);
                            moviesRecyclerAdapter = new MoviesRecyclerAdapter(moviesList);
                            recyclerView.setAdapter(moviesRecyclerAdapter);
                            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
                            recyclerView.addItemDecoration(dividerItemDecoration);

                            FloatingSearchView mSearchView = getView().findViewById(R.id.friend_searchBar);
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

    public void removeFriendnow(){
        String url = "https://friendflix.herokuapp.com/removefriend/" + userID + "/" + id;
        StringRequest MyStringRequest = new StringRequest(Request.Method.PUT, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //This code is executed if the server responds, whether or not the response contains data.
                //The String 'response' contains the server's response.
                Toasty.normal(getView().getContext(), email + " was removed from your Friends List", Toast.LENGTH_SHORT).show();

                ((FragmentActivity) getView().getContext()).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container_view, new FriendsFragment())
                        .commit();
            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {
                //This code is executed if there is an error.
            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> MyData = new HashMap<String, String>();
                MyData.put("userid1", userID);
                MyData.put("userid2", id);
                return MyData;
            }
        };


        MyRequestQueue.add(MyStringRequest);
    }
}
