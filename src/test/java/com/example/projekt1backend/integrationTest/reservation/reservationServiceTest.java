package com.example.projekt1backend.integrationTest.reservation;

import com.example.projekt1backend.customer.service.CustomerService;
import com.example.projekt1backend.reservation.entity.Reservation;
import com.example.projekt1backend.reservation.service.ReservationService;
import com.example.projekt1backend.screening.service.ScreeningService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql(
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"classpath:data.sql"}
)
@Transactional
@Rollback(true)

public class reservationServiceTest {

    @Autowired
    ReservationService reservationService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ScreeningService screeningService;

    @BeforeEach
    void setUp() {


    }


    @Test
    void addReservation() {
        Reservation test = new Reservation();
        test.setCustomer(customerService.findById(1));
        test.setScreening(screeningService.findById(1));

        Reservation saved = reservationService.addReservation(test);

        Reservation found = reservationService.findById(saved.getReservationId());

        assertNotNull(found);
        assertEquals(saved.getReservationId(), found.getReservationId());
        assertEquals(1, found.getCustomer().getCustomerId());
        assertEquals(1, found.getScreening().getScreeningId());
    }

    @Test
    void getAllReservation() {
        List<Reservation> getAll = reservationService.getAllReservations();
        assertFalse(getAll.isEmpty());
        assertNotNull(getAll);
        assertTrue(getAll.size() > 1);
    }


    @Test
    void controlReservation() {
        Reservation test = new Reservation();
        test.setCustomer(customerService.findById(1));
        test.setScreening(screeningService.findById(1));
        test.setUserReservationId("ef5582d8-499b-4bc3-90b4-ce239f3c73f1");

        Reservation saved = reservationService.addReservation(test);
        assertNotNull(saved);

        String lastFourSaved = test.getUserReservationId();
        String lastFourSavedDigits = lastFourSaved.substring(lastFourSaved.length() - 4);
        Reservation findTest = reservationService.findByLastFour(lastFourSavedDigits);
        assertNotNull(findTest);

        String lastFourFound = findTest.getUserReservationId();
        String lastFourFoundDigits = lastFourFound.substring(lastFourFound.length() - 4);

        assertEquals(lastFourFoundDigits, lastFourSavedDigits);
        assertEquals(saved.getCustomer(), findTest.getCustomer());

    }

    @Test
    void deleteReservation() {

        Reservation test = new Reservation();
        test.setCustomer(customerService.findById(1));
        test.setScreening(screeningService.findById(1));
        test.setUserReservationId("ef5582d8-499b-4bc3-90b4-ce239f3c73f1");

        Reservation saved = reservationService.addReservation(test);
        assertNotNull(saved);

        reservationService.deleteById(saved.getReservationId());
        Optional<Reservation> deleted = reservationService.findByIdOptional(saved.getReservationId());
        
        assertTrue(deleted.isEmpty());
        assertTrue(reservationService.findByIdOptional(saved.getReservationId()).isEmpty());

    }

}
