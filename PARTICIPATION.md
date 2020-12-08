# COMP 370 - Assignment 1 - Participation Log

## Aaron Creor

Created initial gitlab
Built the XML for the explore and movie info pages
Produced early version of the navigation bar
Built first version of the override for Androids “pressback” functionality
Wrote SQL queries to build initial table
Worked on heroku setup
Worked on fixing and debugging some non functional queries (/users)
Organized meeting times for the group

## Chinmay Sharma
 - Created initial android project files for the app
 - Setup firebase support (For database and authentication)
 - Created splashscreen ui and backend
 - Created login screen ui and backend
 - Created profile fragment backend
 - Created fragment layout for main activity screen
 - Added dialog box in movies and friends fragment



## Karanveer Singh Bhullar
- Created mockups
- Explore screen:
    - Improved on the layout / user interface
    - Integrated it with the new navigation bar
    - Wrote logic for:
        - Loading movies (with help of Omdb class)
        - “Skip” (loading a new random movie)
        - “Like” (adding the currently shown movie to the user’s list)
- My List screen:
    - Created layout / user interface
    - Wrote logic for:
        - Populating the list of movies
        - Search filter
        - Transition to Movie Info screen upon selecting a movie from the list
- Movie Info screen:
    - Improved on the layout / user interface
    - Wrote logic for:
        - Loading movie info (with help of Omdb class)
        - “Like” (adding the currently shown movie to the user’s list)
        - Remove” (removing the currently shown movie from the user’s list)
- My Friends screen:
    - Created layout / user interface
    - Wrote logic for:
        - Populating the list of friends
        - Search filter
        - “Add friend” (adding someone to the list of friends)
        - Transition to Friend Info screen upon selecting a friend from the list
- Friend Info screen:
    - Created layout / user interface
    - Wrote logic for:
        - Loading friend info
        - Search filter
        - “Unfriend” (removing the currently shown friend from the list of friends)
        - “Common Matches” /  “Their List” toggle
        - Populating “Their List” with the list of the friend’s liked movies
        - Populating “Common Matches” by filtering out only those movies that both you and your friend have in their list of liked movies
        - Transition to Movie Info screen upon selecting a movie from “Common Matches” or “Their List”
- Meta:
    - Set up logic for “Recycler View” components (for building/rendering a list onto the layout)
    - Created some classes and set them up with filler data to create some Movies and Users (along with their friends lists and movie lists) upon being instantiated, to enable testing while the back-end was still in development.
    - Refactored the action result notification messages (“Added to My List” / “Removed from My List” / etc.)



## Joshua Fantillo
 - Setup and implemented the Omdb class and functions
 - Created List CSV file with most popular movies
 - Made and designed YouTube video
 - Cleaned up MoviesData class
 - Worked on connecting Users data from server to app
 - Cleaned up varius other code and added comments