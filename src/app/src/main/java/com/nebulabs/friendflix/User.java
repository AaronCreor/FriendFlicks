package com.nebulabs.friendflix;

import com.google.gson.annotations.SerializedName;

/**
 * User class
 */
public class User {

    @SerializedName("uid")
    String uid;

    String userEmail;

    String userName;

    String photoUrl;

    /**
     * Create a new user
     * @param mUid firebase userid
     * @param mUserEmail user email
     * @param mUserName user name
     * @param mPhotoUrl user photo url
     */
    public User(String mUid, String mUserEmail,String mUserName,String mPhotoUrl) {
        this.userEmail = mUserEmail;
        this.uid = mUid;
        this.userName = mUserName;
        this.photoUrl = mPhotoUrl;
    }


    /**
     * Add movie to the database of a user
     * @param movieID
     * @param movieName
     * @param movieYear
     * @param moviePoster
     */
    public void addMovie(String movieID, String movieName, int movieYear, String moviePoster) {
        Movie input = new Movie(movieID, movieName, movieYear, moviePoster);
        /*
        INSERT INTO example_table
            (id, name)
        SELECT 1, 'John'
        WHERE
            NOT EXISTS (
                SELECT id FROM example_table WHERE id = 1
            );
         */
    }

    /**
     * Retrieve all movies from database
     */
    public void getMovies(){
        // TODO: GET USER MOVIES
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserName() {
        return userName;
    }
}
