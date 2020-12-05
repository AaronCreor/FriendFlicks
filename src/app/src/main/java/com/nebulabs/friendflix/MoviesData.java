// THIS IS FAKE DATA
// TO BE USED FOR TESTING THE APP

package com.nebulabs.friendflix;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static java.lang.Integer.parseInt;


class Movie {
    int id;
    String name;
    int year;

    public Movie(int id, String name, int year) throws IOException {
        this.id = id;
        this.name = name;
        this.year = year;
    }
}


public class MoviesData {
    List<Movie> movieList= new ArrayList<Movie>();

    public Movie getMovieByID(int ID) {
        Movie empty = null;
        Iterator<Movie> movieIterator = movieList.iterator();
        while(movieIterator.hasNext()) {
            Movie current = movieIterator.next();
            if(current.id == ID)
                return current;
        }
        return empty;
    }

    final private int MOVIE_LIST_SIZE = 10;
    public MoviesData() throws IOException {
        //only has at most 10 movies in the array
        while(movieList.size() < MOVIE_LIST_SIZE)
        {
            //String id = getIdFromFirebase();
            //id value is just a place holder for now
            //the id value will be got from the json data on firebase
            movieList.add(new Movie(0, "batman", 2015));
        }


    }

    public List getMovieList() {
        return movieList;
    }
}