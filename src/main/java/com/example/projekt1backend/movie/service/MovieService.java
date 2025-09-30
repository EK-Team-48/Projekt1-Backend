package com.example.projekt1backend.movie.service;

import com.example.projekt1backend.movie.repository.MovieRepository;
import com.example.projekt1backend.movie.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    public Optional<Movie> findById(Integer id) {
        return movieRepository.findById(id);
    }

    public Movie save(Movie movie) {
        return movieRepository.save(movie);
    }

    public void delete(Movie movie) {
        movieRepository.delete(movie);
    }

}
