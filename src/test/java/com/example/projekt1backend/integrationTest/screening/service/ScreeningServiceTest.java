package com.example.projekt1backend.integrationTest.screening.service;

import com.example.projekt1backend.movie.entity.Movie;
import com.example.projekt1backend.movie.service.MovieService;
import com.example.projekt1backend.screening.model.Screening;
import com.example.projekt1backend.screening.service.ScreeningService;
import com.example.projekt1backend.theater.model.Theater;
import com.example.projekt1backend.theater.service.TheaterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.transaction.Transactional;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql(
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"classpath:data.sql"}
)
@Transactional
@Rollback(true)
class ScreeningServiceTest {

    @Autowired
    private MovieService movieService;
    @Autowired
    private TheaterService theaterService;
    @Autowired
    private ScreeningService screeningService;

    @BeforeEach
    void setUp() {



    }

    @Test
    void findAllScreenings() {
        List<Screening>getAllScreenings = screeningService.findAllScreenings();
        assertFalse(getAllScreenings.isEmpty());
        assertNotNull(getAllScreenings);
        assertTrue(getAllScreenings.size() > 1);
    }

    @Test
    void getScreeningsForMovieNextWeek() {
        Movie smurfs = new Movie();


        smurfs.setMovieTitle("The return of the smurfs");
        smurfs.setAgeLimit(null);
        smurfs.setDescription("Evil smurf are lurking");
        smurfs.setDuration(200);
        movieService.save(smurfs);

        Screening screening = new Screening();
        screening.setScreeningDate(LocalDate.now());
        screening.setMovie(smurfs);
        screening.setPrice(100.0);
        screening.setStartTime(1800);
        Theater theater = theaterService.findById(1);
        screening.setTheater(theater);

        screeningService.addScreening(screening);


        List<Screening>notEmptyScreening = screeningService.getScreeningsForMovieNextWeek(smurfs);
        assertFalse(notEmptyScreening.isEmpty());
        assertEquals(notEmptyScreening.getFirst().getMovie().getMovieTitle(), "The return of the smurfs");

    }

    @Test
    void addScreening() {
        Screening screening = new Screening();
        Double price = null;
        assertEquals(screening.getPrice(), price);

        Screening newScreening = new Screening();
        newScreening.setPrice(100.0);
        newScreening.setStartTime(1800);
        newScreening.setScreeningDate(LocalDate.now());

        Theater theater = theaterService.findById(1);
        newScreening.setTheater(theater);

        Movie movie =movieService.findAll().getFirst();
        newScreening.setMovie(movie);

        screeningService.addScreening(newScreening);

        screening = screeningService.findById(newScreening.getScreeningId());
        price = 100.0;
        assertEquals(screening.getPrice(), price);

    }

    @Test
    void findById() {
        Screening test = screeningService.findById(1);
        assertNotNull(test);
        assertTrue(test.getStartTime() == 123);

    }
    @Test
    void deletedScreening(){
        Screening screening = new Screening();
        Double price = null;
        assertEquals(screening.getPrice(), price);

        Screening newScreening = new Screening();
        newScreening.setPrice(100.0);
        newScreening.setStartTime(1800);
        newScreening.setScreeningDate(LocalDate.now());

        Theater theater = theaterService.findById(1);
        newScreening.setTheater(theater);

        Movie movie =movieService.findAll().getFirst();
        newScreening.setMovie(movie);

        screeningService.addScreening(newScreening);

        screening = screeningService.findById(newScreening.getScreeningId());
        price = 100.0;
        assertEquals(screening.getPrice(), price);

        screeningService.deletedScreening(screening.getScreeningId());


        List<Screening> srcList = screeningService.findAllScreenings();

        for(Screening i: srcList){
            assertTrue(i.getScreeningId() != screening.getScreeningId());
        }

    }



}