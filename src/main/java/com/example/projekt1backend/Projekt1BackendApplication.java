package com.example.projekt1backend;

import com.example.projekt1backend.ageLimit.AgeLimit;
import com.example.projekt1backend.ageLimit.AgeLimitRepository;
import com.example.projekt1backend.genre.Genre;
import com.example.projekt1backend.genre.GenreRepository;
import com.example.projekt1backend.movie.Movie;
import com.example.projekt1backend.movie.MovieRepository;
import com.example.projekt1backend.movieStatus.MovieStatus;
import com.example.projekt1backend.movieStatus.MovieStatusRepository;
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
                                   MovieStatusRepository movieStatusRepo) {
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
            forrest.setAgeLimit(ageLimitRepo.findById(2).orElseThrow(() -> new RuntimeException("fail")));
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


            System.out.println("✅ Testdata indlæst i databasen!");
        };
    }
}