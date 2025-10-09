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

import java.util.*;

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


    @BeforeEach
    void setup() {
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
                .thenReturn(new ReservationViewDTO("123", "123",null, null, null,null));

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
