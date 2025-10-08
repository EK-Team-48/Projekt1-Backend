SET REFERENTIAL_INTEGRITY FALSE;

DROP TABLE IF EXISTS ticket;
DROP TABLE IF EXISTS reservation_seats;
DROP TABLE IF EXISTS booked_seats;
DROP TABLE IF EXISTS seat;
DROP TABLE IF EXISTS reservation;
DROP TABLE IF EXISTS screening;
DROP TABLE IF EXISTS movie_genre;
DROP TABLE IF EXISTS movie;
DROP TABLE IF EXISTS genre;
DROP TABLE IF EXISTS customer;
DROP TABLE IF EXISTS age_limit;
DROP TABLE IF EXISTS theater;
DROP TABLE IF EXISTS status;

CREATE TABLE age_limit
(
    age_limit_id INT AUTO_INCREMENT PRIMARY KEY,
    age_rating   INT
);

CREATE TABLE genre
(
    genre_id INT AUTO_INCREMENT PRIMARY KEY,
    genre    VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE customer
(
    customer_id INT AUTO_INCREMENT PRIMARY KEY,
    first_name  VARCHAR(255),
    last_name   VARCHAR(255),
    age         DATE,
    number      VARCHAR(255)
);

CREATE TABLE theater
(
    id           INT AUTO_INCREMENT PRIMARY KEY,
    theater_name VARCHAR(255)
);

CREATE TABLE movie
(
    movie_id        INT AUTO_INCREMENT PRIMARY KEY,
    movie_img       VARCHAR(255),
    movie_title     VARCHAR(255),
    description     TEXT,
    duration        INT,
    trailer_link    VARCHAR(255),
    age_limit_id    INT,
    CONSTRAINT FK_MOVIE_ON_AGE_LIMIT FOREIGN KEY (age_limit_id) REFERENCES age_limit (age_limit_id)
);

CREATE TABLE movie_genre
(
    genre_id INT NOT NULL,
    movie_id INT NOT NULL,
    CONSTRAINT pk_movie_genre PRIMARY KEY (genre_id, movie_id),
    CONSTRAINT fk_movgen_on_genre FOREIGN KEY (genre_id) REFERENCES genre (genre_id),
    CONSTRAINT fk_movgen_on_movie FOREIGN KEY (movie_id) REFERENCES movie (movie_id)
);

CREATE TABLE screening
(
    screening_id   INT AUTO_INCREMENT PRIMARY KEY,
    movie_id       INT,
    theater_id     INT,
    screening_date DATE,
    start_time     INT,
    price DOUBLE,
    CONSTRAINT FK_SCREENING_ON_MOVIE FOREIGN KEY (movie_id) REFERENCES movie (movie_id),
    CONSTRAINT FK_SCREENING_ON_THEATER FOREIGN KEY (theater_id) REFERENCES theater (id)
);

CREATE TABLE reservation
(
    reservation_id INT AUTO_INCREMENT PRIMARY KEY,
    customer_id    INT,
    screening_id   INT,
    CONSTRAINT FK_RESERVATION_ON_CUSTOMER FOREIGN KEY (customer_id) REFERENCES customer (customer_id),
    CONSTRAINT FK_RESERVATION_ON_SCREENING FOREIGN KEY (screening_id) REFERENCES screening (screening_id)
);

CREATE TABLE status
(
    status_id   INT AUTO_INCREMENT PRIMARY KEY,
    status_name VARCHAR(255)
);

CREATE TABLE seat
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    seat_number INT,
    seat_row    INT,
    status_id   INT,
    theater_id  INT,
    CONSTRAINT FK_SEAT_ON_STATUSID FOREIGN KEY (status_id) REFERENCES status (status_id),
    CONSTRAINT FK_SEAT_ON_THEATER FOREIGN KEY (theater_id) REFERENCES theater (id)
);

CREATE TABLE ticket
(
    ticket_id      INT AUTO_INCREMENT PRIMARY KEY,
    reservation_id INT,
