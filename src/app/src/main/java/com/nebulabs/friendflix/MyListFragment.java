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

import com.arlib.floatingsearchview.FloatingSearchView;
import com.example.flatdialoglibrary.dialog.FlatDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Fragment to display movie list of a user
 */
public class MyListFragment extends Fragment {

    RecyclerView recyclerView;
    MoviesRecyclerAdapter moviesRecyclerAdapter;

    List<String[]> moviesList;

    public MyListFragment(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mylist,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        moviesList = new ArrayList<String[]>();

        String userEmail = MainActivity.userEmail;
        UsersData usersData = MainActivity.usersData;
        User user = usersData.getUserByEmail(userEmail);
        Iterator<Movie> movieIterator = user.movieList.iterator();
        while(movieIterator.hasNext()) {
            Movie currentMovie = movieIterator.next();
//            Movie currentMovie = MainActivity.moviesData.getMovieByID(currentMovieID);
            if(currentMovie != null) {
                String[] input = new String[3];
                input[0] = currentMovie.id;
                input[1] = currentMovie.name;
                input[2] = Integer.toString(currentMovie.year);
                moviesList.add(0, input);
            }
        }

        recyclerView = view.findViewById(R.id.recyclerViewMyList);
        moviesRecyclerAdapter = new MoviesRecyclerAdapter(moviesList);
        recyclerView.setAdapter(moviesRecyclerAdapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        FloatingSearchView mSearchView = view.findViewById(R.id.mylist_searchBar);
        mSearchView.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {
            @Override
            public void onSearchTextChanged(String oldQuery, final String newQuery) {
                moviesRecyclerAdapter.getFilter().filter(newQuery);
            }
        });
    }
}
