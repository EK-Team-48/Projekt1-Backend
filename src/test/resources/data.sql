SET REFERENTIAL_INTEGRITY FALSE;

DROP TABLE IF EXISTS ticket;
DROP TABLE IF EXISTS seat;
DROP TABLE IF EXISTS reservation;
DROP TABLE IF EXISTS screening;
DROP TABLE IF EXISTS movie_genre;
DROP TABLE IF EXISTS movie;
DROP TABLE IF EXISTS genre;
DROP TABLE IF EXISTS customer;
DROP TABLE IF EXISTS age_limit;
DROP TABLE IF EXISTS movie_status;
DROP TABLE IF EXISTS theater;
DROP TABLE IF EXISTS status;

CREATE TABLE age_limit
(
    age_limit_id INT AUTO_INCREMENT PRIMARY KEY,
    age_rating   INT
);

CREATE TABLE customer
(
    customer_id INT AUTO_INCREMENT PRIMARY KEY,
    first_name  VARCHAR(255),
    last_name   VARCHAR(255),
    age         DATE,
    number      VARCHAR(255)
);

CREATE TABLE genre
(
    genre_id INT AUTO_INCREMENT PRIMARY KEY,
    genre    VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE movie_status
(
    movie_status_id INT AUTO_INCREMENT PRIMARY KEY,
    status          VARCHAR(255)
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
    movie_status_id INT,
    CONSTRAINT FK_MOVIE_ON_AGE_LIMIT FOREIGN KEY (age_limit_id) REFERENCES age_limit (age_limit_id),
    CONSTRAINT FK_MOVIE_ON_MOVIESTATUSID FOREIGN KEY (movie_status_id) REFERENCES movie_status (movie_status_id)
);

CREATE TABLE movie_genre
(
    genre_id INT NOT NULL,
    movie_id INT NOT NULL,
    CONSTRAINT pk_movie_genre PRIMARY KEY (genre_id, movie_id),
    CONSTRAINT fk_movgen_on_genre FOREIGN KEY (genre_id) REFERENCES genre (genre_id),
    CONSTRAINT fk_movgen_on_movie FOREIGN KEY (movie_id) REFERENCES movie (movie_id)
);

CREATE TABLE theater
(
    id INT AUTO_INCREMENT PRIMARY KEY,
    theater_name VARCHAR(255)
);

CREATE TABLE screening
(
    screening_id INT AUTO_INCREMENT PRIMARY KEY,
    movie_id     INT,
    theater_id   INT,
    screening_date         DATE,
    start_time   INT,
    price        DOUBLE,
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
    seat_id        INT,
    CONSTRAINT FK_TICKET_ON_RESERVATION FOREIGN KEY (reservation_id) REFERENCES reservation (reservation_id),
    CONSTRAINT FK_TICKET_ON_SEAT FOREIGN KEY (seat_id) REFERENCES seat (id)
);

INSERT INTO age_limit (age_rating) VALUES (0);
INSERT INTO age_limit (age_rating) VALUES (7);
INSERT INTO age_limit (age_rating) VALUES (11);
INSERT INTO age_limit (age_rating) VALUES (15);

INSERT INTO movie_status (status) VALUES ('Coming Soon');
INSERT INTO movie_status (status) VALUES ('Now Showing');
INSERT INTO movie_status (status) VALUES ('Archived');

INSERT INTO genre (genre) VALUES ('Action');
INSERT INTO genre (genre) VALUES ('Comedy');
INSERT INTO genre (genre) VALUES ('Drama');
INSERT INTO genre (genre) VALUES ('Sci-Fi');

INSERT INTO movie (movie_img, movie_title, description, duration, trailer_link, age_limit_id, movie_status_id)
VALUES ('img1.jpg', 'Fast & Curious', 'An action movie about car chases and thinking too much.', 120, 'https://youtu.be/trailer1', 2, 2);

INSERT INTO movie (movie_img, movie_title, description, duration, trailer_link, age_limit_id, movie_status_id)
VALUES ('img2.jpg', 'Laugh Hard', 'A ridiculous comedy about two guys coding.', 95, 'https://youtu.be/trailer2', 1, 2);

INSERT INTO movie (movie_img, movie_title, description, duration, trailer_link, age_limit_id, movie_status_id)
VALUES ('img3.jpg', 'Sad Robots', 'Dystopian drama about AI discovering feelings.', 140, 'https://youtu.be/trailer3', 3, 1);

INSERT INTO movie_genre (genre_id, movie_id) VALUES (1, 1);
INSERT INTO movie_genre (genre_id, movie_id) VALUES (2, 2);
INSERT INTO movie_genre (genre_id, movie_id) VALUES (4, 3);
INSERT INTO movie_genre (genre_id, movie_id) VALUES (3, 3);

INSERT INTO customer (first_name, last_name, age, number)
VALUES ('Alice', 'Andersen', '1995-04-12', '12345678');

INSERT INTO customer (first_name, last_name, age, number)
VALUES ('Bob', 'Berg', '2001-11-23', '87654321');

INSERT INTO theater (theater_name) VALUES ('Main Hall');
INSERT INTO theater (theater_name) VALUES ('VIP Lounge');

INSERT INTO screening (movie_id, theater_id, screening_date, start_time, price)
VALUES (1, 1, '2025-10-01', '123', 95.0);

INSERT INTO screening (movie_id, theater_id, screening_date, start_time, price)
VALUES (2, 2, '2025-10-01','123', 120.0);

INSERT INTO reservation (customer_id, screening_id) VALUES (1, 1);
INSERT INTO reservation (customer_id, screening_id) VALUES (2, 2);

INSERT INTO status (status_name) VALUES ('Available');
INSERT INTO status (status_name) VALUES ('Reserved');
INSERT INTO status (status_name) VALUES ('Broken');

INSERT INTO seat (seat_number, seat_row, status_id, theater_id) VALUES (1, 1, 1, 1);
INSERT INTO seat (seat_number, seat_row, status_id, theater_id) VALUES (2, 1, 2, 1);
INSERT INTO seat (seat_number, seat_row, status_id, theater_id) VALUES (1, 1, 1, 2);

INSERT INTO ticket (reservation_id, seat_id) VALUES (1, 1);
INSERT INTO ticket (reservation_id, seat_id) VALUES (2, 3);
