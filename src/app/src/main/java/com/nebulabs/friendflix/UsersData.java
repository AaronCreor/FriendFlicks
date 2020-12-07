// THIS IS FAKE DATA
// TO BE USED FOR TESTING THE APP

package com.nebulabs.friendflix;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class MovieList {
    ArrayList<Movie> movieList;

    public MovieList() {
        movieList = new ArrayList<Movie>();
    }

    public void add(Movie movie) {
        movieList.add(movie);
    }

    public void add(String movieID, String movieName, int movieYear) {
        Movie input = new Movie(movieID, movieName, movieYear);
        this.add(input);
    }

    public ArrayList<Movie> get() {
        return movieList;
    }
}

class FriendsList {
    ArrayList<String> friendsList;

    public FriendsList() {
        friendsList = new ArrayList<String>();
    }

    public void add(String email) {
        friendsList.add(email);
    }

    public ArrayList<String> get() {
        return friendsList;
    }
}

class User {
    public String userEmail;
    public String userName;
    public ArrayList<Movie> movieList;
    public ArrayList<String> friendsList;

    public User(String userEmail, String userName, ArrayList<Movie> movieList, ArrayList<String> friendsList) {
        this.userEmail = userEmail;
        this.userName = userName;
        this.movieList = movieList;
        this.friendsList = friendsList;
    }

    public void addMovie(String movieID, String movieName, int movieYear) {
        Movie input = new Movie(movieID, movieName, movieYear);
        this.movieList.add(input);
    }

    public void addFriend(String email) {
        this.friendsList.add(email);
    }
}

public class UsersData {
    public User getUserByEmail(String email) {
        User empty = null;
        Iterator<User> userIterator = usersList.iterator();
        while(userIterator.hasNext()) {
            User current = userIterator.next();
            if(current.userEmail.contains(email))
                return current;
        }
        return empty;
    }

    public List<User> usersList = new ArrayList<User>();;

    public UsersData() {
        MovieList aaronMovies = new MovieList();
        aaronMovies.add(new Movie("tt0114369", "Se7en", 1995));
        aaronMovies.add(new Movie("tt0381681", "Before Sunset", 2004));
        aaronMovies.add(new Movie("tt0118799", "Life is Beautiful", 1997));
        aaronMovies.add(new Movie("tt0043014", "Sunset Blvd.", 1950));
        aaronMovies.add(new Movie("tt0087884", "Paris, Texas", 2018));
        aaronMovies.add(new Movie("tt0198781", "Monsters, Inc.", 2001));
        aaronMovies.add(new Movie("tt0110357", "The Lion King", 1994));
        aaronMovies.add(new Movie("tt0056592", "To Kill a Mockingbird", 1962));
        aaronMovies.add(new Movie("tt2119532", "Hacksaw Ridge", 2016));
        FriendsList aaronFriends = new FriendsList();
        aaronFriends.add("jonnyboy@gmail.com");
        aaronFriends.add("joshua@gmail.com");
        aaronFriends.add("chinmay@gmail.com");
        User aaron = new User("aaron@gmail.com", "Aaron", aaronMovies.get(), aaronFriends.get());
        usersList.add(aaron);

        MovieList chinmayMovies = new MovieList();
        chinmayMovies.add(new Movie("tt1911644", "The Call", 2013));
        chinmayMovies.add(new Movie("tt0116571", "House Arrest", 1996));
        chinmayMovies.add(new Movie("tt0095765", "Cinema Paradiso", 1988));
        chinmayMovies.add(new Movie("tt0114369", "Se7en", 1995));
        chinmayMovies.add(new Movie("tt0381681", "Before Sunset", 2004));
        chinmayMovies.add(new Movie("tt0032976", "Rebecca", 1940));
        chinmayMovies.add(new Movie("tt0118799", "Life is Beautiful", 1997));
        chinmayMovies.add(new Movie("tt2582802", "Whiplash", 2014));
        FriendsList chinmayFriends = new FriendsList();
        chinmayFriends.add("jonnyboy@gmail.com");
        chinmayFriends.add("joshua@gmail.com");
        chinmayFriends.add("karan@gmail.com");
        User chinmay = new User("chinmay@gmail.com", "Chinmay", chinmayMovies.get(), chinmayFriends.get());
        usersList.add(chinmay);

        MovieList joshMovies = new MovieList();
        joshMovies.add(new Movie("tt0372784", "Batman Begins", 2005));
        joshMovies.add(new Movie("tt0034583", "Casablanca", 1942));
        joshMovies.add(new Movie("tt0053125", "North by Northwest", 1959));
        joshMovies.add(new Movie("tt0114814", "The Usual Suspects", 1995));
        joshMovies.add(new Movie("tt1255953", "Incendies", 2010));
        joshMovies.add(new Movie("tt2119532", "Hacksaw Ridge", 2016));
        joshMovies.add(new Movie("tt0108052", "Schindler's List", 1993));
        joshMovies.add(new Movie("tt0056592", "To Kill a Mockingbird", 1962));
        FriendsList joshFriends = new FriendsList();
        joshFriends.add("jonnyboy@gmail.com");
        joshFriends.add("aaron@gmail.com");
        joshFriends.add("karan@gmail.com");
        User josh = new User("joshua@gmail.com", "Joshua", joshMovies.get(), joshFriends.get());
        usersList.add(josh);

        MovieList karanMovies = new MovieList();
        karanMovies.add(new Movie("tt1911644", "The Call", 2013));
        karanMovies.add(new Movie("tt0095765", "Cinema Paradiso", 1988));
        karanMovies.add(new Movie("tt0381681", "Before Sunset", 2004));
        karanMovies.add(new Movie("tt0118799", "Life is Beautiful", 1997));
        karanMovies.add(new Movie("tt0088247", "The Terminator", 1984));
        karanMovies.add(new Movie("tt0208092", "Snatch", 2017));
        karanMovies.add(new Movie("tt0017136", "Metropolis", 1927));
        karanMovies.add(new Movie("tt0087884", "Paris, Texas", 2018));
        FriendsList karanFriends = new FriendsList();
        karanFriends.add("jonnyboy@gmail.com");
        karanFriends.add("aaron@gmail.com");
        karanFriends.add("chinmay@gmail.com");
        User karan = new User("karan@gmail.com", "Karan", karanMovies.get(), karanFriends.get());
        usersList.add(karan);

        MovieList jonnyMovies = new MovieList();
        jonnyMovies.add(new Movie("tt1911644", "The Call", 2013));
        jonnyMovies.add(new Movie("tt0116571", "House Arrest", 1996));
        jonnyMovies.add(new Movie("tt0095765", "Cinema Paradiso", 1988));
        jonnyMovies.add(new Movie("tt2582802", "Whiplash", 2014));
        jonnyMovies.add(new Movie("tt0088247", "The Terminator", 1984));
        jonnyMovies.add(new Movie("tt0167404", "The Sixth Sense", 1999));
        jonnyMovies.add(new Movie("tt0110357", "The Lion King", 1994));
        jonnyMovies.add(new Movie("tt0042192", "All About Eve", 1950));
        jonnyMovies.add(new Movie("tt0114814", "The Usual Suspects", 1995));
        jonnyMovies.add(new Movie("tt0053125", "North by Northwest", 1959));
        jonnyMovies.add(new Movie("tt0372784", "Batman Begins", 2005));
        FriendsList jonnyFriends = new FriendsList();
        jonnyFriends.add("aaron@gmail.com");
        jonnyFriends.add("chinmay@gmail.com");
        jonnyFriends.add("joshua@gmail.com");
//        jonnyFriends.add("karan@gmail.com");
//        jonnyFriends.add("herobrine@gmail.com");
        User jonny = new User("jonnyboy@gmail.com", "Jonny", jonnyMovies.get(), jonnyFriends.get());
        usersList.add(jonny);

        MovieList herobrineMovies = new MovieList();
        herobrineMovies.add(new Movie("tt0114369", "Se7en", 1995));
        herobrineMovies.add(new Movie("tt0381681", "Before Sunset", 2004));
        herobrineMovies.add(new Movie("tt0118799", "Life is Beautiful", 1997));
        herobrineMovies.add(new Movie("tt2582802", "Whiplash", 2014));
        herobrineMovies.add(new Movie("tt0088247", "The Terminator", 1984));
        herobrineMovies.add(new Movie("tt0167404", "The Sixth Sense", 1999));
        herobrineMovies.add(new Movie("tt0208092", "Snatch", 2017));
        herobrineMovies.add(new Movie("tt0043014", "Sunset Blvd.", 1950));
        herobrineMovies.add(new Movie("tt8579674", "1917", 2019));
        herobrineMovies.add(new Movie("tt1255953", "Incendies", 2010));
        FriendsList herobrineFriends = new FriendsList();
        herobrineFriends.add("aaron@gmail.com");
        herobrineFriends.add("chinmay@gmail.com");
        herobrineFriends.add("joshua@gmail.com");
        herobrineFriends.add("karan@gmail.com");
        herobrineFriends.add("jonnyboy@gmail.com");
        User herobrine = new User("herobrine@gmail.com", "Herobrine", herobrineMovies.get(), herobrineFriends.get());
        usersList.add(herobrine);
    }
}
