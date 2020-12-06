const express = require('express')
const bodyParser = require('body-parser')
const queries = require('./queries')
const app = express()

app.use(bodyParser.json())
app.use(
  bodyParser.urlencoded({
    extended: true,
  })
)

//Index Route
app.get('/', (request, response) => {
    response.json({ info: 'FriendFlix - Simple API' })
  });

// User routes
app.post('/users/create',queries.addUser);

app.delete('/users/del/:id',queries.deleteUser);


// Movie Routes
app.post('/movies/create',queries.addMovie);

app.delete('/movies/del/:id',queries.deleteMovie);

app.put('/addmovie/:userid/:movieid',queries.addMovieToUser);

app.put('/removemovie/:userid/:movieid',queries.removeMovieFromUser);

// Display Routes
app.get('/users',queries.getUsers);

app.get('/movies', queries.getMovies);

// Run Server
let port = process.env.PORT;
if (port == null || port == "") {
  port = 8000;
}

app.listen(port, () => {
    console.log(`App running on port ${port}.`)
  });