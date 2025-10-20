package com.example.projekt1backend;


import com.example.projekt1backend.seat.model.Seat;
import com.example.projekt1backend.seat.repository.SeatRepository;
import com.example.projekt1backend.customer.entity.Customer;
import com.example.projekt1backend.customer.repository.CustomerRepository;
import com.example.projekt1backend.employee.entity.Employee;
import com.example.projekt1backend.employee.entity.EmployeeType;
import com.example.projekt1backend.employee.repository.EmployeeRespository;
import com.example.projekt1backend.reservation.entity.Reservation;
import com.example.projekt1backend.reservation.repository.ReservationRepository;
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

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
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
                                   TheaterRepository theaterRepo,
                                   SeatRepository seatRepo,
                                   ScreeningRepository screeningRepo,
                                   CustomerRepository customerRepo,
                                   ReservationRepository reservationRepo,
                                   EmployeeRespository employeeRepo) {

        return args -> {

            // --- GENRES ---
            Genre action = new Genre();        action.setGenre("Action");
            Genre adventure = new Genre();     adventure.setGenre("Adventure");
            Genre animation = new Genre();     animation.setGenre("Animation");
            Genre biography = new Genre();     biography.setGenre("Biography");
            Genre comedy = new Genre();        comedy.setGenre("Comedy");
            Genre crime = new Genre();         crime.setGenre("Crime");
            Genre documentary = new Genre();   documentary.setGenre("Documentary");
            Genre drama = new Genre();         drama.setGenre("Drama");
            Genre family = new Genre();        family.setGenre("Family");
            Genre fantasy = new Genre();       fantasy.setGenre("Fantasy");
            Genre filmNoir = new Genre();      filmNoir.setGenre("Film-Noir");
            Genre history = new Genre();       history.setGenre("History");
            Genre horror = new Genre();        horror.setGenre("Horror");
            Genre music = new Genre();         music.setGenre("Music");
            Genre musical = new Genre();       musical.setGenre("Musical");
            Genre mystery = new Genre();       mystery.setGenre("Mystery");
            Genre romance = new Genre();       romance.setGenre("Romance");
            Genre sciFi = new Genre();         sciFi.setGenre("Sci-Fi");
            Genre sport = new Genre();         sport.setGenre("Sport");
            Genre thriller = new Genre();      thriller.setGenre("Thriller");
            Genre war = new Genre();           war.setGenre("War");
            Genre western = new Genre();       western.setGenre("Western");

            // --- AGE LIMITS ---
            AgeLimit pg7 = new AgeLimit(); pg7.setAgeRating(7);
            AgeLimit pg13 = new AgeLimit(); pg13.setAgeRating(13);
            AgeLimit pg18 = new AgeLimit(); pg18.setAgeRating(18);
            ageLimitRepo.saveAll(Set.of(pg13, pg18));


            // --- MOVIES ---
            Movie dieHard = new Movie();
            dieHard.setMovieTitle("Die Hard");
            dieHard.setMovieImg("https://media.posterlounge.com/img/products/710000/705263/705263_poster.jpg");
            dieHard.setDescription("Classic 80s action with Bruce Willis.");
            dieHard.setDuration(120);
            dieHard.setTrailerLink("https://www.youtube.com/watch?v=gYWvwkXreaI");
            dieHard.setAgeLimit(pg18);
            dieHard.getGenres().add(action);
            movieRepo.save(dieHard);

            Movie forrest = new Movie();
            forrest.setMovieTitle("Forrest Gump");
            forrest.setMovieImg("https://storage.googleapis.com/pod_public/750/266241.jpg");
            forrest.setDescription("A simple man with an extraordinary life.");
            forrest.setDuration(140);
            forrest.setTrailerLink("https://www.youtube.com/watch?v=bLvqoHBptjg");
            forrest.setAgeLimit(pg13);
            forrest.getGenres().add(drama);
            forrest.getGenres().add(comedy);
            movieRepo.save(forrest);

            Movie darkKnight = new Movie();
            darkKnight.setMovieTitle("The Dark Knight");
            darkKnight.setMovieImg("");
            darkKnight.setDescription("Batman faces the Joker in a gritty crime saga.");
            darkKnight.setDuration(152);
            darkKnight.setTrailerLink("");
            darkKnight.setAgeLimit(pg13);
            darkKnight.getGenres().add(action);
            darkKnight.getGenres().add(crime);
            movieRepo.save(darkKnight);

            Movie inception = new Movie();
            inception.setMovieTitle("Inception");
            inception.setMovieImg("");
            inception.setDescription("A team dives into dreams to plant an idea.");
            inception.setDuration(148);
            inception.setTrailerLink("");
            inception.setAgeLimit(pg13);
            inception.getGenres().add(sciFi);
            inception.getGenres().add(action);
            movieRepo.save(inception);

            Movie godfather = new Movie();
            godfather.setMovieTitle("The Godfather");
            godfather.setMovieImg("");
            godfather.setDescription("Epic tale of a crime family and its legacy.");
            godfather.setDuration(175);
            godfather.setTrailerLink("");
            godfather.setAgeLimit(pg18);
            godfather.getGenres().add(crime);
            godfather.getGenres().add(drama);
            movieRepo.save(godfather);

            Movie findingNemo = new Movie();
            findingNemo.setMovieTitle("Finding Nemo");
            findingNemo.setMovieImg("");
            findingNemo.setDescription("A timid clownfish braves the ocean to find his son.");
            findingNemo.setDuration(100);
            findingNemo.setTrailerLink("");
            findingNemo.setAgeLimit(pg7);
            findingNemo.getGenres().add(animation);
            findingNemo.getGenres().add(family);
            movieRepo.save(findingNemo);

            Movie spiritedAway = new Movie();
            spiritedAway.setMovieTitle("Spirited Away");
            spiritedAway.setMovieImg("");
            spiritedAway.setDescription("A girl enters a spirit world to save her parents.");
            spiritedAway.setDuration(125);
            spiritedAway.setTrailerLink("");
            spiritedAway.setAgeLimit(pg7);
            spiritedAway.getGenres().add(animation);
            spiritedAway.getGenres().add(fantasy);
            movieRepo.save(spiritedAway);

            Movie shawshank = new Movie();
            shawshank.setMovieTitle("The Shawshank Redemption");
            shawshank.setMovieImg("");
            shawshank.setDescription("Hope sustains two prisoners through decades.");
            shawshank.setDuration(142);
            shawshank.setTrailerLink("");
            shawshank.setAgeLimit(pg13);
            shawshank.getGenres().add(drama);
            movieRepo.save(shawshank);

            Movie parasite = new Movie();
            parasite.setMovieTitle("Parasite");
            parasite.setMovieImg("");
            parasite.setDescription("Two families collide in a sharp social thriller.");
            parasite.setDuration(132);
            parasite.setTrailerLink("");
            parasite.setAgeLimit(pg18);
            parasite.getGenres().add(thriller);
            parasite.getGenres().add(drama);
            movieRepo.save(parasite);

            Movie laLaLand = new Movie();
            laLaLand.setMovieTitle("La La Land");
            laLaLand.setMovieImg("");
            laLaLand.setDescription("Love and ambition in modern-day Los Angeles.");
            laLaLand.setDuration(128);
            laLaLand.setTrailerLink("");
            laLaLand.setAgeLimit(pg13);
            laLaLand.getGenres().add(romance);
            laLaLand.getGenres().add(musical);
            movieRepo.save(laLaLand);

            Movie matrix = new Movie();
            matrix.setMovieTitle("The Matrix");
            matrix.setMovieImg("");
            matrix.setDescription("A hacker discovers reality is a simulation.");
            matrix.setDuration(136);
            matrix.setTrailerLink("");
            matrix.setAgeLimit(pg13);
            matrix.getGenres().add(sciFi);
            matrix.getGenres().add(action);
            movieRepo.save(matrix);

            Movie jurassicPark = new Movie();
            jurassicPark.setMovieTitle("Jurassic Park");
            jurassicPark.setMovieImg("");
            jurassicPark.setDescription("Dinosaurs roam a theme park with deadly results.");
            jurassicPark.setDuration(127);
            jurassicPark.setTrailerLink("");
            jurassicPark.setAgeLimit(pg13);
            jurassicPark.getGenres().add(adventure);
            jurassicPark.getGenres().add(sciFi);
            movieRepo.save(jurassicPark);

            Movie lionKing = new Movie();
            lionKing.setMovieTitle("The Lion King");
            lionKing.setMovieImg("");
            lionKing.setDescription("A young lion prince finds his place in the circle of life.");
            lionKing.setDuration(88);
            lionKing.setTrailerLink("");
            lionKing.setAgeLimit(pg7);
            lionKing.getGenres().add(animation);
            lionKing.getGenres().add(family);
            movieRepo.save(lionKing);

            Movie casablanca = new Movie();
            casablanca.setMovieTitle("Casablanca");
            casablanca.setMovieImg("");
            casablanca.setDescription("Love and sacrifice during World War II.");
            casablanca.setDuration(102);
            casablanca.setTrailerLink("");
            casablanca.setAgeLimit(pg7);
            casablanca.getGenres().add(romance);
            casablanca.getGenres().add(drama);
            movieRepo.save(casablanca);

            Movie furyRoad = new Movie();
            furyRoad.setMovieTitle("Mad Max: Fury Road");
            furyRoad.setMovieImg("");
            furyRoad.setDescription("A high-octane chase across a wasteland.");
            furyRoad.setDuration(120);
            furyRoad.setTrailerLink("");
            furyRoad.setAgeLimit(pg18);
            furyRoad.getGenres().add(action);
            furyRoad.getGenres().add(adventure);
            movieRepo.save(furyRoad);

            Movie getOut = new Movie();
            getOut.setMovieTitle("Get Out");
            getOut.setMovieImg("");
            getOut.setDescription("A tense visit turns into a surreal nightmare.");
            getOut.setDuration(104);
            getOut.setTrailerLink("");
            getOut.setAgeLimit(pg18);
            getOut.getGenres().add(horror);
            getOut.getGenres().add(mystery);
            movieRepo.save(getOut);

            Movie socialNetwork = new Movie();
            socialNetwork.setMovieTitle("The Social Network");
            socialNetwork.setMovieImg("");
            socialNetwork.setDescription("The contentious rise of a tech giant.");
            socialNetwork.setDuration(120);
            socialNetwork.setTrailerLink("");
            socialNetwork.setAgeLimit(pg13);
            socialNetwork.getGenres().add(drama);
            socialNetwork.getGenres().add(biography);
            movieRepo.save(socialNetwork);

            Movie nineteenSeventeen = new Movie();
            nineteenSeventeen.setMovieTitle("1917");
            nineteenSeventeen.setMovieImg("");
            nineteenSeventeen.setDescription("Two soldiers race to deliver a lifesaving message.");
            nineteenSeventeen.setDuration(119);
            nineteenSeventeen.setTrailerLink("");
            nineteenSeventeen.setAgeLimit(pg18);
            nineteenSeventeen.getGenres().add(war);
            nineteenSeventeen.getGenres().add(drama);
            movieRepo.save(nineteenSeventeen);

            Movie princessBride = new Movie();
            princessBride.setMovieTitle("The Princess Bride");
            princessBride.setMovieImg("");
            princessBride.setDescription("A fairy-tale adventure with true love and pirates.");
            princessBride.setDuration(98);
            princessBride.setTrailerLink("");
            princessBride.setAgeLimit(pg7);
            princessBride.getGenres().add(adventure);
            princessBride.getGenres().add(comedy);
            princessBride.getGenres().add(romance);
            movieRepo.save(princessBride);

            Movie interstellar = new Movie();
            interstellar.setMovieTitle("Interstellar");
            interstellar.setMovieImg("");
            interstellar.setDescription("Explorers journey through a wormhole to save humanity.");
            interstellar.setDuration(169);
            interstellar.setTrailerLink("");
            interstellar.setAgeLimit(pg13);
            interstellar.getGenres().add(sciFi);
            interstellar.getGenres().add(drama);
            movieRepo.save(interstellar);

            Movie whiplash = new Movie();
            whiplash.setMovieTitle("Whiplash");
            whiplash.setMovieImg("");
            whiplash.setDescription("A drummer pushes himself under a ruthless mentor.");
            whiplash.setDuration(107);
            whiplash.setTrailerLink("");
            whiplash.setAgeLimit(pg13);
            whiplash.getGenres().add(drama);
            whiplash.getGenres().add(music);
            movieRepo.save(whiplash);

            Movie bigLebowski = new Movie();
            bigLebowski.setMovieTitle("The Big Lebowski");
            bigLebowski.setMovieImg("");
            bigLebowski.setDescription("The Dude gets tangled in a bizarre kidnapping.");
            bigLebowski.setDuration(117);
            bigLebowski.setTrailerLink("");
            bigLebowski.setAgeLimit(pg18);
            bigLebowski.getGenres().add(comedy);
            bigLebowski.getGenres().add(crime);
            movieRepo.save(bigLebowski);

            Movie grandBudapest = new Movie();
            grandBudapest.setMovieTitle("The Grand Budapest Hotel");
            grandBudapest.setMovieImg("");
            grandBudapest.setDescription("A concierge and lobby boy in a caper across Europe.");
            grandBudapest.setDuration(100);
            grandBudapest.setTrailerLink("");
            grandBudapest.setAgeLimit(pg13);
            grandBudapest.getGenres().add(comedy);
            grandBudapest.getGenres().add(crime);
            movieRepo.save(grandBudapest);

            Movie silenceLambs = new Movie();
            silenceLambs.setMovieTitle("The Silence of the Lambs");
            silenceLambs.setMovieImg("");
            silenceLambs.setDescription("An FBI trainee seeks a killer with a cannibal’s help.");
            silenceLambs.setDuration(118);
            silenceLambs.setTrailerLink("");
            silenceLambs.setAgeLimit(pg18);
            silenceLambs.getGenres().add(thriller);
            silenceLambs.getGenres().add(crime);
            movieRepo.save(silenceLambs);

            Movie seven = new Movie();
            seven.setMovieTitle("Se7en");
            seven.setMovieImg("");
            seven.setDescription("Detectives hunt a killer using the seven deadly sins.");
            seven.setDuration(127);
            seven.setTrailerLink("");
            seven.setAgeLimit(pg18);
            seven.getGenres().add(crime);
            seven.getGenres().add(mystery);
            seven.getGenres().add(thriller);
            movieRepo.save(seven);

            Movie rocky = new Movie();
            rocky.setMovieTitle("Rocky");
            rocky.setMovieImg("");
            rocky.setDescription("An underdog boxer gets a shot at the title.");
            rocky.setDuration(120);
            rocky.setTrailerLink("");
            rocky.setAgeLimit(pg13);
            rocky.getGenres().add(sport);
            rocky.getGenres().add(drama);
            movieRepo.save(rocky);

            Movie goodBadUgly = new Movie();
            goodBadUgly.setMovieTitle("The Good, the Bad and the Ugly");
            goodBadUgly.setMovieImg("");
            goodBadUgly.setDescription("Three gunslingers chase buried gold in the Old West.");
            goodBadUgly.setDuration(161);
            goodBadUgly.setTrailerLink("");
            goodBadUgly.setAgeLimit(pg13);
            goodBadUgly.getGenres().add(western);
            goodBadUgly.getGenres().add(adventure);
            movieRepo.save(goodBadUgly);

            Movie noCountry = new Movie();
            noCountry.setMovieTitle("No Country for Old Men");
            noCountry.setMovieImg("");
            noCountry.setDescription("A hunter, a hitman, and a sheriff cross paths over stolen cash.");
            noCountry.setDuration(122);
            noCountry.setTrailerLink("");
            noCountry.setAgeLimit(pg18);
            noCountry.getGenres().add(crime);
            noCountry.getGenres().add(thriller);
            movieRepo.save(noCountry);

            Movie exorcist = new Movie();
            exorcist.setMovieTitle("The Exorcist");
            exorcist.setMovieImg("");
            exorcist.setDescription("A possessed girl and a desperate fight against evil.");
            exorcist.setDuration(122);
            exorcist.setTrailerLink("");
            exorcist.setAgeLimit(pg18);
            exorcist.getGenres().add(horror);
            movieRepo.save(exorcist);

            Movie amelie = new Movie();
            amelie.setMovieTitle("Amélie");
            amelie.setMovieImg("");
            amelie.setDescription("A whimsical Parisian helps others find joy and love.");
            amelie.setDuration(122);
            amelie.setTrailerLink("");
            amelie.setAgeLimit(pg13);
            amelie.getGenres().add(romance);
            amelie.getGenres().add(comedy);
            movieRepo.save(amelie);

            Movie beautifulMind = new Movie();
            beautifulMind.setMovieTitle("A Beautiful Mind");
            beautifulMind.setMovieImg("");
            beautifulMind.setDescription("A brilliant mathematician battles inner demons.");
            beautifulMind.setDuration(135);
            beautifulMind.setTrailerLink("");
            beautifulMind.setAgeLimit(pg13);
            beautifulMind.getGenres().add(biography);
            beautifulMind.getGenres().add(drama);
            movieRepo.save(beautifulMind);

            Movie gladiator = new Movie();
            gladiator.setMovieTitle("Gladiator");
            gladiator.setMovieImg("");
            gladiator.setDescription("A betrayed general seeks justice in the arena.");
            gladiator.setDuration(155);
            gladiator.setTrailerLink("");
            gladiator.setAgeLimit(pg18);
            gladiator.getGenres().add(action);
            gladiator.getGenres().add(history);
            movieRepo.save(gladiator);

            Movie backToTheFuture = new Movie();
            backToTheFuture.setMovieTitle("Back to the Future");
            backToTheFuture.setMovieImg("");
            backToTheFuture.setDescription("A teen travels through time to fix his family history.");
            backToTheFuture.setDuration(116);
            backToTheFuture.setTrailerLink("");
            backToTheFuture.setAgeLimit(pg7);
            backToTheFuture.getGenres().add(adventure);
            backToTheFuture.getGenres().add(sciFi);
            movieRepo.save(backToTheFuture);

            Movie marchOfPenguins = new Movie();
            marchOfPenguins.setMovieTitle("March of the Penguins");
            marchOfPenguins.setMovieImg("");
            marchOfPenguins.setDescription("Emperor penguins endure an epic Antarctic journey.");
            marchOfPenguins.setDuration(80);
            marchOfPenguins.setTrailerLink("");
            marchOfPenguins.setAgeLimit(pg7);
            marchOfPenguins.getGenres().add(documentary);
            marchOfPenguins.getGenres().add(family);
            movieRepo.save(marchOfPenguins);

            Movie freeSolo = new Movie();
            freeSolo.setMovieTitle("Free Solo");
            freeSolo.setMovieImg("");
            freeSolo.setDescription("A climber attempts El Capitan without ropes.");
            freeSolo.setDuration(100);
            freeSolo.setTrailerLink("");
            freeSolo.setAgeLimit(pg13);
            freeSolo.getGenres().add(documentary);
            freeSolo.getGenres().add(sport);
            movieRepo.save(freeSolo);

            // --- THEATERS ---
            Theater theater1 = new Theater(); theater1.setTheaterName("Main Hall");
            Theater theater2 = new Theater(); theater2.setTheaterName("VIP Lounge");
            theaterRepo.saveAll(Set.of(theater1, theater2));

            // --- SEATS ---
            for (int row = 1; row <= 5; row++) {
                for (int num = 1; num <= 5; num++) {
                    seatRepo.save(new Seat(num, row, theater1));
                }
            }
            for (int row = 1; row <= 3; row++) {
                for (int num = 1; num <= 4; num++) {
                    seatRepo.save(new Seat(num, row, theater2));
                }
            }

            // --- SCREENINGS ---
            Screening screening1 = new Screening();
            screening1.setMovie(dieHard);
            screening1.setTheater(theater1);
            screening1.setScreeningDate(LocalDate.of(2025, 10, 6));
            screening1.setStartTime(2000);
            screening1.setPrice(95.0);
            screeningRepo.save(screening1);

                        for (int b = 700; b <= 2300; b += 400) {
                LocalDate[] dates = {
                        LocalDate.now(),
                        LocalDate.of(2025, 10, 9),
                        LocalDate.of(2025, 10, 10),
                        LocalDate.of(2025, 10, 11),
                        LocalDate.of(2025, 10, 12),
                        LocalDate.of(2025, 10, 13),
                        LocalDate.of(2025, 10, 14),
                        LocalDate.of(2025, 10, 15),
                        LocalDate.of(2025, 10, 16)

                };

                for (LocalDate date : dates) {
                    Screening screening = new Screening();
                    screening.setMovie(dieHard);
                    screening.setTheater(theater1);
                    screening.setStartTime(b);
                    screening.setPrice(150.0);
                    screening.setScreeningDate(date);
                    screeningRepo.save(screening);
                }
            }

            Screening screening2 = new Screening();
            screening2.setMovie(forrest);
            screening2.setTheater(theater2);
            screening2.setScreeningDate(LocalDate.of(2025, 10, 7));
            screening2.setStartTime(1800);
            screening2.setPrice(120.0);
            screeningRepo.save(screening2);

            // --- CUSTOMERS ---
            var alice = new Customer();
            alice.setFirstName("Alice");
            alice.setLastName("Andersen");
            alice.setAge(Date.valueOf(LocalDate.of(1995, 4, 12)));
            alice.setNumber("12345678");
            customerRepo.save(alice);

            var bob = new Customer();
            bob.setFirstName("Bob");
            bob.setLastName("Berg");
            bob.setAge(Date.valueOf(LocalDate.of(2001, 11, 23)));
            bob.setNumber("87654321");
            customerRepo.save(bob);

            // --- RESERVATIONS ---
            var allSeats = seatRepo.findAll();
            var reservation1 = new Reservation();
            reservation1.setCustomer(alice);
            reservation1.setScreening(screening1);
            reservation1.setSeats(allSeats.subList(0, 3)); // Seats 1–3
            reservation1.setUserReservationId("12345");
            reservationRepo.save(reservation1);

            var reservation2 = new Reservation();
            reservation2.setCustomer(bob);
            reservation2.setScreening(screening2);
            reservation2.setSeats(allSeats.subList(3, 5)); // Seats 4–5
            reservation2.setUserReservationId("1234");
            reservationRepo.save(reservation2);

            //Employees
            var emp1 = new Employee();
            emp1.setEmployeeName("Zahaa");
            emp1.setEmployeePassword("1234");
            emp1.setEmployeeCreatedDate(Date.valueOf(LocalDate.now()));
            emp1.setEmployeeType(EmployeeType.ADMIN);
            employeeRepo.save(emp1);

            var emp2 = new Employee();
            emp2.setEmployeeName("Kasper");
            emp2.setEmployeePassword("1234");
            emp2.setEmployeeCreatedDate(Date.valueOf(LocalDate.now()));
            emp2.setEmployeeType(EmployeeType.ADMIN);
            employeeRepo.save(emp2);


            System.out.println("✅ Testdata indlæst med reservations og seats!");
        };
    }
}
