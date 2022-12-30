package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    public void  addMovie(Movie movie) {
        movieRepository.saveMovie(movie);
    }

    public void addDirector(Director director){
        movieRepository.saveDirector(director);
    }

    public void create_movie_director_pair(String movie, String director) {
        movieRepository.saveMovieDirectorPair(movie,director);
    }

    public Movie findMovie(String movie_name){
        return movieRepository.findMovie(movie_name);
    }

    public Director findDirector(String director_name) {
        return movieRepository.find_director(director_name);
    }

    public List<String> findAllMovies() {
        return movieRepository.findAllMovies();
    }

    public List<String> findMoviesFromDirector(String director){
        return movieRepository.findMoviesFromDirector(director);
    }
    public void deleteDirector(String director){
        movieRepository.deleteDirector(director);
    }

    public void deleteAllDirectors(){
        movieRepository.deleteAllDirector();
    }
}
