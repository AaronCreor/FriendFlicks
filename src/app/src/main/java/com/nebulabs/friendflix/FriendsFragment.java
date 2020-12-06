package com.nebulabs.friendflix;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.example.flatdialoglibrary.dialog.FlatDialog;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import es.dmoral.toasty.Toasty;

/**
 * Fragment to display friend list of a user
 */
public class FriendsFragment extends Fragment {

    RecyclerView recyclerView;
    FriendsRecyclerAdapter friendsRecyclerAdapter;

    List<String[]> friendsList;

    // Access a Cloud Firestore instance from your Activity
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public FriendsFragment(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_friends,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        friendsList = new ArrayList<String[]>();

        String userEmail = MainActivity.userEmail;
        UsersData usersData = MainActivity.usersData;
        User user = usersData.getUserByEmail(userEmail);
        Iterator<String> friendEmailIterator = user.friendsList.iterator();
        while(friendEmailIterator.hasNext()) {
            User possibleFriend = usersData.getUserByEmail(friendEmailIterator.next());
            if(possibleFriend != null && possibleFriend.friendsList.contains(userEmail)) { // if a person on your friends list also has you on their friends list
                String[] input = new String[2];
                input[0] = possibleFriend.userName;
                input[1] = possibleFriend.userEmail;
                friendsList.add(input); // show them in the friendsList recycler view
            }
        }

        recyclerView = view.findViewById(R.id.recyclerViewFriendsList);
        friendsRecyclerAdapter = new FriendsRecyclerAdapter(friendsList);

        recyclerView.setAdapter(friendsRecyclerAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        FloatingSearchView mSearchView = view.findViewById(R.id.friendslist_searchBar);
        mSearchView.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {
            @Override
            public void onSearchTextChanged(String oldQuery, final String newQuery) {
                friendsRecyclerAdapter.getFilter().filter(newQuery);
            }
        });

        FloatingActionButton addGroup_fab = view.findViewById(R.id.addgroupfab);
        addGroup_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toasty.info(getContext(),"ADD GROUP",Toasty.LENGTH_SHORT).show();
                addNewFriend();
            }
        });
    }

    public void addNewFriend(){
        final FlatDialog flatDialog = new FlatDialog(getContext());
        flatDialog.setTitle("ADD FRIEND")
                .setSubtitle("Ask your friend to share their user ID with you")
                .setFirstTextFieldHint("friend's user ID")
                .setFirstButtonText("SEND REQUEST")
                .setSecondButtonText("CANCEL")
                .withFirstButtonListner(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (flatDialog.getFirstTextField().isEmpty()){
                            Toasty.error(getContext(),"Enter your friend's user ID", Toasty.LENGTH_SHORT).show();
                        }
                        else {
                            ProgressDialog dialog = new ProgressDialog(getContext());
                            dialog.setMessage("Sending request, please wait...");
                            dialog.show();

                            dialog.dismiss();

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
