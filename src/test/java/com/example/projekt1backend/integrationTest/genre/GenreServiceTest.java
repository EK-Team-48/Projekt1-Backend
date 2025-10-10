package com.example.projekt1backend.integrationTest.genre;

import com.example.projekt1backend.genre.entity.Genre;
import com.example.projekt1backend.genre.service.GenreService;
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
class GenreServiceTest {

    @Autowired
    private GenreService genreService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void findAllTest() {
        List<Genre> allGenres = genreService.findAll();
        assertNotNull(allGenres);

        // Der er 4 genre i h2-databasen.
        assertTrue(allGenres.size() >= 4);

        // Undersøger om der er en genre der hedder "Sci-Fi" i h2-databasen.
        assertTrue(allGenres.stream().anyMatch(g -> "Sci-Fi".equals(g.getGenre())));
    }

    @Test
    void findByIdTest() {
        Optional<Genre> g1 = genreService.findById(1);
        assertTrue(g1.isPresent());
        assertEquals("Action", g1.get().getGenre());
    }

    @Test
    void postGenreTest() {
        int before = genreService.findAll().size();

        Genre newGenre = new Genre("Horror");

        Genre saveTest = genreService.save(newGenre);
        assertNotNull(saveTest.getGenre());

        Optional<Genre> found = genreService.findById(saveTest.getGenreId());
        assertTrue(found.isPresent());
        assertEquals("Horror", found.get().getGenre());

        List<Genre> after = genreService.findAll();
        assertEquals(before + 1, after.size());
    }

    @Test
    void deleteMovieTest() {
        Genre deleteTest = new Genre("Horror");
        genreService.save(deleteTest);

        Integer id = deleteTest.getGenreId();
        genreService.deleteById(id);

        Optional<Genre> after = genreService.findById(id);
        assertTrue(after.isEmpty());
    }
}