CREATE DATABASE friendflix;


CREATE TABLE users (
    userid VARCHAR     PRIMARY KEY     NOT NULL,
    userEmail varchar(255)
);
CREATE TABLE movies (
    mid VARCHAR     PRIMARY KEY     NOT NULL,
    moviename varchar(255),
    year int
);
CREATE TABLE friends (
    userid varchar(255) NOT NULL ,
    frienduid varchar(255)
);
CREATE TABLE user_movies (
    userid VARCHAR NOT NULL,
    mid VARCHAR
);

INSERT INTO users (userid, userEmail)
  VALUES ('XYZ','chinmay@gmail.com');

INSERT INTO movies (mid,moviename,year) VALUES ('ABC','SOMEA',NULL);

Select * from users;
