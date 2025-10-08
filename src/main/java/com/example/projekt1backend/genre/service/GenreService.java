package com.example.projekt1backend.genre.service;


import com.example.projekt1backend.genre.entity.Genre;
import com.example.projekt1backend.genre.repository.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreService {


    private final GenreRepository genreRepository;

    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    public Optional<Genre> findById(Integer id) {
        return genreRepository.findById(id);
    }

    public Genre save(Genre genre) {
        return genreRepository.save(genre);
    }

    public void deleteById(Integer id) {
        genreRepository.deleteById(id);
    }
}
