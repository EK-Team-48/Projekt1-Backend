package com.example.projekt1backend;


import com.example.projekt1backend.Seat.Seat;
import com.example.projekt1backend.Seat.SeatRepository;
import com.example.projekt1backend.Seat.Status;
import com.example.projekt1backend.Seat.StatusRepository;
import com.example.projekt1backend.theater.Theater;
import com.example.projekt1backend.theater.TheaterRepository;
import com.example.projekt1backend.ageLimit.entity.AgeLimit;
import com.example.projekt1backend.ageLimit.repository.AgeLimitRepository;
import com.example.projekt1backend.genre.entity.Genre;
import com.example.projekt1backend.genre.repository.GenreRepository;
import com.example.projekt1backend.movie.entity.Movie;
import com.example.projekt1backend.movie.repository.MovieRepository;
import com.example.projekt1backend.movieStatus.entity.MovieStatus;
import com.example.projekt1backend.movieStatus.repository.MovieStatusRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Set;

@SpringBootApplication
public class Projekt1BackendApplication {


    public static void main(String[] args) {
        SpringApplication.run(Projekt1BackendApplication.class, args);
    }

    @Bean
    CommandLineRunner loadTestData(MovieRepository movieRepo,
                                   GenreRepository genreRepo,
                                   AgeLimitRepository ageLimitRepo,
                                   MovieStatusRepository movieStatusRepo,
                                   TheaterRepository theaterRepo,
                                   SeatRepository seatRepo,
                                   StatusRepository statusRepo) {
        return args -> {
            // --- Genres ---
            Genre action = new Genre();
            action.setGenre("Action");


            Genre drama = new Genre();
            drama.setGenre("Drama");


            Genre comedy = new Genre();
            comedy.setGenre("Comedy");


            genreRepo.saveAll(Set.of(action, drama, comedy));


            // --- Age limits ---
            AgeLimit pg13 = new AgeLimit();
            pg13.setAgeRating(13);
            ageLimitRepo.save(pg13);


            AgeLimit pg18 = new AgeLimit();
            pg18.setAgeRating(18);
            ageLimitRepo.save(pg18);


            // --- Movie statuses ---
            MovieStatus released = new MovieStatus();
            released.setStatus("Released");
            movieStatusRepo.save(released);


            MovieStatus comingSoon = new MovieStatus();
            comingSoon.setStatus("Coming Soon");
            movieStatusRepo.save(comingSoon);


            // --- Movies ---
            Movie dieHard = new Movie();
            dieHard.setMovieTitle("Die Hard");
            dieHard.setDescription("Bruce Willis redder dagen");
            dieHard.setDuration(120);
            dieHard.setTrailerLink("https://youtu.be/2TQ-pOvI6Xo");
            dieHard.setAgeLimit(pg18);
            dieHard.setMovieStatus(released);
            dieHard.getGenres().add(action);


            Movie forrest = new Movie();
            forrest.setMovieTitle("Forrest Gump");
            forrest.setDescription("Livshistorie om Forrest Gump");
            forrest.setDuration(140);
            forrest.setTrailerLink("https://youtu.be/bLvqoHBptjg");
            forrest.setAgeLimit(pg18);
            forrest.setMovieStatus(released);
            forrest.getGenres().add(drama);
            forrest.getGenres().add(comedy);


            Movie avengers = new Movie();
            avengers.setMovieTitle("Avengers: Endgame");
            avengers.setDescription("Marvel superhelte samles");
            avengers.setDuration(180);
            avengers.setTrailerLink("https://youtu.be/TcMBFSGVi1c");
            avengers.setAgeLimit(pg13);
            avengers.setMovieStatus(comingSoon);
            avengers.getGenres().add(action);


            movieRepo.saveAll(Set.of(dieHard, forrest, avengers));

            Theater theater1 = new Theater();
            theater1.setTheaterName("SAL 1");
            theaterRepo.save(theater1);

            Theater theater2 = new Theater();
            theater2.setTheaterName("SAL 2");
            theaterRepo.save(theater2);

            Status status1 = new Status("Active");
            statusRepo.save(status1);

            Status status2 = new Status("Inactive");
            statusRepo.save(status2);


            //Largest theater of 25 rows and 16 seats per row
            for (int i = 1; i < 26; i++) {
                for (int j = 1; j < 17; j++) {
                    seatRepo.save(new Seat(j, i, theater1, status1));
                }
            }

            //Smallest theater of 20 rows and 12 seats per row
            for (int k = 1; k < 21; k++) {
                for (int l = 1; l < 13; l++) {
                    seatRepo.save(new Seat(l, k, theater2, status1));
                }
            }





            System.out.println("✅ Testdata indlæst i databasen!");
        };
    }
}