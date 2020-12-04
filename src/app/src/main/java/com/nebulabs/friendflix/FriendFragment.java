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
import java.util.List;

public class FriendFragment extends Fragment {

    boolean showCommon;

    RecyclerView recyclerView;
    MoviesRecyclerAdapter moviesRecyclerAdapter;

    List<String> moviesList;

    String name;

    public FriendFragment(String name){
        this.name = name;
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

        MaterialButtonToggleGroup materialButtonToggleGroup =
                (MaterialButtonToggleGroup) getView().findViewById(R.id.toggleButton);
        int buttonId = materialButtonToggleGroup.getCheckedButtonId();

        MaterialButton button = materialButtonToggleGroup.findViewById(buttonId);

        materialButtonToggleGroup.addOnButtonCheckedListener(
                new MaterialButtonToggleGroup.OnButtonCheckedListener() {
                    @Override
                    public void onButtonChecked(
                        MaterialButtonToggleGroup group, int checkedId, boolean isChecked) {
                        friendName.setText(Integer.toString(checkedId));
                            if(checkedId == 2131230860) { // user clicked "show common"
                                friendName.setText("Common");
                            }
                            else // user clicked "their list"
                                friendName.setText("Their");
                        }
                });

        moviesList = new ArrayList<>();

        recyclerView = view.findViewById(R.id.recyclerViewFriend);
        moviesRecyclerAdapter = new MoviesRecyclerAdapter(moviesList);

//        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(moviesRecyclerAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);


        moviesList.add("Iron Man");
        moviesList.add("The Incredible Hulk");
        moviesList.add("Iron Man 2");
        moviesList.add("Thor");
        moviesList.add("Captain America: The First Avenger");
        moviesList.add("The Avengers");
        moviesList.add("Iron Man 3");
        moviesList.add("Thor: The Dark World");
        moviesList.add("Captain America: The Winter Soldier");
        moviesList.add("Guardians of the Galaxy");
        moviesList.add("Avengers: Age of Ultron");
        moviesList.add("Ant-Man");
        moviesList.add("Captain America: Civil War");
        moviesList.add("Doctor Strange");
        moviesList.add("Guardians of the Galaxy Vol. 2");
        moviesList.add("Spider-Man: Homecoming");
        moviesList.add("Thor: Ragnarok");
        moviesList.add("Black Panther");
        moviesList.add("Avengers: Infinity War");
        moviesList.add("Ant-Man and the Wasp");
        moviesList.add("Captain Marvel");
        moviesList.add("Avengers: Endgame");
        moviesList.add("Spider-Man: Far From Home");
    }
}
