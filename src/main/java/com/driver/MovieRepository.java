package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@Repository
public class MovieRepository {


    private static HashMap<String,Movie> movieMap=new HashMap<String,Movie>();
    private static HashMap<String,Director> directorMap=new HashMap<String,Director>();
    private static HashMap<String, List<String>> movieDirectorMapping=new HashMap<String, List<String>>();

    public static void saveMovie(Movie movie){
        movieMap.put(movie.getName(),movie);
    }
    public static void saveDirector(Director director ){
        directorMap.put(director.getName(),director);
    }

    public static void saveMovieDirectorPair(String movie,String director){
        if(movieMap.containsKey(movie) && directorMap.containsKey(director)){
            List<String> movieList=new ArrayList<>();
            if(movieDirectorMapping.containsKey(director)) movieList=movieDirectorMapping.get(director);
            movieList.add(movie);
            movieDirectorMapping.put(director,movieList);

        }
    }
    public static Movie findMovie(String name){
        return movieMap.get(name);
    }
    public static Director find_director(String name){
        return directorMap.get(name);
    }

    public static List<String> findMoviesFromDirector(String director ){
        List movieDirectorList=new ArrayList<>();
        if(movieDirectorMapping.containsKey(director)) movieDirectorList=movieDirectorMapping.get(director);
        return movieDirectorList;
    }
    public static List<String> findAllMovies(){
        return new ArrayList<>(movieMap.keySet());
    }
    public static void deleteDirector(String director){
        List<String> teacherlList=new ArrayList();
        if(movieDirectorMapping.containsKey(director)){
            teacherlList=movieDirectorMapping.get(director);
            for (String directors:teacherlList){
                if(movieMap.containsKey(directors)){
                    movieMap.remove(directors);
                }
            }
        }

    }
    public static void deleteAllDirector(){
        HashSet<String> directorSet=new HashSet<>();
        for(String directors:movieDirectorMapping.keySet()){
            for(String movies:movieDirectorMapping.get(directors)){
                directorSet.add(movies);
            }
        }
        for(String student:directorSet){
            if(movieMap.containsKey(student)){
                movieMap.remove(student);

            }
        }
    }


}