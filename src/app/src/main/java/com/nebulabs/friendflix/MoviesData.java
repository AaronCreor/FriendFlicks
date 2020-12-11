// THIS IS FAKE DATA
// TO BE USED FOR TESTING THE APP

package com.nebulabs.friendflix;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


class Movie {
    String id;
    String name;
    int year;
    String poster;

    public Movie(String id, String name, int year, String poster) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.poster = poster;
    }

    @Override
    public boolean equals(Object obj) {

        if (!(obj instanceof Movie))
            return false;
        final Movie om = (Movie)obj;
        // compare om's fields to mine
        if(this.id.equals(om.id))
            if(this.name.equals(om.name))
                if(this.year == om.year)
                    return true;
        return false;
    }

}


//public class MoviesData {
//    List<Movie> movieList= new ArrayList<Movie>();
//
//    public Movie getMovieByID(String ID) {
//        Movie empty = null;
//        Iterator<Movie> movieIterator = movieList.iterator();
//        while(movieIterator.hasNext()) {
//            Movie current = movieIterator.next();
//            if(current.id.equals(ID))
//                return current;
//        }
//        return empty;
//    }
//
//    public MoviesData() {
//        movieList.add(new Movie("tt1219289", "Limitless", 2011));
//        movieList.add(new Movie("tt1911644", "The Call", 2013));
//        movieList.add(new Movie("tt0116571", "House Arrest", 1996));
//        movieList.add(new Movie("tt0095765", "Cinema Paradiso", 1988));
//        movieList.add(new Movie("tt0114369", "Se7en", 1995));
//        movieList.add(new Movie("tt0381681", "Before Sunset", 2004));
//        movieList.add(new Movie("tt0032976", "Rebecca", 1940));
//        movieList.add(new Movie("tt0118799", "Life is Beautiful", 1997));
//        movieList.add(new Movie("tt2582802", "Whiplash", 2014));
//        movieList.add(new Movie("tt0088247", "The Terminator", 1984));
//        movieList.add(new Movie("tt0167404", "The Sixth Sense", 1999));
//        movieList.add(new Movie("tt0208092", "Snatch", 2017));
//        movieList.add(new Movie("tt0043014", "Sunset Blvd.", 1950));
//        movieList.add(new Movie("tt0017136", "Metropolis", 1927));
//        movieList.add(new Movie("tt8579674", "1917", 2019));
//        movieList.add(new Movie("tt0087884", "Paris, Texas", 2018));
//        movieList.add(new Movie("tt0119217", "Good Will Hunting", 1997));
//        movieList.add(new Movie("tt0198781", "Monsters, Inc.", 2001));
//        movieList.add(new Movie("tt0099685", "Goodfellas", 1990));
//        movieList.add(new Movie("tt0110357", "The Lion King", 1994));
//        movieList.add(new Movie("tt0042192", "All About Eve", 1950));
//        movieList.add(new Movie("tt0056592", "To Kill a Mockingbird", 1962));
//        movieList.add(new Movie("tt0108052", "Schindler's List", 1993));
//        movieList.add(new Movie("tt2119532", "Hacksaw Ridge", 2016));
//        movieList.add(new Movie("tt1255953", "Incendies", 2010));
//        movieList.add(new Movie("tt0114814", "The Usual Suspects", 1995));
//        movieList.add(new Movie("tt0053125", "North by Northwest", 1959));
//        movieList.add(new Movie("tt0034583", "Casablanca", 1942));
//        movieList.add(new Movie("tt0372784", "Batman Begins", 2005));
//    }
//
//
//    public List getMovieList() {
//        return movieList;
//    }
//}