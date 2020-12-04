package com.nebulabs.friendflix;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import es.dmoral.toasty.Toasty;

/**
 * Fragment to display a single movie from movie list
 */
public class MovieFragment extends Fragment {

    String name;

    int pressed = 0;

    public MovieFragment(String name){
        this.name = name;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movie,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView title = getView().findViewById(R.id.movieTitle);
        title.setText(name);

        // Back Button
        ImageButton movieBackButton = view.findViewById(R.id.movie_back_button);
        movieBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((FragmentActivity) view.getContext()).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container_view, new MyListFragment())
                        .commit();
            }
        });

        // Like Button
        ImageButton movieFavButton = view.findViewById(R.id.movie_like_button);
        movieFavButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pressed == 1){
                    movieFavButton.setImageResource(R.drawable.movie_favourite);
                    v.getBackground().setColorFilter(getResources().getColor(R.color.colorPrimary),PorterDuff.Mode.SRC_ATOP);
                    pressed = 0;
                } else {
                    v.getBackground().setColorFilter(Color.WHITE,PorterDuff.Mode.SRC_ATOP);
                    movieFavButton.setImageResource(R.drawable.ic_baseline_favorite_24);
                    pressed = 1;
                }
            }
        });
    }
}
