package com.nebulabs.friendflix;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.DividerItemDecoration;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.button.MaterialButtonToggleGroup;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * Fragment to display a single friend from friend list
 */
public class FriendFragment extends Fragment {

    boolean clickFlag = false; // fix for click registering twice

    RecyclerView recyclerView;
    MoviesRecyclerAdapter moviesRecyclerAdapter;

    List<String> moviesList;

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
        TextView friendName = getView().findViewById(R.id.friendName);
        friendName.setText(name);
        TextView friendEmail = getView().findViewById(R.id.friendEmail);
        friendEmail.setText(email);

        MaterialButtonToggleGroup materialButtonToggleGroup =
                (MaterialButtonToggleGroup) getView().findViewById(R.id.toggleButton);
        int buttonId = materialButtonToggleGroup.getCheckedButtonId();

        MaterialButton button = materialButtonToggleGroup.findViewById(buttonId);

        materialButtonToggleGroup.addOnButtonCheckedListener(
                new MaterialButtonToggleGroup.OnButtonCheckedListener() {
                    @Override
                    public void onButtonChecked(
                        MaterialButtonToggleGroup group, int checkedId, boolean isChecked) {
                            if(checkedId == 2131230860 && clickFlag) { // user clicked "show common"
                                moviesList.clear();
                                recyclerView.removeAllViews();
                                populateCommonMatches();
                            }
                            else if(checkedId == 2131231207 && clickFlag) {// user clicked "their list"
                                moviesList.clear();
                                recyclerView.removeAllViews();
                                populateTheirList();
                            }
                            clickFlag = !clickFlag;
                        }
                });

        moviesList = new ArrayList<>();

        recyclerView = view.findViewById(R.id.recyclerViewFriend);
        moviesRecyclerAdapter = new MoviesRecyclerAdapter(moviesList);

//        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(moviesRecyclerAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        populateCommonMatches();


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

    void populateCommonMatches() {
        moviesList.add("The Incredible Hulk");
        moviesList.add("Thor: The Dark World");
        moviesList.add("Ant-Man and the Wasp");
        moviesList.add("Spider-Man: Far From Home");
    }

    void populateTheirList() {
        moviesList.add("The Incredible Hulk");
        moviesList.add("Limitless");
        moviesList.add("Thor: The Dark World");
        moviesList.add("Hitchhiker's Guide to the Galaxy");
        moviesList.add("The Revelations of Narnia");
        moviesList.add("Sherlock Holmes");
        moviesList.add("Ant-Man and the Wasp");
        moviesList.add("Batman Begins");
        moviesList.add("Justice League");
        moviesList.add("Spider-Man: Far From Home");
    }
}
