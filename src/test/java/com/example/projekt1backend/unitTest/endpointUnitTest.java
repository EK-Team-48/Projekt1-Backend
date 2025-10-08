package com.example.projekt1backend.unitTest;


import com.example.projekt1backend.employee.entity.Employee;
import com.example.projekt1backend.employee.entity.EmployeeType;
import com.example.projekt1backend.employee.service.EmployeeService;
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
}
