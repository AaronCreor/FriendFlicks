// THIS IS FAKE DATA
// TO BE USED FOR TESTING THE APP

package com.nebulabs.friendflix;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


class Movie {
    int id;
    String name;
    int year;

    public Movie(int id, String name, int year) {
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

    public MoviesData() {
        movieList.add(new Movie(0, "Limitless", 2011));
        movieList.add(new Movie(1, "The Call", 2020));
        movieList.add(new Movie(2, "Limitless", 2011));
        movieList.add(new Movie(3, "Andhagharaam", 2020));
        movieList.add(new Movie(4, "Operation Alamelamma", 2017));
        movieList.add(new Movie(5, "Extraction", 2020));
        movieList.add(new Movie(6, "Middle Class Melodies", 2020));
        movieList.add(new Movie(7, "Angel Has Fallen", 2019));
        movieList.add(new Movie(8, "#Alive", 2020));
        movieList.add(new Movie(9, "Joker", 2019));
        movieList.add(new Movie(10, "Bombhaat", 2020));
        movieList.add(new Movie(11, "World Famous Lover", 2020));
        movieList.add(new Movie(12, "Ford v Ferrari", 2019));
        movieList.add(new Movie(13, "Dunkirk", 2017));
        movieList.add(new Movie(14, "Aquaman", 2018));
        movieList.add(new Movie(15, "Venom", 2018));
        movieList.add(new Movie(16, "Awe!", 2018));
        movieList.add(new Movie(17, "Frozen 2", 2019));
        movieList.add(new Movie(18, "The Judge", 2014));
        movieList.add(new Movie(19, "Sonic the Hedgehog", 2020));
        movieList.add(new Movie(20, "X-Men: Dark Phoenix", 2019));
        movieList.add(new Movie(21, "Justice League", 2017));
        movieList.add(new Movie(22, "Shazam!", 2019));
        movieList.add(new Movie(23, "Wonder Woman", 2017));
        movieList.add(new Movie(24, "Sadak 2", 2020));
        movieList.add(new Movie(25, "The Lion King", 2019));
        movieList.add(new Movie(26, "Suicide Squad", 2016));
        movieList.add(new Movie(27, "Power Rangers", 2017));
        movieList.add(new Movie(28, "Goosebumps", 2015));
    }


    public List getMovieList() {
        return movieList;
    }
}