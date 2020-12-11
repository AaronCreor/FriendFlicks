package com.nebulabs.friendflix;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
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
 * Fragment to display friend list of a user
 */
public class FriendsFragment extends Fragment {

    RequestQueue MyRequestQueue;
    FirebaseUser user;
    String userID;

    RecyclerView recyclerView;
    FriendsRecyclerAdapter friendsRecyclerAdapter;

    ArrayList<User> allFriends;
    ArrayList<String> userFriends;
    ArrayList<String[]> friendsList;

    // Access a Cloud Firestore instance from your Activity
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public FriendsFragment(){}

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
        return inflater.inflate(R.layout.fragment_friends,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        friendsList = new ArrayList<String[]>();

        populateFriendsList();

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

        FloatingActionButton addGroup_fab = view.findViewById(R.id.friendslist_addbutton);
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
                .setSubtitle("Enter your friend's email address\n\nTell your friend to add your email address in their FriendFlix app\n\nWhen you have both added each other, you'll show up in each other's Friends List!")
                .setFirstTextFieldHint("friend's email")
                .setFirstButtonText("SEND REQUEST")
                .setSecondButtonText("CANCEL")
                .withFirstButtonListner(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (flatDialog.getFirstTextField().isEmpty()){
                            Toasty.error(getContext(),"Enter your friend's email", Toasty.LENGTH_SHORT).show();
                        }
                        else {
                            String userEmail = MainActivity.userEmail;
                            UsersData usersData = MainActivity.usersData;
                            User user = usersData.getUserByEmail(userEmail);

                            if(user.friendsList.contains(flatDialog.getFirstTextField())) { // check heroku
                                Toasty.error(getContext(),"You have already added that email!", Toasty.LENGTH_SHORT).show();
                                flatDialog.dismiss();
                            }
                            else {
                                user.friendsList.add(flatDialog.getFirstTextField()); // send post friend request
                                Toasty.success(getContext(),"Friend request sent!", Toasty.LENGTH_SHORT).show();
                                flatDialog.dismiss();
                                friendsList.clear();
                                recyclerView.removeAllViews();
                                populateFriendsList();
                            }
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

    void populateFriendsList() {
//        String userEmail = MainActivity.userEmail;
//        UsersData usersData = MainActivity.usersData;
//        User user = usersData.getUserByEmail(userEmail);
//        Iterator<String> friendEmailIterator = user.friendsList.iterator();
//        while(friendEmailIterator.hasNext()) {
//            User possibleFriend = usersData.getUserByEmail(friendEmailIterator.next());
//            if(possibleFriend != null && possibleFriend.friendsList.contains(userEmail)) { // if a person on your friends list also has you on their friends list
//                String[] input = new String[3];
//                input[0] = possibleFriend.userName;
//                input[1] = possibleFriend.userEmail;
//                input[2] = possibleFriend.picture;
//                friendsList.add(0, input); // show them in the friendsList recycler view
//            }
//        }
        getAllFriends();
    }

    public void getUserFriends() {
        userFriends = new ArrayList<String>();
        String url = "https://friendflix.herokuapp.com/users/" + userID + "/" + "getfriends";

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
                                JSONObject friend = response.getJSONObject(i);

                                // Get the current movie (json object) data
                                userFriends.add(0, friend.getString("frienduid"));

                                Iterator<User> allFriendsIterator = allFriends.iterator();
                                while(allFriendsIterator.hasNext()) {
                                    User currentAllFriend = allFriendsIterator.next();
                                    if(friend.getString("mid").contains(currentAllFriend.userid)) {
                                        String input[] = new String[3];
                                        input[0] = currentAllFriend.username;
                                        input[1] = currentAllFriend.useremail;
                                        input[2] = currentAllFriend.photourl;
                                        friendsList.add(0, input);
                                    }
                                }
//
//                                // Display the formatted json data in text view
//                                mTextView.append(firstName +" " + lastName +"\nAge : " + age);
//                                mTextView.append("\n\n");
                            }
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

    public void getAllFriends() {
        allFriends = new ArrayList<User>();
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

                                // Get the current friend (json object) data
                                String mid = movie.getString("userid");
                                String moviename = movie.getString("useremail");
                                String year = movie.getString("username");
                                String posterurl = movie.getString("photourl");
                                String[] input = new String[4];
                                allFriends.add(new User(mid, moviename, year, posterurl));
//
//                                // Display the formatted json data in text view
//                                mTextView.append(firstName +" " + lastName +"\nAge : " + age);
//                                mTextView.append("\n\n");
                            }
                            getUserFriends();
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
