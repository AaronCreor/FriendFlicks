const db = require('./config');

// generate timestamp for current time
// const getTimestamp = () => {
//     return Math.floor(Date.now() / 1000).toString()
// };

// user queries
const addUser = (request,response) => {
    let {userid, userEmail} = request.body;
    // currently keeping time_updated same as time_created as there is no update method
    db.query('INSERT INTO users (userid, userEmail) VALUES ($1,$2)',[userid, userEmail], (error, results) => {
        if (error) {
            response.status(400).send('ERROR 400: Bad Request!')
            throw error
        }
        response.status(201).send('SUCCESS 201: User added')
    })

};

const deleteUser = (request,response) => {
    let userid = parseInt(request.params.id);
    console.log("Delete: "+userid);
    db.query('DELETE FROM users WHERE userid=$1',[userid], (error, results) => {
        if (error) {
            response.status(400).send('ERROR 400: Bad Request!')
            throw error
        }
        response.status(200).send('SUCCESS 200: User deleted')
    })
};

// movie queries
const addMovie = (request,response) => {
    let {mid,moviename,year} = request.body;
    db.query('INSERT INTO movies (mid,moviename,year) VALUES ($1,$2,$3)',[mid,moviename,year], (error, results) => {
        if (error) {
            response.status(400).send('ERROR 400: Bad Request!')
            throw error
        }
        response.status(201).send('SUCCESS 201: Movie added')
    })

};

const deleteMovie = (request,response) => {
    let mid = parseInt(request.params.id);
    db.query('DELETE FROM movies WHERE mid=$1',[mid], (error, results) => {
        if (error) {
            response.status(400).send('ERROR 400: Bad Request!')
            throw error
        }
        response.status(200).send('SUCCESS 200: Movie deleted')
    })
};

const addMovieToUser = (request,response) => {
    let mid = parseInt(request.params.mid);
    let userid = parseInt(request.params.userid);
    db.query('INSERT INTO user_movies (userid,mid) VALUES ($1,$2)',[userid,mid], (error, results) => {
        if (error) {
            response.status(400).send('ERROR 400: Bad Request!')
            throw error
        }
        response.status(200).send('SUCCESS 200: Movie addded to bookmark')
    })
};

const removeMovieFromUser = (request,response) => {
    let mid = parseInt(request.params.mid);
    let userid = parseInt(request.params.userid);
    db.query('DELETE FROM user_movies WHERE userid = $1 AND mid = $2',[userid,mid], (error, results) => {
        if (error) {
            response.status(400).send('ERROR 400: Bad Request!')
            throw error
        }
        response.status(200).send('SUCCESS 200: Movie removed from bookmark')
    })
};


// display queries

// do not use this getUsers
const getUsers = (request, response) => {
    db.query('SELECT users.* , user_movies.mid FROM users LEFT OUTER JOIN connection ON users.uuid = connection.bookmark_uuid ORDER BY users.uuid ASC', (error, results) => {
      if (error) {
        response.status(400).send('ERROR 400: BAD Request!')
        throw error
      }
      response.status(200).json(results.rows)
    })
  };

const getMovies = (request, response) => {
    db.query('SELECT * FROM movies ORDER BY mid', (error, results) => {
        if (error) {
            response.status(400).send('ERROR 400: BAD Request!')
            throw error
        }
        response.status(200).json(results.rows)
    })
};






module.exports = {
    getUsers,
    getMovies,
    addUser,
    deleteUser,
    addMovie,
    deleteMovie,
    addMovieToUser,
    removeMovieFromUser
}