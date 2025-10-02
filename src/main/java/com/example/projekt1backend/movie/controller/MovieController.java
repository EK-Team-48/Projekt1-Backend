package com.example.projekt1backend.movie.controller;

import com.example.projekt1backend.movie.entity.Movie;
import com.example.projekt1backend.movie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("*")
public class MovieController {

    @Autowired
    MovieService movieService;

    @GetMapping("/movies")
    public ResponseEntity<List<Movie>> getMovies() {
        return new ResponseEntity<>(movieService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/movies/{id}")
    public ResponseEntity<Movie> getMovie(@PathVariable Integer id) {
        return new ResponseEntity<>(movieService.findById(id).orElseThrow(() -> new RuntimeException("Movie not found with id: " + id)),
                HttpStatus.OK);
    }

    @PostMapping("/movies")
    public ResponseEntity<Movie> postMovies(@RequestBody Movie movie) {
        return new ResponseEntity<>(movieService.save(movie), HttpStatus.CREATED);
    }

    @PutMapping("/movies/{id}")
    public ResponseEntity<Movie> putMovie(@PathVariable Integer id, @RequestBody Movie movie) {
        Optional<Movie> orgMovie = movieService.findById(id);
        if (orgMovie.isPresent()) {
            movieService.save(movie);
            return ResponseEntity.ok(movie);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/movies/{id}")
    public ResponseEntity<String> deleteMovie(@PathVariable Integer id) {
        Optional<Movie> orgMovie = movieService.findById(id);
        if (orgMovie.isPresent()) {
            String title = orgMovie.get().getMovieTitle();
            movieService.deleteById(orgMovie.get().getMovieId());
            return ResponseEntity.ok(title + ": Has been removed");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Movie not found");
        }
    }
}
