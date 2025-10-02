package com.example.projekt1backend.movie;

import com.example.projekt1backend.ageLimit.entity.AgeLimit;
import com.example.projekt1backend.movie.entity.Movie;
import com.example.projekt1backend.movie.service.MovieService;
import com.example.projekt1backend.movieStatus.entity.MovieStatus;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
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
class MovieServiceTest {

    @Autowired
    private MovieService movieService;

    @PersistenceContext
    private EntityManager em;

    private Movie newMovie(String title) {
        Movie m = new Movie();
        m.setMovieTitle(title);
        m.setDescription("Integration test movie");
        m.setDuration(123);
        m.setTrailerLink("https://example.com/trailer");
        m.setMovieImg("poster.jpg");

        m.setAgeLimit(em.getReference(AgeLimit.class, 1));
        m.setMovieStatus(em.getReference(MovieStatus.class, 2));

        return m;
    }

    @Test
    void findAllTest() {
        List<Movie> allMovies = movieService.findAll();
        assertNotNull(allMovies);

        // Der er 3 movies i h2-databasen.
        assertTrue(allMovies.size() >= 3);

        // Undersøger om der er en movie med titlen "Fast & Curious" i h2-databasen.
        assertTrue(allMovies.stream().anyMatch(m -> "Fast & Curious".equals(m.getMovieTitle())));
    }

    @Test
    void findByIdTest() {
        Optional<Movie> m1 = movieService.findById(1);
        assertTrue(m1.isPresent());
        assertEquals("Fast & Curious", m1.get().getMovieTitle());
        assertNotNull(m1.get().getAgeLimit());
        assertNotNull(m1.get().getMovieStatus());
    }

    @Test
    void postMovieTest() {
        int before = movieService.findAll().size();

        Movie saveTest = movieService.save(newMovie("test movie"));
        assertNotNull(saveTest.getMovieId());

        Optional<Movie> found = movieService.findById(saveTest.getMovieId());
        assertTrue(found.isPresent());
        assertEquals("test movie", found.get().getMovieTitle());
        assertNotNull(found.get().getAgeLimit());
        assertNotNull(found.get().getMovieStatus());

        List<Movie> after = movieService.findAll();
        assertEquals(before + 1, after.size());
    }

    @Test
    void updateMovieTest() {
        Movie existing = movieService.findById(2).orElseThrow();
        String newDescription = "Updated movie description";

        existing.setDescription(newDescription);

        em.flush();
        em.clear();
        Movie reread = movieService.findById(2).orElseThrow();
        assertEquals(newDescription, reread.getDescription());
    }

    @Test
    void deleteMovieTest() {
        Movie deleteTest = newMovie("movie delete");
        movieService.save(deleteTest);

        Integer id = deleteTest.getMovieId();
        movieService.deleteById(id);

        em.flush();
        em.clear();

        Optional<Movie> after = movieService.findById(id);
        assertTrue(after.isEmpty());
    }
}
