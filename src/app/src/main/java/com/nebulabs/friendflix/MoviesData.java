// THIS IS FAKE DATA
// TO BE USED FOR TESTING THE APP

package com.nebulabs.friendflix;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


class Movie {
    String id;
    String name;
    String year;

    public Movie(String id, String name, String year) {
        this.id = id;
        this.name = Omdb.getTitle(id);
        this.year = Omdb.getYear(id);
    }
}


public class MoviesData {
    List<Movie> movieList= new ArrayList<Movie>();

    public Movie getMovieByID(String ID) {
        Movie empty = null;
        Iterator<Movie> movieIterator = movieList.iterator();
        while(movieIterator.hasNext()) {
            Movie current = movieIterator.next();
            if(current.id.equals(ID))
                return current;
        }
        return empty;
    }
    
    final private int MOVIE_LIST_SIZE = 10;
    public MoviesData() {
        //only has at most 10 movies in the array
        while(movieList.size() < MOVIE_LIST_SIZE)
        {
            String id = "";
            //String id = getIdFromFirebase();
            //id value is just a place holder for now
            //the id value will be got from the json data on firebase
            movieList.add(new Movie(id, Omdb.getTitle(id), Omdb.getYear(id)));
        }


    }

    public List getMovieList() {
        return movieList;
    }
}