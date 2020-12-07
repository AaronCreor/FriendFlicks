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

import com.google.android.material.button.MaterialButton;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

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

    int pressed = 0;

    public MovieFragment(String movieID){
        this.movieID = movieID;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
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

        // Like Button
        MaterialButton movieFavButton = view.findViewById(R.id.movie_likebutton);

        Movie thisMovie = new Movie(movieID, movieTitleValue, Integer.parseInt(movieYearValue), moviePosterValue);
        String userEmail = MainActivity.userEmail;
        UsersData usersData = MainActivity.usersData;
        User user = usersData.getUserByEmail(userEmail);

        if(user.movieList.contains(thisMovie))
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

                // add/remove movie logic
                if(user.movieList.contains(thisMovie)) {
                    user.movieList.remove(thisMovie);
                    movieFavButton.setText("Like");
                }
                else {
                    user.movieList.add(thisMovie);
                    movieFavButton.setText("Remove");
                }

//                Toasty.info(view.getContext(), movieID, Toast.LENGTH_SHORT).show();

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
}

