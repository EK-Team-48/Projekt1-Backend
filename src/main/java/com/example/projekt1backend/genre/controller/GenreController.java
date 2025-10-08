package com.example.projekt1backend.genre.controller;


import com.example.projekt1backend.genre.entity.Genre;
import com.example.projekt1backend.genre.service.GenreService;
import com.example.projekt1backend.movie.entity.Movie;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class GenreController {


    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping("/genres")
    public ResponseEntity<List<Genre>> getAll() {
        return new ResponseEntity<>(genreService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/genres/{id}")
    public ResponseEntity<Genre> getGenreById(@PathVariable Integer id) {
        return new ResponseEntity<>(genreService.findById(id).orElseThrow(() -> new RuntimeException("Genre not found with id: " + id)),
                HttpStatus.OK);
    }

    @PostMapping("/genres")
    public ResponseEntity<Genre> postGenre(@RequestBody Genre genre) {
        return new ResponseEntity<>(genreService.save(genre), HttpStatus.CREATED);
    }

    @DeleteMapping("genres/{id}")
    public ResponseEntity<String> deleteGenre(@PathVariable Integer id) {
        Optional<Genre> orgGenre = genreService.findById(id);
        if (orgGenre.isPresent()) {
            String genre = orgGenre.get().getGenre();
            genreService.deleteById(orgGenre.get().getGenreId());
            return ResponseEntity.ok(genre + ": Has been removed");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Genre not found");
        }
    }
}
