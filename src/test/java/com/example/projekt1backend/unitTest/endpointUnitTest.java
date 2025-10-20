package com.example.projekt1backend.unitTest;


import com.example.projekt1backend.ageLimit.entity.AgeLimit;
import com.example.projekt1backend.employee.entity.Employee;
import com.example.projekt1backend.employee.entity.EmployeeType;
import com.example.projekt1backend.employee.service.EmployeeService;
import com.example.projekt1backend.genre.entity.Genre;
import com.example.projekt1backend.movie.entity.Movie;
import com.example.projekt1backend.movie.service.MovieService;
import com.example.projekt1backend.reservation.dto.ReservationViewDTO;
import com.example.projekt1backend.reservation.entity.Reservation;
import com.example.projekt1backend.reservation.service.ReservationService;
import com.example.projekt1backend.screening.model.Screening;
import com.example.projekt1backend.screening.service.ScreeningService;
import com.example.projekt1backend.theater.model.Theater;
import com.example.projekt1backend.theater.service.TheaterService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class endpointUnitTest {


    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    ReservationService reservationService;

    @MockitoBean
    EmployeeService employeeService;

    @MockitoBean
    MovieService movieService;

    @MockitoBean
    TheaterService theaterService;

    @MockitoBean
    ScreeningService screeningService;

    Screening screening = new Screening();
    Movie movie = new Movie();
    Theater theater = new Theater();


    @BeforeEach
    void setup() {
        movie.setMovieId(5);
        movie.setMovieTitle("die hard");

        theater.setTheaterId(5);
        theater.setTheaterName("sal 1");

        LocalDate dato = LocalDate.now();
        screening.setScreeningId(5);
        screening.setMovie(movie);
        screening.setTheater(theater);
        screening.setScreeningDate(dato);
        screening.setStartTime(1390);
        screening.setPrice(100.0);

    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void getAllReservation() throws Exception {

        Mockito.when(this.reservationService.getAllReservations()).thenReturn(List.of(new Reservation(1, null, null, "1")));

        mockMvc.perform(get("/api/v1/reservations"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void getReservationByLastFourDigits() throws Exception {
        Mockito.when(reservationService.findByLastFourDTO("4321"))
                .thenReturn(new ReservationViewDTO("123", "123",null, null, null,null, null));

        mockMvc.perform(get("/api/v1/reservations/4321"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("123"));
    }

    @Test
    void addReservation() throws Exception {

        mockMvc.perform(post("/api/v1/reservations")
                .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                         {
                                       "customerID": 1,
                                       "screeningID": 1,
                                       "seatId": [1,2],
                                       "userReservationId": "1"
                                    }
                                """))
                .andDo(print())
                .andExpect(status().isCreated());

    }

    @Test
    void getAllEmployees() throws Exception {

        Mockito.when(this.employeeService.getAll()).thenReturn(List.of(new Employee(1, "1", null, EmployeeType.ADMIN, "1234")));

        mockMvc.perform(get("/api/v1/employee"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void addEmployee() throws Exception {
        mockMvc.perform(post("/api/v1/employee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                "employeeId": 1,
                                "employeeName": "1234",
                                "employeePassword": "1234",
                                "employeeCreatedDate": null,
                                "employeeType": "ADMIN"
                                    }
                                """))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    void deleteEmployee() throws Exception {
        Mockito.doNothing().when(employeeService).deleteEmployee(1);

        mockMvc.perform(delete("/api/v1/employee/1"))
                .andDo(print())
                .andExpect(status().isAccepted());

        Mockito.verify(employeeService, times(1)).deleteEmployee(1);

    }
    //screenings:
    @Test
    void getAllScreenings() throws Exception {

        Mockito.when(this.screeningService.findAllScreenings()).thenReturn(List.of(screening));

        mockMvc.perform(get("/api/v1/screenings"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void getScreeningById() throws Exception{

        Mockito.when(this.screeningService.findById(1)).thenReturn(screening);

        mockMvc.perform(get("/api/v1/screenings/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(100.0));

    }

    @Test
    void getScreeningsByMovie() throws Exception {

        Mockito.when(movieService.findById(5)).thenReturn(Optional.of(movie));

        Mockito.when(screeningService.getScreeningsForMovieNextWeek(movie)).thenReturn(List.of(screening));

        mockMvc.perform(get("/api/v1/screenings/movie/5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());

    }




    @Test
    void createScreening() throws Exception {
        Mockito.when(movieService.findById(5)).thenReturn(Optional.of(movie));
        Mockito.when(screeningService.addScreening(Mockito.any(Screening.class)))
                .thenReturn(screening);

        mockMvc.perform(post("/api/v1/screenings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "movieId": 5,
                                  "theaterId": 5,
                                  "screeningDate": "2025-10-07",
                                  "startTime": 1900,
                                  "price": 120.0
                                }
                                """))
                .andDo(print())
                .andExpect(status().isCreated());
    }


    @Test
    void deleteScreening() throws Exception{

        Mockito.when(screeningService.findById(5)).thenReturn(screening);
        Mockito.doNothing().when(screeningService).deletedScreening(5);

        mockMvc.perform(delete("/api/v1/screenings/5"))
                .andDo(print())
                .andExpect(status().isOk());

        Mockito.verify(screeningService, times(1)).deletedScreening(5);

    }

    @Test
    void updateScreening () throws Exception {
        Mockito.when(screeningService.findById(1)).thenReturn(screening);
        Mockito.when(movieService.findById(5)).thenReturn(Optional.of(movie));
        Mockito.when(theaterService.findById(5)).thenReturn(theater);
        Mockito.when(screeningService.addScreening(Mockito.any(Screening.class)))
                .thenReturn(screening);

        Mockito.when(screeningService.findById(5)).thenReturn(screening);

        mockMvc.perform(put("/api/v1/screenings/5")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "screeningId": 5,
                                  "movieId": 5,
                                  "theaterId": 5,
                                  "screeningDate": "2025-10-07",
                                  "startTime": 1900,
                                  "price": 120.0
                                }
                                """))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    void getAllMovies() throws Exception {
        AgeLimit ageLimit = new AgeLimit(7);
        Set<Genre> genres = new HashSet<>(List.of(new Genre("Horror"), new Genre("Drama")));
        Movie m = new Movie();


        m.setMovieTitle("title test");
        m.setDescription("Integration test movie");
        m.setDuration(123);
        m.setTrailerLink("https://example.com/trailer");
        m.setMovieImg("poster.jpg");
        m.setAgeLimit(ageLimit);
        m.setGenres(genres);

        Mockito.when(this.movieService.findAll()).thenReturn(List.of(m));

        mockMvc.perform(get("/api/v1/movies"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void getMovieById() throws Exception {
        AgeLimit ageLimit = new AgeLimit(7);
        Set<Genre> genres = new HashSet<>(List.of(new Genre("Horror"), new Genre("Drama")));
        Movie m = new Movie();


        m.setMovieTitle("title test");
        m.setDescription("Integration test movie");
        m.setDuration(123);
        m.setTrailerLink("https://example.com/trailer");
        m.setMovieImg("poster.jpg");
        m.setAgeLimit(ageLimit);
        m.setGenres(genres);

        Mockito.when(movieService.findById(1))
                .thenReturn(Optional.of(m));

        mockMvc.perform(get("/api/v1/movies/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.movieTitle").value("title test"));
    }

    @Test
    void addMovie() throws Exception {
        mockMvc.perform(post("/api/v1/movies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                         "movieImg": "https://image.tmdb.org/t/p/original/yCyv9inVfwdZa0DyFAEEElqNgNn.jpg",
                                         "movieTitle": "Harry Potter and the philosopher's stone",
                                         "description": "Harry Potter and the Philosopher's Stone is the first novel in J.K. Rowling's Harry Potter series, following orphan Harry Potter as he discovers on his eleventh birthday that he is a wizard and has been accepted into Hogwarts School of Witchcraft and Wizardry. At the school, he makes close friends, learns about his parents' death at the hands of the dark wizard Lord Voldemort, and uncovers the truth behind the Boy Who Lived and the mythical Philosopher's Stone. The book introduces the magical world, its rules, and the key characters, setting the stage for the rest of the beloved series.",
                                         "duration": 150,
                                         "trailerLink": "https://www.youtube.com/watch?v=VyHV0BRtdxo",
                                         "ageLimit": {
                                             "ageLimitId": 1,
                                             "ageRating": 13
                                         },
                                         "genres": [
                                             {
                                                 "genreId": 2,
                                                 "genre": "Drama"
                                             }
                                         ]
                                     }
                                """))
                .andDo(print())
                .andExpect(status().isCreated());
    }


    @Test
    void deleteMovie() throws Exception {
        Movie m = new Movie();

        m.setMovieId(1);
        m.setMovieTitle("title test");

        Mockito.when(movieService.findById(1))
                .thenReturn(Optional.of(m));

        mockMvc.perform(delete("/api/v1/movies/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("title test: Has been removed"));

        Mockito.verify(movieService, Mockito.times(1)).findById(1);
        Mockito.verify(movieService, Mockito.times(1)).deleteById(1);
        Mockito.verifyNoMoreInteractions(movieService);
    }



}

