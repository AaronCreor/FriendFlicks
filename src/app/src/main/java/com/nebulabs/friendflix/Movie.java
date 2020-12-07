package com.nebulabs.friendflix;

import com.google.gson.annotations.SerializedName;

/**
 * Movie class
 */
public class Movie {

    @SerializedName("mid")
    String mid;

    @SerializedName("movieName")
    String movieName;

    int year;

    String poster;

    /**
     * Movie constructor
     * @param id movie id
     * @param name movie name
     * @param year release year
     * @param moviePoster poster url
     */
    public Movie(String id, String name, int year, String moviePoster) {
        this.mid = id;
        this.movieName = name;
        this.year = year;
        this.poster = moviePoster;
    }


    /**
     * Getter for movie id
     * @return movie id
     */
    public String getMid() {
        return mid;
    }

    /**
     * Getter for movie name
     * @return movie name
     */
    public String getMovieName() {
        return movieName;
    }

    /**
     * Getter for movie poster
     * @return
     */
    public String getPoster() {
        return poster;
    }

    /**
     * Getter for movie release year
     * @return
     */
    public int getYear() {
        return year;
    }

}
