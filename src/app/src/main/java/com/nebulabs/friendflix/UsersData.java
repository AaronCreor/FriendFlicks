// THIS IS FAKE DATA
// TO BE USED FOR TESTING THE APP

package com.nebulabs.friendflix;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class MovieList {
    ArrayList<Integer> movieList;

    public MovieList() {
        movieList = new ArrayList<Integer>();
    }

    public void add(int movieID) {
        movieList.add(movieID);
    }

    public ArrayList<Integer> get() {
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
    public ArrayList<Integer> movieList;
    public ArrayList<String> friendsList;

    public User(String userEmail, String userName, ArrayList<Integer> movieList, ArrayList<String> friendsList) {
        this.userEmail = userEmail;
        this.userName = userName;
        this.movieList = movieList;
        this.friendsList = friendsList;
    }
}

public class UsersData {
    public User getUserByEmail(String email) {
        User empty = null;
        Iterator<User> userIterator = usersList.iterator();
        while(userIterator.hasNext()) {
            User current = userIterator.next();
            if(current.userEmail == email)
                return current;
        }
        return empty;
    }

    public List<User> usersList = new ArrayList<User>();;

    public UsersData() {
        MovieList aaronMovies = new MovieList();
        aaronMovies.add(4);
        aaronMovies.add(5);
        aaronMovies.add(7);
        aaronMovies.add(12);
        aaronMovies.add(15);
        aaronMovies.add(17);
        aaronMovies.add(19);
        aaronMovies.add(21);
        aaronMovies.add(23);
        FriendsList aaronFriends = new FriendsList();
        aaronFriends.add("jonnyboy@gmail.com");
        aaronFriends.add("joshua@gmail.com");
        aaronFriends.add("chinmay@gmail.com");
        User aaron = new User("aaron@gmail.com", "Aaron", aaronMovies.get(), aaronFriends.get());
        usersList.add(aaron);

        MovieList chinmayMovies = new MovieList();
        chinmayMovies.add(1);
        chinmayMovies.add(2);
        chinmayMovies.add(3);
        chinmayMovies.add(4);
        chinmayMovies.add(5);
        chinmayMovies.add(6);
        chinmayMovies.add(7);
        chinmayMovies.add(8);
        FriendsList chinmayFriends = new FriendsList();
        chinmayFriends.add("jonnyboy@gmail.com");
        chinmayFriends.add("joshua@gmail.com");
        chinmayFriends.add("karan@gmail.com");
        User chinmay = new User("chinmay@gmail.com", "Chinmay", chinmayMovies.get(), chinmayFriends.get());
        usersList.add(chinmay);

        MovieList joshMovies = new MovieList();
        joshMovies.add(28);
        joshMovies.add(27);
        joshMovies.add(26);
        joshMovies.add(25);
        joshMovies.add(24);
        joshMovies.add(23);
        joshMovies.add(22);
        joshMovies.add(21);
        FriendsList joshFriends = new FriendsList();
        joshFriends.add("jonnyboy@gmail.com");
        joshFriends.add("aaron@gmail.com");
        joshFriends.add("karan@gmail.com");
        User josh = new User("joshua@gmail.com", "Joshua", joshMovies.get(), joshFriends.get());
        usersList.add(josh);

        MovieList karanMovies = new MovieList();
        karanMovies.add(1);
        karanMovies.add(3);
        karanMovies.add(5);
        karanMovies.add(7);
        karanMovies.add(9);
        karanMovies.add(11);
        karanMovies.add(13);
        karanMovies.add(15);
        FriendsList karanFriends = new FriendsList();
        karanFriends.add("jonnyboy@gmail.com");
        karanFriends.add("aaron@gmail.com");
        karanFriends.add("chinmay@gmail.com");
        User karan = new User("karan@gmail.com", "Karan", karanMovies.get(), karanFriends.get());
        usersList.add(karan);

        MovieList jonnyMovies = new MovieList();
        jonnyMovies.add(1);
        jonnyMovies.add(2);
        jonnyMovies.add(3);
        jonnyMovies.add(8);
        jonnyMovies.add(9);
        jonnyMovies.add(10);
        jonnyMovies.add(19);
        jonnyMovies.add(20);
        jonnyMovies.add(25);
        jonnyMovies.add(26);
        jonnyMovies.add(28);
        FriendsList jonnyFriends = new FriendsList();
        jonnyFriends.add("aaron@gmail.com");
        jonnyFriends.add("chinmay@gmail.com");
        jonnyFriends.add("joshua@gmail.com");
        jonnyFriends.add("karan@gmail.com");
        User jonny = new User("jonnyboy@gmail.com", "Jonny", jonnyMovies.get(), jonnyFriends.get());
        usersList.add(jonny);
    }
}
