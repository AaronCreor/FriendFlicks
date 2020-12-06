package com.nebulabs.friendflix;

import com.google.gson.annotations.SerializedName;

/**
 * User class
 */
public class User {

    @SerializedName("uid")
    String uid;

    @SerializedName("userEmail")
    String userEmail;

    /**
     * Create a new user
     * @param mUid user id
     * @param mUserEmail user email
     */
    public User(String mUid, String mUserEmail) {
        this.userEmail = mUserEmail;
        this.uid = mUid;
    }

    /**
     * Create a new user
     * @param mUid user id
     */
    public User(String mUid){
        this.uid = mUid;
    }

    /**
     * Add movie to user database
     * @param mx movie object
     */
    public void addMovie(Movie mx) {
        Movie input = mx;
        // TODO: ADD MOVIE TO DB
    }

    /**
     * Retrieve all movies from database
     */
    public void getMovies(){
        // TODO: GET USER MOVIES
    }

}