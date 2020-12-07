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

    public void add(String movieID, String movieName, int movieYear, String moviePoster) {
        Movie input = new Movie(movieID, movieName, movieYear, moviePoster);
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

/*
class User {
    public String userEmail;
    public String userName;
    public ArrayList<Movie> movieList;
    public ArrayList<String> friendsList;
    String picture;

    public User(String userEmail, String userName, ArrayList<Movie> movieList, ArrayList<String> friendsList, String picture) {
        this.userEmail = userEmail;
        this.userName = userName;
        this.movieList = movieList;
        this.friendsList = friendsList;
        this.picture = picture;
    }

    public void addMovie(String movieID, String movieName, int movieYear, String moviePoster) {
        Movie input = new Movie(movieID, movieName, movieYear, moviePoster);
        this.movieList.add(input);
    }

    public void addFriend(String email) {
        this.friendsList.add(email);
    }
}
*/
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
        aaronMovies.add(new Movie("tt0114369", "Se7en", 1995, "https://m.media-amazon.com/images/M/MV5BOTUwODM5MTctZjczMi00OTk4LTg3NWUtNmVhMTAzNTNjYjcyXkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_SX300.jpg"));
        aaronMovies.add(new Movie("tt0381681", "Before Sunset", 2004, "https://m.media-amazon.com/images/M/MV5BMTQ1MjAwNTM5Ml5BMl5BanBnXkFtZTYwNDM0MTc3._V1_SX300.jpg"));
        aaronMovies.add(new Movie("tt0118799", "Life is Beautiful", 1997, "https://m.media-amazon.com/images/M/MV5BYmJmM2Q4NmMtYThmNC00ZjRlLWEyZmItZTIwOTBlZDQ3NTQ1XkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_SX300.jpg"));
        aaronMovies.add(new Movie("tt0043014", "Sunset Blvd.", 1950, "https://m.media-amazon.com/images/M/MV5BMTU0NTkyNzYwMF5BMl5BanBnXkFtZTgwMDU0NDk5MTI@._V1_SX300.jpg"));
        aaronMovies.add(new Movie("tt0087884", "Paris, Texas", 2018, "https://m.media-amazon.com/images/M/MV5BM2RjMmU3ZWItYzBlMy00ZmJkLWE5YzgtNTVkODdhOWM3NGZhXkEyXkFqcGdeQXVyNDA5Mjg5MjA@._V1_SX300.jpg"));
        aaronMovies.add(new Movie("tt0198781", "Monsters, Inc.", 2001, "https://m.media-amazon.com/images/M/MV5BMTY1NTI0ODUyOF5BMl5BanBnXkFtZTgwNTEyNjQ0MDE@._V1_SX300.jpg"));
        aaronMovies.add(new Movie("tt0110357", "The Lion King", 1994, "https://m.media-amazon.com/images/M/MV5BYTYxNGMyZTYtMjE3MS00MzNjLWFjNmYtMDk3N2FmM2JiM2M1XkEyXkFqcGdeQXVyNjY5NDU4NzI@._V1_SX300.jpg"));
        aaronMovies.add(new Movie("tt0056592", "To Kill a Mockingbird", 1962, "https://m.media-amazon.com/images/M/MV5BNmVmYzcwNzMtMWM1NS00MWIyLThlMDEtYzUwZDgzODE1NmE2XkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_SX300.jpg"));
        aaronMovies.add(new Movie("tt2119532", "Hacksaw Ridge", 2016, "https://m.media-amazon.com/images/M/MV5BMjQ1NjM3MTUxNV5BMl5BanBnXkFtZTgwMDc5MTY5OTE@._V1_SX300.jpg"));
        FriendsList aaronFriends = new FriendsList();
        aaronFriends.add("jonnyboy@gmail.com");
        aaronFriends.add("joshua@gmail.com");
        aaronFriends.add("chinmay@gmail.com");
        User aaron = new User("aaron@gmail.com", "Aaron", aaronMovies.get(), aaronFriends.get(), "https://cdn.discordapp.com/avatars/255897947085078529/e868b3c954d52d49b418cc1b74f3b261.png");
        usersList.add(aaron);

        MovieList chinmayMovies = new MovieList();
        chinmayMovies.add(new Movie("tt1911644", "The Call", 2013, "https://m.media-amazon.com/images/M/MV5BMjExNDkzNjAwOV5BMl5BanBnXkFtZTcwMDMzMzQwOQ@@._V1_SX300.jpg"));
        chinmayMovies.add(new Movie("tt0116571", "House Arrest", 1996, "https://m.media-amazon.com/images/M/MV5BYWJiMGU3ODItMWRiZi00MjFjLWE1ZWItM2U1YTY5NmViMjk2XkEyXkFqcGdeQXVyNTM5NzI0NDY@._V1_SX300.jpg"));
        chinmayMovies.add(new Movie("tt0095765", "Cinema Paradiso", 1988, "https://m.media-amazon.com/images/M/MV5BM2FhYjEyYmYtMDI1Yy00YTdlLWI2NWQtYmEzNzAxOGY1NjY2XkEyXkFqcGdeQXVyNTA3NTIyNDg@._V1_SX300.jpg"));
        chinmayMovies.add(new Movie("tt0114369", "Se7en", 1995, "https://m.media-amazon.com/images/M/MV5BOTUwODM5MTctZjczMi00OTk4LTg3NWUtNmVhMTAzNTNjYjcyXkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_SX300.jpg"));
        chinmayMovies.add(new Movie("tt0381681", "Before Sunset", 2004, "https://m.media-amazon.com/images/M/MV5BMTQ1MjAwNTM5Ml5BMl5BanBnXkFtZTYwNDM0MTc3._V1_SX300.jpg"));
        chinmayMovies.add(new Movie("tt0032976", "Rebecca", 1940, "https://m.media-amazon.com/images/M/MV5BYTcxYWExOTMtMWFmYy00ZjgzLWI0YjktNWEzYzJkZTg0NDdmL2ltYWdlXkEyXkFqcGdeQXVyNjc1NTYyMjg@._V1_SX300.jpg"));
        chinmayMovies.add(new Movie("tt0118799", "Life is Beautiful", 1997, "https://m.media-amazon.com/images/M/MV5BYmJmM2Q4NmMtYThmNC00ZjRlLWEyZmItZTIwOTBlZDQ3NTQ1XkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_SX300.jpg"));
        chinmayMovies.add(new Movie("tt2582802", "Whiplash", 2014, "https://m.media-amazon.com/images/M/MV5BOTA5NDZlZGUtMjAxOS00YTRkLTkwYmMtYWQ0NWEwZDZiNjEzXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_SX300.jpg"));
        FriendsList chinmayFriends = new FriendsList();
        chinmayFriends.add("jonnyboy@gmail.com");
        chinmayFriends.add("joshua@gmail.com");
        chinmayFriends.add("karan@gmail.com");
        User chinmay = new User("chinmay@gmail.com", "Chinmay", chinmayMovies.get(), chinmayFriends.get(), "https://cdn.discordapp.com/avatars/752448216997298207/be69b35931f36fcb1b1253fe804cd864.png");
        usersList.add(chinmay);

        MovieList joshMovies = new MovieList();
        joshMovies.add(new Movie("tt0034583", "Casablanca", 1942, "https://m.media-amazon.com/images/M/MV5BY2IzZGY2YmEtYzljNS00NTM5LTgwMzUtMzM1NjQ4NGI0OTk0XkEyXkFqcGdeQXVyNDYyMDk5MTU@._V1_SX300.jpg"));
        joshMovies.add(new Movie("tt0053125", "North by Northwest", 1959, "https://m.media-amazon.com/images/M/MV5BZDA3NDExMTUtMDlhOC00MmQ5LWExZGUtYmI1NGVlZWI4OWNiXkEyXkFqcGdeQXVyNjc1NTYyMjg@._V1_SX300.jpg"));
        joshMovies.add(new Movie("tt0114814", "The Usual Suspects", 1995, "https://m.media-amazon.com/images/M/MV5BYTViNjMyNmUtNDFkNC00ZDRlLThmMDUtZDU2YWE4NGI2ZjVmXkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_SX300.jpg"));
        joshMovies.add(new Movie("tt1255953", "Incendies", 2010, "https://m.media-amazon.com/images/M/MV5BMWE3MGYzZjktY2Q5Mi00Y2NiLWIyYWUtMmIyNzA3YmZlMGFhXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_SX300.jpg"));
        joshMovies.add(new Movie("tt2119532", "Hacksaw Ridge", 2016, "https://m.media-amazon.com/images/M/MV5BMjQ1NjM3MTUxNV5BMl5BanBnXkFtZTgwMDc5MTY5OTE@._V1_SX300.jpg"));
        joshMovies.add(new Movie("tt0108052", "Schindler's List", 1993, "https://m.media-amazon.com/images/M/MV5BNDE4OTMxMTctNmRhYy00NWE2LTg3YzItYTk3M2UwOTU5Njg4XkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_SX300.jpg"));
        joshMovies.add(new Movie("tt0056592", "To Kill a Mockingbird", 1962, "https://m.media-amazon.com/images/M/MV5BNmVmYzcwNzMtMWM1NS00MWIyLThlMDEtYzUwZDgzODE1NmE2XkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_SX300.jpg"));
        FriendsList joshFriends = new FriendsList();
        joshFriends.add("jonnyboy@gmail.com");
        joshFriends.add("aaron@gmail.com");
        joshFriends.add("karan@gmail.com");
        User josh = new User("joshua@gmail.com", "Joshua", joshMovies.get(), joshFriends.get(), "https://cdn.discordapp.com/avatars/673388474082263070/a05fe7b6a679d80088545adf2f2026fa.png");
        usersList.add(josh);

        MovieList karanMovies = new MovieList();
        karanMovies.add(new Movie("tt1911644", "The Call", 2013, "https://m.media-amazon.com/images/M/MV5BMjExNDkzNjAwOV5BMl5BanBnXkFtZTcwMDMzMzQwOQ@@._V1_SX300.jpg"));
        karanMovies.add(new Movie("tt0095765", "Cinema Paradiso", 1988, "https://m.media-amazon.com/images/M/MV5BM2FhYjEyYmYtMDI1Yy00YTdlLWI2NWQtYmEzNzAxOGY1NjY2XkEyXkFqcGdeQXVyNTA3NTIyNDg@._V1_SX300.jpg"));
        karanMovies.add(new Movie("tt0381681", "Before Sunset", 2004, "https://m.media-amazon.com/images/M/MV5BMTQ1MjAwNTM5Ml5BMl5BanBnXkFtZTYwNDM0MTc3._V1_SX300.jpg"));
        karanMovies.add(new Movie("tt0118799", "Life is Beautiful", 1997, "https://m.media-amazon.com/images/M/MV5BYmJmM2Q4NmMtYThmNC00ZjRlLWEyZmItZTIwOTBlZDQ3NTQ1XkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_SX300.jpg"));
        karanMovies.add(new Movie("tt0088247", "The Terminator", 1984, "https://m.media-amazon.com/images/M/MV5BYTViNzMxZjEtZGEwNy00MDNiLWIzNGQtZDY2MjQ1OWViZjFmXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_SX300.jpg"));
        karanMovies.add(new Movie("tt0208092", "Snatch", 2017, "https://m.media-amazon.com/images/M/MV5BMzEyMTc4NzEyMl5BMl5BanBnXkFtZTgwMzA2OTYzNjM@._V1_SX300.jpg"));
        karanMovies.add(new Movie("tt0017136", "Metropolis", 1927, "https://m.media-amazon.com/images/M/MV5BMTg5YWIyMWUtZDY5My00Zjc1LTljOTctYmI0MWRmY2M2NmRkXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_SX300.jpg"));
        karanMovies.add(new Movie("tt0087884", "Paris, Texas", 2018, "https://m.media-amazon.com/images/M/MV5BM2RjMmU3ZWItYzBlMy00ZmJkLWE5YzgtNTVkODdhOWM3NGZhXkEyXkFqcGdeQXVyNDA5Mjg5MjA@._V1_SX300.jpg"));
        FriendsList karanFriends = new FriendsList();
        karanFriends.add("jonnyboy@gmail.com");
        karanFriends.add("aaron@gmail.com");
        karanFriends.add("chinmay@gmail.com");
        User karan = new User("karan@gmail.com", "Karan", karanMovies.get(), karanFriends.get(), "https://cdn.discordapp.com/avatars/756554515288948748/1fe74a8c2172db135fc8b97e7fac78d1.png");
        usersList.add(karan);

        MovieList jonnyMovies = new MovieList();
        jonnyMovies.add(new Movie("tt1911644", "The Call", 2013, "https://m.media-amazon.com/images/M/MV5BMjExNDkzNjAwOV5BMl5BanBnXkFtZTcwMDMzMzQwOQ@@._V1_SX300.jpg"));
        jonnyMovies.add(new Movie("tt0116571", "House Arrest", 1996, "https://m.media-amazon.com/images/M/MV5BYWJiMGU3ODItMWRiZi00MjFjLWE1ZWItM2U1YTY5NmViMjk2XkEyXkFqcGdeQXVyNTM5NzI0NDY@._V1_SX300.jpg"));
        jonnyMovies.add(new Movie("tt0095765", "Cinema Paradiso", 1988, "https://m.media-amazon.com/images/M/MV5BM2FhYjEyYmYtMDI1Yy00YTdlLWI2NWQtYmEzNzAxOGY1NjY2XkEyXkFqcGdeQXVyNTA3NTIyNDg@._V1_SX300.jpg"));
        jonnyMovies.add(new Movie("tt2582802", "Whiplash", 2014, "https://m.media-amazon.com/images/M/MV5BOTA5NDZlZGUtMjAxOS00YTRkLTkwYmMtYWQ0NWEwZDZiNjEzXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_SX300.jpg"));
        jonnyMovies.add(new Movie("tt0088247", "The Terminator", 1984, "https://m.media-amazon.com/images/M/MV5BYTViNzMxZjEtZGEwNy00MDNiLWIzNGQtZDY2MjQ1OWViZjFmXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_SX300.jpg"));
        jonnyMovies.add(new Movie("tt0167404", "The Sixth Sense", 1999, "https://m.media-amazon.com/images/M/MV5BMWM4NTFhYjctNzUyNi00NGMwLTk3NTYtMDIyNTZmMzRlYmQyXkEyXkFqcGdeQXVyMTAwMzUyOTc@._V1_SX300.jpg"));
        jonnyMovies.add(new Movie("tt0110357", "The Lion King", 1994, "https://m.media-amazon.com/images/M/MV5BYTYxNGMyZTYtMjE3MS00MzNjLWFjNmYtMDk3N2FmM2JiM2M1XkEyXkFqcGdeQXVyNjY5NDU4NzI@._V1_SX300.jpg"));
        jonnyMovies.add(new Movie("tt0042192", "All About Eve", 1950, "https://m.media-amazon.com/images/M/MV5BMTY2MTAzODI5NV5BMl5BanBnXkFtZTgwMjM4NzQ0MjE@._V1_SX300.jpg"));
        jonnyMovies.add(new Movie("tt0114814", "The Usual Suspects", 1995, "https://m.media-amazon.com/images/M/MV5BYTViNjMyNmUtNDFkNC00ZDRlLThmMDUtZDU2YWE4NGI2ZjVmXkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_SX300.jpg"));
        jonnyMovies.add(new Movie("tt0053125", "North by Northwest", 1959, "https://m.media-amazon.com/images/M/MV5BZDA3NDExMTUtMDlhOC00MmQ5LWExZGUtYmI1NGVlZWI4OWNiXkEyXkFqcGdeQXVyNjc1NTYyMjg@._V1_SX300.jpg"));
        FriendsList jonnyFriends = new FriendsList();
        jonnyFriends.add("aaron@gmail.com");
        jonnyFriends.add("chinmay@gmail.com");
        jonnyFriends.add("joshua@gmail.com");
//        jonnyFriends.add("karan@gmail.com");
//        jonnyFriends.add("herobrine@gmail.com");
        User jonny = new User("jonnyboy@gmail.com", "Jonny", jonnyMovies.get(), jonnyFriends.get(), "https://cachedimages.podchaser.com/256x256/aHR0cHM6Ly9jcmVhdG9yLWltYWdlcy5wb2RjaGFzZXIuY29tL2FjZjdmZmNkMTIxMGViOTA3N2JjN2FkN2FhYTMyMjI5LnBuZw%3D%3D/aHR0cHM6Ly93d3cucG9kY2hhc2VyLmNvbS9pbWFnZXMvbWlzc2luZy1pbWFnZS5wbmc%3D");
        usersList.add(jonny);

        MovieList herobrineMovies = new MovieList();
        herobrineMovies.add(new Movie("tt0114369", "Se7en", 1995, "https://m.media-amazon.com/images/M/MV5BOTUwODM5MTctZjczMi00OTk4LTg3NWUtNmVhMTAzNTNjYjcyXkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_SX300.jpg"));
        herobrineMovies.add(new Movie("tt0381681", "Before Sunset", 2004, "https://m.media-amazon.com/images/M/MV5BMTQ1MjAwNTM5Ml5BMl5BanBnXkFtZTYwNDM0MTc3._V1_SX300.jpg"));
        herobrineMovies.add(new Movie("tt0118799", "Life is Beautiful", 1997, "https://m.media-amazon.com/images/M/MV5BYmJmM2Q4NmMtYThmNC00ZjRlLWEyZmItZTIwOTBlZDQ3NTQ1XkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_SX300.jpg"));
        herobrineMovies.add(new Movie("tt2582802", "Whiplash", 2014, "https://m.media-amazon.com/images/M/MV5BOTA5NDZlZGUtMjAxOS00YTRkLTkwYmMtYWQ0NWEwZDZiNjEzXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_SX300.jpg"));
        herobrineMovies.add(new Movie("tt0088247", "The Terminator", 1984, "https://m.media-amazon.com/images/M/MV5BYTViNzMxZjEtZGEwNy00MDNiLWIzNGQtZDY2MjQ1OWViZjFmXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_SX300.jpg"));
        herobrineMovies.add(new Movie("tt0167404", "The Sixth Sense", 1999, "https://m.media-amazon.com/images/M/MV5BMWM4NTFhYjctNzUyNi00NGMwLTk3NTYtMDIyNTZmMzRlYmQyXkEyXkFqcGdeQXVyMTAwMzUyOTc@._V1_SX300.jpg"));
        herobrineMovies.add(new Movie("tt0208092", "Snatch", 2017, "https://m.media-amazon.com/images/M/MV5BMzEyMTc4NzEyMl5BMl5BanBnXkFtZTgwMzA2OTYzNjM@._V1_SX300.jpg"));
        herobrineMovies.add(new Movie("tt0043014", "Sunset Blvd.", 1950, "https://m.media-amazon.com/images/M/MV5BMTU0NTkyNzYwMF5BMl5BanBnXkFtZTgwMDU0NDk5MTI@._V1_SX300.jpg"));
        herobrineMovies.add(new Movie("tt8579674", "1917", 2019, "https://m.media-amazon.com/images/M/MV5BOTdmNTFjNDEtNzg0My00ZjkxLTg1ZDAtZTdkMDc2ZmFiNWQ1XkEyXkFqcGdeQXVyNTAzNzgwNTg@._V1_SX300.jpg"));
        herobrineMovies.add(new Movie("tt1255953", "Incendies", 2010, "https://m.media-amazon.com/images/M/MV5BMWE3MGYzZjktY2Q5Mi00Y2NiLWIyYWUtMmIyNzA3YmZlMGFhXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_SX300.jpg"));
        FriendsList herobrineFriends = new FriendsList();
        herobrineFriends.add("aaron@gmail.com");
        herobrineFriends.add("chinmay@gmail.com");
        herobrineFriends.add("joshua@gmail.com");
        herobrineFriends.add("karan@gmail.com");
        herobrineFriends.add("jonnyboy@gmail.com");
        User herobrine = new User("herobrine@gmail.com", "Herobrine", herobrineMovies.get(), herobrineFriends.get(), "https://i.pinimg.com/favicons/9d470e197d6d4d445051d7a7e216b57d4285282ff62c10bb8a24550e.jpg");
        usersList.add(herobrine);
    }
}
