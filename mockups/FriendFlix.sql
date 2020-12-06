CREATE TABLE app_user (
    numid int,
    fullname varchar(255),
    userid varchar(255),
    created_at timestamp,
	PRIMARY KEY (numid)
    
);
CREATE TABLE movie (
    numid int,
    moviename varchar(255),
    mid int,
    rating_imdb int,
    rating_rotton int,
	PRIMARY KEY (numid)
);
CREATE TABLE friends (
    userid varchar(255),
    frienduid varchar(255)
);
CREATE TABLE user_movies (
    userid varchar(255),
    mid int
);