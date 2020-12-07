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

import com.arlib.floatingsearchview.FloatingSearchView;
import com.example.flatdialoglibrary.dialog.FlatDialog;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import es.dmoral.toasty.Toasty;


/**
 * Fragment to display a single friend from friend list
 */
public class FriendFragment extends Fragment {

    boolean buttonJustChecked = false;

    boolean clickFlag = false; // fix for click registering twice

    RecyclerView recyclerView;
    MoviesRecyclerAdapter moviesRecyclerAdapter;

    List<String[]> moviesList;
//    List<String[]> unfilteredMoviesList;

    String name;
    String email;

    public FriendFragment(String name, String email){
        this.name = name;
        this.email = email;
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
        friendName.setText(name);
        TextView friendEmail = getView().findViewById(R.id.friendEmail);
        friendEmail.setText(email);
        ImageView friendImage = getView().findViewById(R.id.friendImage);
        UsersData usersData = MainActivity.usersData;
        User user = usersData.getUserByEmail(email);
        Picasso.get().load(user.picture).into(friendImage);

        MaterialButtonToggleGroup materialButtonToggleGroup =
                (MaterialButtonToggleGroup) getView().findViewById(R.id.toggleButton);
        int buttonId = materialButtonToggleGroup.getCheckedButtonId();

        MaterialButton button = materialButtonToggleGroup.findViewById(buttonId);

        materialButtonToggleGroup.addOnButtonCheckedListener(
                new MaterialButtonToggleGroup.OnButtonCheckedListener() {
                    @Override
                    public void onButtonChecked(
                        MaterialButtonToggleGroup group, int checkedId, boolean isChecked) {
                            buttonJustChecked = true;
                            FloatingSearchView mSearchView = view.findViewById(R.id.friend_searchBar);
                            mSearchView.clearQuery();
                            mSearchView.clearFocus();
//                            friendName.setText(getResources().getResourceEntryName(checkedId)); // to debug id name corresponding to checkedId
//                            friendName.setText(Integer.toString(checkedId)); // to debug checkedId value
                            if(getResources().getResourceEntryName(checkedId).equals("common_matches") && clickFlag) { // user clicked "show common"
                                moviesList.clear();
                                recyclerView.removeAllViews();
                                populateCommonMatches();
                            }
                            else if(getResources().getResourceEntryName(checkedId).equals("their_list") && clickFlag) {// user clicked "their list"
                                moviesList.clear();
                                recyclerView.removeAllViews();
                                populateTheirList();
                            }
                            clickFlag = !clickFlag;
                        }
                });

        moviesList = new ArrayList<String[]>();

        populateTheirList();

        recyclerView = view.findViewById(R.id.recyclerViewFriend);
        moviesRecyclerAdapter = new MoviesRecyclerAdapter(moviesList);

        recyclerView.setAdapter(moviesRecyclerAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

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

        moviesList.clear();
        recyclerView.removeAllViews();
        populateCommonMatches();

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
        UsersData usersData = MainActivity.usersData;
        User user = usersData.getUserByEmail(MainActivity.userEmail);

        final FlatDialog flatDialog = new FlatDialog(getContext());
        flatDialog.setTitle("REMOVE FRIEND")
                .setSubtitle("Are you sure you want to remove " + name + " from your Friends List?")
                .setFirstButtonText("CONFIRM")
                .setSecondButtonText("CANCEL")
                .withFirstButtonListner(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        flatDialog.dismiss();
                        if(user.friendsList.contains(email)) {
                            user.friendsList.remove(email);

                            Toasty.normal(view.getContext(), email + " was removed from your Friends List", Toast.LENGTH_SHORT).show();

                            ((FragmentActivity) getView().getContext()).getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.fragment_container_view, new FriendsFragment())
                                    .commit();
                        }
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
}
