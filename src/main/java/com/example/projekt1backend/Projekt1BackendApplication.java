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
            Genre action = new Genre(); action.setGenre("Action");
            Genre drama = new Genre(); drama.setGenre("Drama");
            Genre comedy = new Genre(); comedy.setGenre("Comedy");
            genreRepo.saveAll(Set.of(action, drama, comedy));

            // --- AGE LIMITS ---
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

            //EMployees
            var emp1 = new Employee();
            emp1.setEmployeeName("Abdi");
            emp1.setEmployeePassword("1234");
            emp1.setEmployeeCreatedDate(Date.valueOf(LocalDate.now()));
            emp1.setEmployeeType(EmployeeType.ADMIN);
            employeeRepo.save(emp1);


            System.out.println("✅ Testdata indlæst med reservations og seats!");
        };
    }
}
