// THIS IS FAKE DATA
// TO BE USED FOR TESTING THE APP

package com.nebulabs.friendflix;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class MovieList {
    ArrayList<String> movieList;

    public MovieList() {
        movieList = new ArrayList<String>();
    }

    public void add(String movieID) {
        movieList.add(movieID);
    }

    public ArrayList<String> get() {
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
    public ArrayList<String> movieList;
    public ArrayList<String> friendsList;

    public User(String userEmail, String userName, ArrayList<String> movieList, ArrayList<String> friendsList) {
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
        aaronMovies.add("tt0114369");
        aaronMovies.add("tt0381681");
        aaronMovies.add("tt0118799");
        aaronMovies.add("tt0043014");
        aaronMovies.add("tt0087884");
        aaronMovies.add("tt0198781");
        aaronMovies.add("tt0110357");
        aaronMovies.add("tt0056592");
        aaronMovies.add("tt2119532");
        FriendsList aaronFriends = new FriendsList();
        aaronFriends.add("jonnyboy@gmail.com");
        aaronFriends.add("joshua@gmail.com");
        aaronFriends.add("chinmay@gmail.com");
        User aaron = new User("aaron@gmail.com", "Aaron", aaronMovies.get(), aaronFriends.get());
        usersList.add(aaron);

        MovieList chinmayMovies = new MovieList();
        chinmayMovies.add("tt1911644");
        chinmayMovies.add("tt0116571");
        chinmayMovies.add("tt0095765");
        chinmayMovies.add("tt0114369");
        chinmayMovies.add("tt0381681");
        chinmayMovies.add("tt0032976");
        chinmayMovies.add("tt0118799");
        chinmayMovies.add("tt2582802");
        FriendsList chinmayFriends = new FriendsList();
        chinmayFriends.add("jonnyboy@gmail.com");
        chinmayFriends.add("joshua@gmail.com");
        chinmayFriends.add("karan@gmail.com");
        User chinmay = new User("chinmay@gmail.com", "Chinmay", chinmayMovies.get(), chinmayFriends.get());
        usersList.add(chinmay);

        MovieList joshMovies = new MovieList();
        joshMovies.add("tt0372784");
        joshMovies.add("tt0034583");
        joshMovies.add("tt0053125");
        joshMovies.add("tt0114814");
        joshMovies.add("tt1255953");
        joshMovies.add("tt2119532");
        joshMovies.add("tt0108052");
        joshMovies.add("tt0056592");
        FriendsList joshFriends = new FriendsList();
        joshFriends.add("jonnyboy@gmail.com");
        joshFriends.add("aaron@gmail.com");
        joshFriends.add("karan@gmail.com");
        User josh = new User("joshua@gmail.com", "Joshua", joshMovies.get(), joshFriends.get());
        usersList.add(josh);

        MovieList karanMovies = new MovieList();
        karanMovies.add("tt1911644");
        karanMovies.add("tt0095765");
        karanMovies.add("tt0381681");
        karanMovies.add("tt0118799");
        karanMovies.add("tt0088247");
        karanMovies.add("tt0208092");
        karanMovies.add("tt0017136");
        karanMovies.add("tt0087884");
        FriendsList karanFriends = new FriendsList();
        karanFriends.add("jonnyboy@gmail.com");
        karanFriends.add("aaron@gmail.com");
        karanFriends.add("chinmay@gmail.com");
        User karan = new User("karan@gmail.com", "Karan", karanMovies.get(), karanFriends.get());
        usersList.add(karan);

        MovieList jonnyMovies = new MovieList();
        jonnyMovies.add("tt1911644");
        jonnyMovies.add("tt0116571");
        jonnyMovies.add("tt0095765");
        jonnyMovies.add("tt2582802");
        jonnyMovies.add("tt0088247");
        jonnyMovies.add("tt0167404");
        jonnyMovies.add("tt0110357");
        jonnyMovies.add("tt0042192");
        jonnyMovies.add("tt0114814");
        jonnyMovies.add("tt0053125");
        jonnyMovies.add("tt0372784");
        FriendsList jonnyFriends = new FriendsList();
        jonnyFriends.add("aaron@gmail.com");
        jonnyFriends.add("chinmay@gmail.com");
        jonnyFriends.add("joshua@gmail.com");
        jonnyFriends.add("karan@gmail.com");
        jonnyFriends.add("herobrine@gmail.com");
        User jonny = new User("jonnyboy@gmail.com", "Jonny", jonnyMovies.get(), jonnyFriends.get());
        usersList.add(jonny);

        MovieList herobrineMovies = new MovieList();
        herobrineMovies.add("tt0114369");
        herobrineMovies.add("tt0381681");
        herobrineMovies.add("tt0118799");
        herobrineMovies.add("tt2582802");
        herobrineMovies.add("tt0088247");
        herobrineMovies.add("tt0167404");
        herobrineMovies.add("tt0208092");
        herobrineMovies.add("tt0043014");
        herobrineMovies.add("tt8579674");
        herobrineMovies.add("tt1255953");
        FriendsList herobrineFriends = new FriendsList();
        herobrineFriends.add("aaron@gmail.com");
        herobrineFriends.add("chinmay@gmail.com");
        herobrineFriends.add("joshua@gmail.com");
        herobrineFriends.add("karan@gmail.com");
        User herobrine = new User("herobrine@gmail.com", "Herobrine", herobrineMovies.get(), herobrineFriends.get());
        usersList.add(jonny);
    }
}
