package com.twu.biblioteca.service;

import com.twu.biblioteca.domain.Movie;

import java.util.Arrays;

public class MoviesService extends ItemService<Movie> {

    private static MoviesService instance;

    public static MoviesService getInstance() {
        if (instance == null)
            instance = new MoviesService();
        return instance;
    }

    private MoviesService() {
        super(Arrays.asList(
                new Movie("The Shawshank Redemption", 1994, "Frank Darabont", 10),
                new Movie("The Godfather", 1972, "Francis Ford Coppola", 10),
                new Movie("The Godfather: Part II", 1974, "Francis Ford Coppola", 9),
                new Movie("The Dark Knight", 2008, "Christopher Nolan", 8),
                new Movie("12 Angry Men", 1957, "Sidney Lumet", 7))
        );
    }

}
