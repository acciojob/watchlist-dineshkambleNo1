package com.driver;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MovieRepository {

    private HashMap<String, Movie> movieMap;

    private HashMap<String, Director> directorMap;

    private HashMap<String, List<String>> director_MovieMap;

    // pehle hashmap ko initilize krdo nhi toh null pointer excep dega


    public MovieRepository() {
        this.movieMap = new HashMap<String, Movie>();
        this.directorMap = new HashMap<String, Director>();
        this.director_MovieMap = new HashMap<String, List<String>>();
    }

    public void saveMovie(Movie movie) {
        movieMap.put(movie.getName(), movie);
    }

    public void saveDirector(Director director) {
        directorMap.put(director.getName(), director);
    }

    public Movie findMovie(String movie) {
        return movieMap.get(movie);
    }

    public Director find_director(String director) {
        return directorMap.get(director);
    }

    public void saveMovieDirectorPair(String movie, String director) {

        // pehle check karoooo ki movie ka obj or director k object exist krta h kya

        if (movieMap.containsKey(movie) && director_MovieMap.containsKey(director)) {

            List<String> current_movie_byDirector = new ArrayList<>();

            if (director_MovieMap.containsKey(director)) {

                current_movie_byDirector = director_MovieMap.get(director);

                current_movie_byDirector.add(movie);

                director_MovieMap.put(director, current_movie_byDirector);
            }
        }
    }


    public List<String> findMoviesFromDirector(String director){
        List<String> moviesList = new ArrayList<String>();
        if(director_MovieMap.containsKey(director)) moviesList = director_MovieMap.get(director);
        return moviesList;
    }

    public List<String> findAllMovies() {
        return new ArrayList<>(movieMap.keySet());
    }

    public void deleteDirector(String director) {

        List<String> movies = new ArrayList<String>();

        if (director_MovieMap.containsKey(director)) {
            // movie name dhundho by director from the pair

            movies = director_MovieMap.get(director);

            // deleting all the movies from movieDb by using movie

            for (String movie : movies) {
                if (movieMap.containsKey(movie)) {
                    movieMap.remove(movie);
                }
            }

            // deleting the pair

            director_MovieMap.remove(director);
        }

        if (directorMap.containsKey(director)) {
            directorMap.remove(director);
        }
    }

    public void deleteAllDirector() {
        HashSet<String> movieSet = new HashSet<String>();

        // deleting the director's map

        directorMap = new HashMap<>();

        // find out all the movies by all the direrctor combined

        for (String director : director_MovieMap.keySet()) {

            // iterate in the list of movies

            for (String movies : director_MovieMap.get(director)) {
                movieSet.add(movies);

            }

            // deleting movies from the movies from the mlovieDb

            for (String movie : movieSet) {

                if (movieMap.containsKey(movie)) {
                    movieMap.remove(movie);
                }
            }

            // pair ko bhi delete kr do
             director_MovieMap = new HashMap<>();
        }
    }

}







