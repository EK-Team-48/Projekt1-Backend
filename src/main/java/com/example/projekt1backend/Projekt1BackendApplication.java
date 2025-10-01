package com.example.projekt1backend;


import com.example.projekt1backend.Seat.model.Seat;
import com.example.projekt1backend.Seat.repository.SeatRepository;
import com.example.projekt1backend.theater.model.Theater;
import com.example.projekt1backend.theater.repository.TheaterRepository;
import com.example.projekt1backend.Seat.model.Seat;
import com.example.projekt1backend.Seat.repository.SeatRepository;

import com.example.projekt1backend.screening.model.Screening;
import com.example.projekt1backend.screening.repository.ScreeningRepository;
import com.example.projekt1backend.theater.model.Theater;
import com.example.projekt1backend.theater.repository.TheaterRepository;
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

import java.time.LocalDate;
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
                                   ScreeningRepository screeningRepository) {
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
            dieHard.setMovieImg("https://media.posterlounge.com/img/products/710000/705263/705263_poster.jpg");
            dieHard.setDescription("Die Hard is a 1988 American action film about NYPD detective John McClane (Bruce Willis), who must single-handedly stop a group of terrorists, led by the sophisticated Hans Gruber (Alan Rickman), from holding his wife and others hostage during a Christmas Eve party at a Los Angeles skyscraper, where they plan to steal millions in bearer bonds. McClane uses the building's structure to his advantage to fight the terrorists and rescue the hostages, all while dealing with the often incompetent local authorities.");
            dieHard.setDuration(120);
            dieHard.setTrailerLink("https://www.youtube.com/watch?v=gYWvwkXreaI");
            dieHard.setAgeLimit(pg18);
            dieHard.setMovieStatus(released);
            dieHard.getGenres().add(action);


            Movie forrest = new Movie();
            forrest.setMovieTitle("Forrest Gump");
            forrest.setMovieImg("https://storage.googleapis.com/pod_public/750/266241.jpg");
            forrest.setDescription("Forrest Gump is the story of a kind-hearted, simple-minded man with a low IQ who inadvertently becomes involved in many significant events of 20th-century American history, from the Vietnam War to meeting U.S. Presidents, while pursuing a life defined by optimism, love for his childhood friend Jenny, and an innate, simple wisdom. Portrayed by Tom Hanks in the Academy Award-winning film, Forrest experiences success as a college football star, ping-pong champion, shrimp boat captain, and philanthropist, all while maintaining a compassionate and tenacious spirit.");
            forrest.setDuration(140);
            forrest.setTrailerLink("https://www.youtube.com/watch?v=bLvqoHBptjg");
            forrest.setAgeLimit(pg18);
            forrest.setMovieStatus(released);
            forrest.getGenres().add(drama);
            forrest.getGenres().add(comedy);


            Movie avengers = new Movie();
            avengers.setMovieTitle("Avengers: Endgame");
            avengers.setMovieImg("https://static.posters.cz/image/1300/122136.jpg");
            avengers.setDescription("Avengers: Endgame is the climactic finale to Marvel's Infinity Saga, where the remaining Avengers attempt to reverse the devastation caused by Thanos's Infinity Stone snap, which eliminated half of all life in the universe. The story follows the shattered team, including Iron Man, Captain America, and Hulk, as they undertake a risky mission involving time travel to undo the destruction and bring back their lost loved ones, leading to a final, decisive confrontation with Thanos.");
            avengers.setDuration(180);
            avengers.setTrailerLink("https://www.youtube.com/watch?v=TcMBFSGVi1c");
            avengers.setAgeLimit(pg13);
            avengers.setMovieStatus(comingSoon);
            avengers.getGenres().add(action);

            Movie darkKnight = new Movie();
            darkKnight.setMovieTitle("The Dark Knight");
            darkKnight.setMovieImg("https://static.posters.cz/image/1300/198201.jpg");
            darkKnight.setDescription("The Dark Knight is a superhero film where Batman, Lt. James Gordon, and new District Attorney Harvey Dent form an alliance to combat Gotham's organized crime, only to be challenged by the chaotic, psychopathic Joker, a criminal mastermind who unleashes a reign of terror and manipulates the city's systems. The film explores themes of heroism, justice, and the line between good and evil as Batman, Dent, and the Joker engage in a battle of wills that threatens to consume Gotham.");
            darkKnight.setDuration(152);
            darkKnight.setTrailerLink("https://www.youtube.com/watch?v=EXeTwQWrcwY");
            darkKnight.setAgeLimit(pg13);
            darkKnight.setMovieStatus(released);
            darkKnight.getGenres().add(action);
            darkKnight.getGenres().add(drama);

            Movie inception = new Movie();
            inception.setMovieTitle("Inception");
            inception.setMovieImg("https://storage.googleapis.com/pod_public/800webp/263129.webp");
            inception.setDescription("Inception is a 2010 science fiction action film about Dom Cobb, a skilled thief who steals corporate secrets from people's minds by entering their dreams. Cobb is hired to perform the inverse task: inception, which is the act of planting an idea into a target's subconscious. To achieve this, he and his team must navigate multiple layers of dreams, facing both external threats and Cobb's own personal demons.");
            inception.setDuration(148);
            inception.setTrailerLink("https://www.youtube.com/watch?v=YoHD9XEInc0");
            inception.setAgeLimit(pg13);
            inception.setMovieStatus(released);
            inception.getGenres().add(action);
            inception.getGenres().add(drama);

            Movie matrix = new Movie();
            matrix.setMovieTitle("The Matrix");
            matrix.setMovieImg("https://static.posters.cz/image/1300/104636.jpg");
            matrix.setDescription("The Matrix is a 1999 science fiction action film where a computer programmer named Neo, who is also a hacker, discovers that his reality is a simulation created by intelligent machines. Neo joins a rebellion of free humans against the machines, who are using humanity as an energy source, and learns he may be \"the One\" prophesied to end the war. He must confront powerful computer programs called Agents and master his abilities within the Matrix to save humanity.");
            matrix.setDuration(136);
            matrix.setTrailerLink("https://www.youtube.com/watch?v=vKQi3bBA1y8");
            matrix.setAgeLimit(pg18);
            matrix.setMovieStatus(released);
            matrix.getGenres().add(action);
            matrix.getGenres().add(drama);

            Movie shawshank = new Movie();
            shawshank.setMovieTitle("The Shawshank Redemption");
            shawshank.setMovieImg("https://m.media-amazon.com/images/I/71715eBi1sL._AC_SL1000_.jpg");
            shawshank.setDescription("The Shawshank Redemption is a 1994 prison drama film about banker Andy Dufresne, who is wrongfully convicted of murdering his wife and her lover. Over two decades at Shawshank State Penitentiary, Andy befriends fellow prisoner Red and uses his banking skills to launder money for the corrupt warden. The film explores themes of hope, resilience, and the corrupt nature of the prison system, culminating in Andy's eventual escape and a powerful reunion with Red.");
            shawshank.setDuration(142);
            shawshank.setTrailerLink("https://www.youtube.com/watch?v=PLl99DlL6b4");
            shawshank.setAgeLimit(pg18);
            shawshank.setMovieStatus(released);
            shawshank.getGenres().add(drama);

            Movie interstellar = new Movie();
            interstellar.setMovieTitle("Interstellar");
            interstellar.setMovieImg("https://static.posters.cz/image/750/23157.jpg");
            interstellar.setDescription("Interstellar is a 2014 science fiction film directed by Christopher Nolan that follows a team of astronauts who travel through a newly discovered wormhole near Saturn in search of a new home for humanity. Set in a dystopian future where a catastrophic blight is making Earth uninhabitable, the film centers on ex-NASA pilot Joseph Cooper as he undertakes a dangerous mission, leaving his family behind to save the human race from extinction. The epic journey explores themes of love, sacrifice, and the human drive for survival as the crew seeks a new world among the stars, facing challenges including time dilation and the vastness of space.");
            interstellar.setDuration(169);
            interstellar.setTrailerLink("https://www.youtube.com/watch?v=zSWdZVtXT7E");
            interstellar.setAgeLimit(pg13);
            interstellar.setMovieStatus(released);
            interstellar.getGenres().add(drama);
            interstellar.getGenres().add(action);

            Movie godfather = new Movie();
            godfather.setMovieTitle("The Godfather");
            godfather.setMovieImg("https://static.posters.cz/image/750/106943.jpg");
            godfather.setDescription("The Godfather is a 1972 American crime film that follows the powerful Italian-American Corleone mafia family in the years following World War II, chronicling the transformation of the patriarch Don Vito Corleone's youngest son, Michael, from a family outsider to a ruthless crime boss. Directed by Francis Ford Coppola, the critically acclaimed film explores themes of loyalty, power, and betrayal as the family navigates a brutal world of organized crime.");
            godfather.setDuration(175);
            godfather.setTrailerLink("https://www.youtube.com/watch?v=UaVTIH8mujA");
            godfather.setAgeLimit(pg18);
            godfather.setMovieStatus(released);
            godfather.getGenres().add(drama);

            Movie lotr1 = new Movie();
            lotr1.setMovieTitle("The Lord of the Rings: The Fellowship of the Ring");
            lotr1.setMovieImg("https://static.posters.cz/image/750/11723.jpg");
            lotr1.setDescription("The Lord of the Rings: The Fellowship of the Ring is the 2001 epic high fantasy film directed by Peter Jackson and the first installment in The Lord of the Rings trilogy. It follows Frodo Baggins, a young Hobbit, as he undertakes the daunting task of destroying the powerful One Ring in Mount Doom to save Middle-earth from the Dark Lord Sauron, with a fellowship of nine companions joining him on this perilous quest.");
            lotr1.setDuration(178);
            lotr1.setTrailerLink("https://www.youtube.com/watch?v=V75dMMIW2B4");
            lotr1.setAgeLimit(pg13);
            lotr1.setMovieStatus(comingSoon);
            lotr1.getGenres().add(action);
            lotr1.getGenres().add(drama);

            movieRepo.saveAll(Set.of(dieHard, forrest, avengers, darkKnight, inception, matrix, shawshank, interstellar, godfather, lotr1));

            Theater theater1 = new Theater();
            theater1.setTheaterName("SAL 1");
            theaterRepo.save(theater1);

            Theater theater2 = new Theater();
            theater2.setTheaterName("SAL 2");
            theaterRepo.save(theater2);


            //Largest theater of 25 rows and 16 seats per row
            for (int i = 1; i < 26; i++) {
                for (int j = 1; j < 17; j++) {
                    seatRepo.save(new Seat(j, i, theater1));
                }
            }

            //Smallest theater of 20 rows and 12 seats per row
            for (int k = 1; k < 21; k++) {
                for (int l = 1; l < 13; l++) {
                    seatRepo.save(new Seat(l, k, theater2));
                }
            }

            //Screenings test data:
            Screening screening1 = new Screening();
            screening1.setMovieId(dieHard);
            screening1.setScreeningDate(LocalDate.now());
            screening1.setTheaterId(theater1);
            screening1.setStartTime(1800);
            screening1.setPrice(150.0);
            screeningRepository.save(screening1);

            Screening screening4 = new Screening();
            screening4.setScreeningDate(LocalDate.now());
            screening4.setMovieId(dieHard);
            screening4.setTheaterId(theater1);
            screening4.setStartTime(1500);
            screening4.setPrice(180.0);
            screeningRepository.save(screening4);

            Screening screening5 = new Screening();
            screening5.setScreeningDate(LocalDate.now());
            screening5.setMovieId(dieHard);
            screening5.setTheaterId(theater2);
            screening5.setStartTime(1700);
            screening5.setPrice(180.0);
            screeningRepository.save(screening5);

            Screening screening2 = new Screening();
            screening2.setScreeningDate(LocalDate.now());
            screening2.setMovieId(forrest);
            screening2.setTheaterId(theater2);
            screening2.setStartTime(2000);
            screening2.setPrice(145.50);
            screeningRepository.save(screening2);


            Screening screening3 = new Screening();
            screening3.setScreeningDate(LocalDate.now());
            screening3.setMovieId(darkKnight);
            screening3.setTheaterId(theater2);
            screening3.setStartTime(1230);
            screening3.setPrice(140.0);
            screeningRepository.save(screening3);





            System.out.println("✅ Testdata indlæst i databasen!");
        };
    }
}