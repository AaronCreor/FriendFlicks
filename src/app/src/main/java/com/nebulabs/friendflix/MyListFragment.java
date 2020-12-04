package com.nebulabs.friendflix;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

        // Search Fab
        FloatingActionButton searchFab = view.findViewById(R.id.search_mylist_fab);
        searchFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final FlatDialog flatDialog = new FlatDialog(getContext());
                flatDialog.setTitle("Search Movie")
                        .setSubtitle("Enter movie name")
                        .setFirstTextFieldHint("movie name")
                        .setFirstButtonText("SEARCH")
                        .setSecondButtonText("CANCEL")
                        .withFirstButtonListner(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                //TODO: ADD SEARCH SUPPORT
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
        });

        moviesList = new ArrayList<String[]>();

        recyclerView = view.findViewById(R.id.recyclerViewMyList);
        moviesRecyclerAdapter = new MoviesRecyclerAdapter(moviesList);

//        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(moviesRecyclerAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        String userEmail = MainActivity.userEmail;
        UsersData usersData = MainActivity.usersData;
        User user = usersData.getUserByEmail(userEmail);
        Iterator<Integer> movieIterator = user.movieList.iterator();
        while(movieIterator.hasNext()) {
            int currentMovieID = movieIterator.next();
            Movie currentMovie = MainActivity.moviesData.getMovieByID(currentMovieID);
            if(currentMovie != null) {
                String[] input = new String[2];
                input[0] = currentMovie.name;
                input[1] = Integer.toString(currentMovie.year);
                moviesList.add(input);
            }
        }

//        moviesList.add("Iron Man");
//        moviesList.add("The Incredible Hulk");
//        moviesList.add("Iron Man 2");
//        moviesList.add("Thor");
//        moviesList.add("Captain America: The First Avenger");
//        moviesList.add("The Avengers");
//        moviesList.add("Iron Man 3");
//        moviesList.add("Thor: The Dark World");
//        moviesList.add("Captain America: The Winter Soldier");
//        moviesList.add("Guardians of the Galaxy");
//        moviesList.add("Avengers: Age of Ultron");
//        moviesList.add("Ant-Man");
//        moviesList.add("Captain America: Civil War");
//        moviesList.add("Doctor Strange");
//        moviesList.add("Guardians of the Galaxy Vol. 2");
//        moviesList.add("Spider-Man: Homecoming");
//        moviesList.add("Thor: Ragnarok");
//        moviesList.add("Black Panther");
//        moviesList.add("Avengers: Infinity War");
//        moviesList.add("Ant-Man and the Wasp");
//        moviesList.add("Captain Marvel");
//        moviesList.add("Avengers: Endgame");
//        moviesList.add("Spider-Man: Far From Home");
    }

}
