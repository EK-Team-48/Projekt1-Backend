package com.example.projekt1backend.unitTest;


import com.example.projekt1backend.employee.entity.Employee;
import com.example.projekt1backend.employee.entity.EmployeeType;
import com.example.projekt1backend.employee.service.EmployeeService;
import com.example.projekt1backend.movie.entity.Movie;
import com.example.projekt1backend.reservation.dto.ReservationViewDTO;
import com.example.projekt1backend.reservation.entity.Reservation;
import com.example.projekt1backend.reservation.service.ReservationService;
import com.example.projekt1backend.screening.model.Screening;
import com.example.projekt1backend.screening.service.ScreeningService;
import com.example.projekt1backend.theater.model.Theater;
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
import java.util.List;

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
    private ScreeningService screeningService;

    private Screening screening = new Screening();
    private Movie movie = new Movie();
    private Theater theater = new Theater();


    @BeforeEach
    void setup() {
        movie.setMovieId(1);
        movie.setMovieTitle("die hard");

        theater.setId(1);
        theater.setTheaterName("sal 1");

        LocalDate dato = LocalDate.now();
        screening.setScreeningId(1);
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

        Mockito.when(this.screeningService.getScreeningsForMovieNextWeek(movie)).thenReturn(List.of(screening));

        mockMvc.perform(get("/api/v1/screenings/movie/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());

    }

    @Test
    void createScreening() throws Exception {
        mockMvc.perform(post("/api/v1/screenings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "movieId": 1,
                                  "theaterId": 2,
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

        Mockito.when(screeningService.findById(1)).thenReturn(screening);
        Mockito.doNothing().when(screeningService).deletedScreening(1);

        mockMvc.perform(delete("/api/v1/screenings/1"))
                .andDo(print())
                .andExpect(status().isOk());

        Mockito.verify(screeningService, times(1)).deletedScreening(1);

    }

    @Test
    void updateScreening () throws Exception {
        Mockito.when(screeningService.findById(1)).thenReturn(screening);

        mockMvc.perform(put("/api/v1/screenings/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "screeningId": 1,
                                  "movieId": 1,
                                  "theaterId": 2,
                                  "screeningDate": "2025-10-07",
                                  "startTime": 1900,
                                  "price": 120.0
                                }
                                """))
                .andDo(print())
                .andExpect(status().isCreated());
    }



}

